# Plano de Implementação — Confecção de Provas

## Visão Geral

Serviço para professores confeccionarem provas PDF a partir de exercícios gerados automaticamente.
Disponível em 3 planos com limites mensais de uso.

---

## Planos de Acesso

| Plano  | Provas/mês |
|--------|-----------|
| Básico | 100       |
| Pro    | 500       |
| Master | 2000      |

Limites definidos no enum `PlanoProva` (não no banco).  
Campo `planoProva` adicionado à entidade `Usuario`.

---

## Modelo de Dados

### Enums criados em `modelo/prova/`

| Enum              | Valores                                                    |
|-------------------|------------------------------------------------------------|
| `PlanoProva`      | BASICO(100), PRO(500), MASTER(2000)                        |
| `NomeDocumento`   | PROVA, AVALIACAO, SIMULADO, TESTE                          |
| `FormatoProva`    | ALTERNATIVAS, DISCURSIVA                                   |
| `TipoGabarito`    | SOMENTE_GABARITO, COM_RESOLUCAO                            |
| `PosicaoGabarito` | APOS_CADA_PROVA, AGRUPADO_NO_FINAL                         |
| `FormatoSaida`    | PDF_UNICO, ZIP                                             |
| `StatusPedidoProva` | AGUARDANDO, GERANDO, CONCLUIDO, ERRO                     |

### Entidades em `modelo/prova/`

**`ConfigPedidoProva`** — configuração singleton (admin)
- `maxExerciciosPorProva` int (default 40)
- `maxProvasPorSolicitacao` int (default 50)
- `diasRetencaoPdf` int (default 30)

**`PedidoProva`** — solicitação do professor
- `usuario` FK → Usuario
- `codigoBatch` String (4 chars, ex: "A3F7")
- `nomeDocumento` NomeDocumento
- `titulo` String
- `escola` String (opcional)
- `nomeProfessor` String (opcional)
- `dataProva` LocalDate (opcional)
- `formatoProva` FormatoProva
- `tipoGabarito` TipoGabarito
- `posicaoGabarito` PosicaoGabarito
- `formatoSaida` FormatoSaida
- `quantidadeProvas` int (1–50)
- `status` StatusPedidoProva
- `progresso` double (0–100)
- `dataSolicitacao` LocalDateTime
- `dataExpiracao` LocalDateTime (+ diasRetencaoPdf dias)
- `caminhoArquivo` String (caminho do PDF/ZIP final)
- `nomeDownload` String
- `itens` List<ItemPedidoProva>

**`ItemPedidoProva`** — cada linha do conteúdo da prova
- `pedidoProva` FK → PedidoProva
- `assunto` FK → Assunto
- `nivel` Nivel
- `quantidade` int
- `ordem` int

---

## Código de Identificação

Cada `PedidoProva` recebe um `codigoBatch` gerado automaticamente (ex: "A3F7").  
Cada prova individual recebe o código `A3F7-001`, `A3F7-002`, ..., `A3F7-050`.

Este código aparece:
1. No canto superior direito do cabeçalho de cada prova (visível ao aluno)
2. No cabeçalho de cada bloco de gabarito

---

## Layout do PDF

### PDF único + gabarito agrupado no final
```
Prova A3F7-001 (páginas 1–4)
Prova A3F7-002 (páginas 5–8)
...
Prova A3F7-050 (páginas N–M)
══════════════════════════════
           GABARITOS
══════════════════════════════
A3F7-001:  1-B  2-D  3-A ...
A3F7-002:  1-A  2-C  3-E ...
...
```

### PDF único + gabarito após cada prova
```
Prova A3F7-001 → Gabarito A3F7-001
Prova A3F7-002 → Gabarito A3F7-002
...
```

### ZIP (sempre: um PDF por prova)
- `APOS_CADA_PROVA`: cada ZIP entry = [prova + gabarito]
- `AGRUPADO_NO_FINAL`: ZIP contém N arquivos de prova + 1 arquivo `gabarito.pdf`

---

## Cabeçalho de cada Prova no PDF

```
┌──────────────────────────────────────────────────────────┐
│ ESCOLA: Escola Estadual João da Silva                    │
│ PROFESSOR(A): Prof. Maria Santos    DATA: 15/06/2026     │
│                                                          │
│         AVALIAÇÃO DE MATEMÁTICA          Cód.: A3F7-001  │
└──────────────────────────────────────────────────────────┘
NOME: ________________________________  TURMA: ___  NOTA: ___
```

---

## Estrutura de Arquivos Criados

```
src/main/java/
├── modelo/prova/
│   ├── PlanoProva.java            ✅
│   ├── NomeDocumento.java         ✅
│   ├── FormatoProva.java          ✅
│   ├── TipoGabarito.java          ✅
│   ├── PosicaoGabarito.java       ✅
│   ├── FormatoSaida.java          ✅
│   ├── StatusPedidoProva.java     ✅
│   ├── ConfigPedidoProva.java     ✅
│   ├── PedidoProva.java           ✅
│   └── ItemPedidoProva.java       ✅
├── dao/prova/
│   ├── ConfigPedidoProvaDAO.java  ✅
│   ├── PedidoProvaDAO.java        ✅
│   └── ItemPedidoProvaDAO.java    ✅
├── service/prova/
│   ├── GeradorProvaPdfService.java     ✅
│   ├── MontadorPedidoProvaService.java ⬜ (próximo)
│   └── CleanupPedidoProvaService.java  ⬜
├── bean/prova/
│   ├── ConfigPedidoProvaBean.java ⬜
│   └── PedidoProvaBean.java       ⬜
└── modelo/usuario/Usuario.java    ✅ (campo planoProva adicionado)

src/main/webapp/
├── prova/
│   ├── form.xhtml      ⬜ (formulário de solicitação)
│   └── list.xhtml      ⬜ (histórico de pedidos)
└── administracao/configuracao/pedido-prova/
    └── config.xhtml    ⬜
```

---

## Fases de Implementação

| # | Tarefa                                           | Status  |
|---|--------------------------------------------------|---------|
| 1 | Enums + Entidades + DAOs                         | ✅ Feito |
| 2 | Campo `planoProva` em `Usuario`                  | ✅ Feito |
| 3 | `GeradorProvaPdfService` (LaTeX de uma prova)    | ✅ Feito |
| 4 | `GeradorGabaritoPdfService` (gabarito separado)  | ✅ Feito |
| 5 | `MontadorPedidoProvaService` (async, ZIP, progresso) | ✅ Feito |
| 6 | `CleanupPedidoProvaService` (expiração de PDFs)  | ✅ Feito |
| 7 | `ConfigPedidoProvaBean` + view admin             | ✅ Feito |
| 8 | `PedidoProvaBean` + form + progresso + histórico | ✅ Feito |
| 9 | Menu + controle de acesso (`PageFilter`)         | ✅ Feito |

---

## Validações de Negócio

- Professor precisa ter `planoProva` atribuído e `validadePlano >= hoje`
- Total de exercícios por prova: soma dos itens ≤ `ConfigPedidoProva.maxExerciciosPorProva`
- Provas por solicitação: `quantidadeProvas` ≤ `ConfigPedidoProva.maxProvasPorSolicitacao`
- Cota mensal: soma de `quantidadeProvas` no mês corrente + nova solicitação ≤ `PlanoProva.limiteProvaMensal`

---

## Navegação

- Professor: menu lateral abaixo de "Questões" → **"Confecção de Provas"** → `/prova/form.xhtml`
- Admin: "Administração → Configuração" → **"Config. de Provas"** → `/administracao/configuracao/pedido-prova/config.xhtml`
