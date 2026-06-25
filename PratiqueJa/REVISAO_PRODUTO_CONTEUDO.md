# Revisão do produto — /produtos/conteudo

Análise da página de vendas (`conteudo.xhtml`): preços, planos, funcionalidades e conversão.

---

## 1. Inconsistências/erros para corrigir já

- **Lista do Master não é cumulativa.** O card Master não repete "Exercícios exclusivos
  nos 3 níveis" nem "Banco de imagens padrão" (que aparecem no Básico/Premium). O cliente
  pode achar que o plano mais caro tem menos. → *Já corrigido: adicionar linha dos exercícios.*

- **Confusão no "Acesso ao Pratique Já" (tabela).** Básico→"Básico", Premium→"Básico",
  Master→"Premium". Ou seja, o plano *Premium* dá acesso *Básico* à plataforma. NÃO é bug
  (reflete a regra real: só o Master dá acesso Premium), mas o rótulo confunde. Sugestão:
  renomear para algo como "Acesso à plataforma: Free / Free / Premium" — decisão sua.

- **Teste grátis sem porta de entrada.** O hero promete "Teste grátis 7 dias", mas os 3
  botões vão direto para pagamento (`pagamentoCriador(...)`). Falta um CTA de "Começar
  teste grátis". (Precisa existir um fluxo de trial no backend — confirmar.)

- **"O mais escolhido" sem destaque visual.** Os 3 cards eram iguais. → *Já corrigido:
  borda + selo "Mais popular" no Premium.*

- **Hero não mostra a diferença de volume.** "Posts por envio: 6" é igual para todos, mas
  a frequência muda muito (a cada 3 dias vs diário). Poderia exibir "até ~180 posts/mês".

## 2. Planos & preços

- **Salto Básico→Premium desbalanceado.** Básico = ~60 posts/mês; Premium = ~180/mês
  (3×) por apenas +R$100 (+34%). Custo por post: Básico ~R$5, Premium ~R$2,2. Ótimo para
  empurrar o Premium, mas faz o Básico parecer caro. Opções: baixar o Básico (ex.: R$197)
  ou reduzir o volume do Premium.

- **Master se diferencia pouco.** Mesma frequência do Premium; ganha só +backgrounds,
  legendas personalizadas, suporte e acesso Premium, por +R$100. Reforçar com algo
  "profissional" (agendamento automático, outras disciplinas, multi-perfil).

- **Sem plano anual.** Toggle Mensal/Anual com ~2 meses grátis melhora caixa e retenção.
  (Forte recomendação — precisa definir os preços anuais.)

- **Ancoragem de valor.** Mostrar "menos de R$10/dia" ou "R$X por post" ajuda a justificar.

## 3. Funcionalidades (diferencial ou add-on)

- **Agendamento/publicação automática** (API Instagram/Meta) — hoje é manual; maior salto
  de valor e justificaria o Master.
- **Outras disciplinas** além de matemática (expansão de mercado).
- **Formatos novos:** carrossel, stories, capa de destaque, e **reel em vídeo real**
  (hoje o "reel" é imagem estática).
- **Galeria de exemplos** dos posts gerados na própria página (prova visual converte muito).
- **Prova social:** depoimentos, nº de professores ativos.

## 4. Conversão da página

- Selo "Mais popular" + destaque no Premium (feito).
- CTA de teste grátis no topo.
- Faixa de garantia ("7 dias grátis, cancele quando quiser") perto dos botões.
- Galeria de exemplos + depoimentos.

---

## Já implementado nesta sessão
1. Selo "Mais popular" + borda de destaque no card Premium (`conteudo.xhtml` + `conteudo.css`).
2. (a fazer) Lista cumulativa do Master.

## Depende de decisão/insumo seu
- Toggle anual → preços anuais.
- Galeria/prova social → quais exemplos e depoimentos usar.
- CTA de teste grátis → confirmar se há fluxo de trial no backend.
- Reequilíbrio de preço Básico/Premium → números.
