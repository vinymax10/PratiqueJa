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

echo ">> publicando PratiqueJa.war..."
rm -f "$WAR.deployed" "$WAR.failed" "$WAR.dodeploy"
install -o wildfly -g wildfly -m 644 "$SRC" "$WAR"
sudo -u wildfly touch "$WAR.dodeploy"

echo ">> aguardando o scanner (ate ~120s)..."
for i in $(seq 1 40); do
	sleep 3
	if [ -f "$WAR.deployed" ]; then
		echo
		echo "===================== OK: NO AR ====================="
		limpar
		echo
		echo "Backup da versao anterior: $BAK"
		echo "Rollback manual:"
		echo "  sudo install -o wildfly -g wildfly -m 644 $BAK $WAR"
		echo "  sudo rm -f $WAR.deployed $WAR.failed"
		echo "  sudo -u wildfly touch $WAR.dodeploy"
		exit 0
	fi
	if [ -f "$WAR.failed" ]; then
		echo
		echo "===================== FALHOU ====================="
		cat "$WAR.failed"
		echo
		echo ">> ROLLBACK AUTOMATICO para $BAK ..."
		rm -f "$WAR.failed" "$WAR.deployed" "$WAR.dodeploy"
		install -o wildfly -g wildfly -m 644 "$BAK" "$WAR"
		sudo -u wildfly touch "$WAR.dodeploy"
		for j in $(seq 1 40); do
			sleep 3
			[ -f "$WAR.deployed" ] && { echo "== rollback OK: versao anterior de volta no ar =="; exit 2; }
			[ -f "$WAR.failed" ]   && { echo "!! ROLLBACK TAMBEM FALHOU — ver /opt/wildfly/standalone/log/server.log"; exit 3; }
		done
		echo "!! timeout no rollback — ver /opt/wildfly/standalone/log/server.log"
		exit 3
	fi
done

echo "AVISO: timeout aguardando o marcador. Estado atual:"
ls -la "$DEP"
exit 4
