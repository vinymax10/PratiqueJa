#!/usr/bin/env bash
# Deploy do PratiqueJa.war (default-host, contexto /).
#
# Este arquivo roda NO VPS, em /home/deploy/deploy_pratiqueja.sh. A copia daqui e
# a do servidor sao iguais byte a byte — ao mexer numa, mande a outra junto:
#
#     scp deploy_pratiqueja.sh deploy@179.197.70.136:/home/deploy/
#
# Ele cuida so da metade que acontece no servidor: conferir, trocar, esperar o
# scanner e, se falhar, voltar sozinho. Os tres passos do deploy inteiro:
#
#   1) build          mvn -o clean package          (gera target/PratiqueJa.war)
#   2) envio          scp target/PratiqueJa.war deploy@VPS:~/PratiqueJa.war.new
#                     sha256sum target/PratiqueJa.war | awk '{print $1}' > pj.sha256
#                     scp pj.sha256 deploy@VPS:~/PratiqueJa.war.new.sha256
#   3) publicacao     ssh deploy@VPS 'sudo -n /home/deploy/deploy_pratiqueja.sh'
#
# POR QUE ELE REINICIA O WILDFLY (28/08/2026)
#
# Ate aqui a publicacao era a quente: `touch .dodeploy` e o scanner trocava a
# aplicacao sem reiniciar a JVM. Isso custou um OOM.
#
# Em 27/08 as 06:06 o WildFly morreu com OutOfMemoryError num servidor OCIOSO —
# nenhum acesso na madrugada, so o timer das 06:00 gerando os posts do dia. A
# geracao nao era a causa: no dia seguinte, numa JVM recem-reiniciada, o mesmo
# trabalho rodou em 25 segundos. O que faltava era heap, e o motivo estava no
# historico: a JVM vinha de antes de 20/08 e levara 40 redeploys a quente sem um
# unico restart (18 so no dia 22). Cada troca a quente so libera o classloader
# antigo se nada segurar referencia a ele — e alguma coisa segurava.
#
# Reiniciar no deploy troca ~1-2 min de indisponibilidade por uma JVM que comeca
# limpa toda vez. Derruba junto o OrcamentoDigital, que divide a JVM; por isso o
# script confere os dois no fim, e nao so o PratiqueJa.
#
# O sudo do usuario deploy e NOPASSWD para este caminho exato — `sudo bash ~/...`
# pede senha, `sudo -n /home/deploy/deploy_pratiqueja.sh` nao.
#
# O .sha256 existe para o passo 1 nao publicar um WAR truncado pelo passo 2: e o
# unico jeito de saber que os 192 MB chegaram inteiros.
#
# Diferente dos deploy_full.sh/deploy_root.sh (de 14/07): o hash vem do arquivo
# .sha256 em vez de hardcoded, e o alvo e PratiqueJa.war em vez do ROOT.war, que
# nao existe mais. Por isso este da pra reusar em todo deploy.
set -euo pipefail

DEP=/opt/wildfly/standalone/deployments
SRC=/home/deploy/PratiqueJa.war.new
WAR=$DEP/PratiqueJa.war
TS=$(date +%Y%m%d-%H%M%S)

# O backup do WAR anterior mora FORA da pasta de deployments. Ela e varrida pelo
# scanner do WildFly e deve conter so o que esta no ar — e o .bak-TS ficava la
# dentro, um por deploy, sem ninguem nunca apagar. Somados aos do OrcamentoDigital
# eram 91 arquivos e 7,5 GB de WAR morto ao lado do que esta rodando.
BKP=/home/deploy/backups/wars

# Quantos backups guardar. Os mais antigos que isto somem ao fim de um deploy que
# deu certo — so de um que deu certo: se o deploy falha, nada e apagado.
MANTER=3

BAK=$BKP/PratiqueJa.war.bak-$TS

[ "$(id -u)" = "0" ] || { echo "ERRO: rode com sudo (root)."; exit 1; }
[ -f "$SRC" ]        || { echo "ERRO: $SRC nao existe."; exit 1; }
[ -f "$SRC.sha256" ] || { echo "ERRO: $SRC.sha256 nao existe."; exit 1; }

# Backup pertence ao deploy, nao ao root: e ele quem faz a limpeza e quem
# eventualmente copia o arquivo de volta.
install -d -o deploy -g deploy -m 755 "$BKP"

# Deixa a pasta de deployments so com o que esta no ar. Nunca no caminho de
# falha: ali o backup e a unica volta.
limpar()
{
	echo ">> limpeza"

	# Backup antigo que tenha ficado dentro da pasta de deployments sai de la.
	# So dispara uma vez, para o que as versoes anteriores deste script deixaram.
	find "$DEP" -maxdepth 1 -name 'PratiqueJa.war.bak-*' -exec mv -t "$BKP/" {} + 2>/dev/null || true
	chown deploy:deploy "$BKP"/PratiqueJa.war.bak-* 2>/dev/null || true

	# Guarda os mais recentes e apaga o resto.
	ls -t "$BKP"/PratiqueJa.war.bak-* 2>/dev/null | tail -n +$((MANTER + 1)) | xargs -r rm -f || true

	# O WAR de transferencia ja cumpriu o papel.
	rm -f "$SRC" "$SRC.sha256"

	echo "   backups guardados: $(ls -1 "$BKP"/PratiqueJa.war.bak-* 2>/dev/null | wc -l) em $BKP"
	echo "   lixo em $DEP: $(ls -1 "$DEP"/PratiqueJa.war.bak-* 2>/dev/null | wc -l)"
}

echo ">> conferindo hash do WAR enviado..."
EXPECT=$(cat "$SRC.sha256")
GOT=$(sha256sum "$SRC" | awk '{print $1}')
[ "$GOT" = "$EXPECT" ] || { echo "ERRO: hash divergente."; echo "  esperado: $EXPECT"; echo "  obtido:   $GOT"; exit 1; }
echo "   ok"

echo ">> backup do WAR atual -> $BAK"
install -o deploy -g deploy -m 644 "$WAR" "$BAK"

# Poe o WAR no lugar e marca para o scanner pegá-lo no proximo boot.
publicar()
{
	local origem=$1
	rm -f "$WAR.deployed" "$WAR.failed" "$WAR.dodeploy"
	install -o wildfly -g wildfly -m 644 "$origem" "$WAR"
	sudo -u wildfly touch "$WAR.dodeploy"
}

# Espera o marcador aparecer. 0 = no ar, 1 = falhou, 2 = nem um nem outro.
# O prazo e maior que o do scanner a quente porque aqui a JVM sobe do zero.
aguardar()
{
	local tentativas=${1:-60}

	for _ in $(seq 1 "$tentativas"); do
		sleep 3
		[ -f "$WAR.deployed" ] && return 0
		[ -f "$WAR.failed" ]   && return 1
	done

	return 2
}

echo ">> publicando PratiqueJa.war..."
publicar "$SRC"

# O restart e o ponto do exercicio: JVM nova, sem os classloaders acumulados.
# Nao usa systemctl reload nem o scanner a quente de proposito.
echo ">> reiniciando o WildFly (a JVM sobe limpa; ~1-2 min de indisponibilidade)..."
systemctl restart wildfly

echo ">> aguardando a aplicacao subir (ate ~180s)..."
# `|| RESULTADO=$?` e obrigatorio: com `set -e` (linha 44) um `aguardar 60` solto
# que devolvesse 1 ou 2 encerraria o script aqui, antes de chegar ao rollback —
# justamente no caso em que ele e necessario.
RESULTADO=0
aguardar 60 || RESULTADO=$?

if [ "$RESULTADO" = "0" ]; then
	echo
	echo "===================== OK: NO AR ====================="

	# O OrcamentoDigital divide a JVM: o restart derrubou os dois, entao nao
	# basta o PratiqueJa ter voltado.
	OD=$DEP/OrcamentoDigital.war
	if [ -f "$OD.failed" ]; then
		echo "!! ATENCAO: o PratiqueJa subiu, mas o OrcamentoDigital FALHOU:"
		cat "$OD.failed"
	elif [ -f "$OD.deployed" ]; then
		echo "   OrcamentoDigital: no ar"
	elif [ -e "$OD" ]; then
		echo "!! ATENCAO: OrcamentoDigital sem marcador — conferir o server.log"
	fi

	limpar
	echo
	echo "Backup da versao anterior: $BAK"
	echo "Rollback manual:"
	echo "  sudo install -o wildfly -g wildfly -m 644 $BAK $WAR"
	echo "  sudo rm -f $WAR.deployed $WAR.failed"
	echo "  sudo -u wildfly touch $WAR.dodeploy"
	echo "  sudo systemctl restart wildfly"
	exit 0
fi

echo
if [ "$RESULTADO" = "1" ]; then
	echo "===================== FALHOU ====================="
	cat "$WAR.failed"
else
	# Sem marcador nenhum: normalmente a JVM nao subiu. Volta a versao boa do
	# mesmo jeito — deixar o servidor fora do ar esperando nao ajuda ninguem.
	echo "===================== TIMEOUT ====================="
	echo "Nenhum marcador apareceu. Estado do systemd:"
	systemctl status wildfly --no-pager | head -12 || true
fi

echo
echo ">> ROLLBACK AUTOMATICO para $BAK ..."
publicar "$BAK"
systemctl restart wildfly

if aguardar 60; then
	echo "== rollback OK: versao anterior de volta no ar =="
	exit 2
fi

echo "!! ROLLBACK TAMBEM FALHOU — ver /opt/wildfly/standalone/log/server.log"
exit 3
