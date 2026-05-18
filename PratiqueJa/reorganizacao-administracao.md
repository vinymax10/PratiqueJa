# Proposta de Reorganização — pasta `administracao`

## Estrutura de pastas proposta

```
administracao/
│
├── conteudo/
│   ├── assunto-curso/       (vem de: administracao/assunto-curso/)
│   ├── ebook/               (vem de: administracao/ebook/)
│   ├── exercicio/           (vem de: administracao/exercicio/)
│   ├── exercicio-padrao/    (vem de: administracao/exercicio-padrao/)
│   ├── teste-padrao/        (vem de: administracao/teste-padrao/)
│   ├── questao/
│   │   ├── gerenciar/             (vem de: administracao/questao/)
│   │   ├── orgao/               (vem de: gestaoQuestao/orgao/)
│   │   ├── banca/               (vem de: gestaoQuestao/banca/)
│   │   ├── ano/                 (vem de: gestaoQuestao/ano/)
│   │   ├── assunto/             (vem de: gestaoQuestao/assunto/)
│   │   └── disciplina/          (vem de: gestaoQuestao/disciplina/)
│
├── atividades/
│   ├── resultado-exercicio/ (vem de: administracao/resultado-exercicio/)
│   └── resultado-teste/     (vem de: administracao/resultado-teste/)
│
├── usuarios/
│   ├── usuario/             (vem de: administracao/usuario/)
│   ├── contato/             (vem de: administracao/contato/)
│   ├── pagamento/           (vem de: administracao/pagamento/)
│   ├── acesso/              (vem de: administracao/acesso/)
│   ├── controle-acesso/     (vem de: administracao/controle-acesso/)
│
├── auditoria/           (vem de: administracao/auditoria/)
│
├── marketing/
│   ├── hashtag/             (vem de: administracao/hashtag/)
│   ├── cta/                 (vem de: administracao/cta/)
│   └── convite-grupo/       (vem de: administracao/convite-grupo/)
│
├── configuracao/
│   ├── config-latex/        (vem de: administracao/config-latex/)
│   ├── cleanup/             (vem de: administracao/cleanup/)
│   ├── spam/                (vem de: administracao/spam/)
│   ├── config-controle-acesso/ (vem de: administracao/config-controle-acesso/)
│   └── aviso.xhtml          (vem de: administracao/aviso.xhtml)
│   
└── email/               (vem de: administracao/email/)
    
```

---

## Menu lateral — seção Administração

```
ADMINISTRAÇÃO

  Assuntos

▼ Exercícios
    Exercícios
    Exercício Padrão
    Resultados

▼ Testes
    Testes
    Teste Padrão
    Resultados

▼ Questões
    Questões
    Órgão
    Banca
    Ano
    Assunto
    Disciplina

▼ Usuários
    Usuários
    Logados
    Turma
    Contatos
    Pagamento
    Acessos
    Controle de Acesso

▼ Instagram
    Hashtag
    CTA's
    Convite ao Grupo

▼ Configurações
    Email
    Config Latex
    Config Cleanup
    Config Spam
    Config Controle de Acesso
```

---

## Mapeamento de URLs antigas → novas

### conteudo/
| URL antiga | URL nova |
|---|---|
| /administracao/assunto-curso/list.xhtml | /administracao/conteudo/assunto-curso/list.xhtml |
| /administracao/assunto-curso/form.xhtml | /administracao/conteudo/assunto-curso/form.xhtml |
| /administracao/ebook/download.xhtml | /administracao/conteudo/ebook/download.xhtml |
| /administracao/exercicio/list.xhtml | /administracao/conteudo/exercicio/list.xhtml |
| /administracao/exercicio/form.xhtml | /administracao/conteudo/exercicio/form.xhtml |
| /administracao/exercicio-padrao/list.xhtml | /administracao/conteudo/exercicio-padrao/list.xhtml |
| /administracao/exercicio-padrao/form.xhtml | /administracao/conteudo/exercicio-padrao/form.xhtml |
| /administracao/questao/list.xhtml | /administracao/conteudo/questao/gerenciar/list.xhtml |
| /administracao/questao/form.xhtml | /administracao/conteudo/questao/gerenciar/form.xhtml |
| /administracao/teste-padrao/list.xhtml | /administracao/conteudo/teste-padrao/list.xhtml |
| /administracao/teste-padrao/form.xhtml | /administracao/conteudo/teste-padrao/form.xhtml |
| /gestaoQuestao/orgao/list.xhtml | /administracao/conteudo/questao/orgao/list.xhtml |
| /gestaoQuestao/banca/list.xhtml | /administracao/conteudo/questao/banca/list.xhtml |
| /gestaoQuestao/ano/list.xhtml | /administracao/conteudo/questao/ano/list.xhtml |
| /gestaoQuestao/assunto/list.xhtml | /administracao/conteudo/questao/assunto/list.xhtml |
| /gestaoQuestao/disciplina/list.xhtml | /administracao/conteudo/questao/disciplina/list.xhtml |

### atividades/
| URL antiga | URL nova |
|---|---|
| /administracao/resultado-exercicio/list.xhtml | /administracao/atividades/resultado-exercicio/list.xhtml |
| /administracao/resultado-teste/list.xhtml | /administracao/atividades/resultado-teste/list.xhtml |

### usuarios/
| URL antiga | URL nova |
|---|---|
| /administracao/usuario/list.xhtml | /administracao/usuarios/usuario/list.xhtml |
| /administracao/usuario/form.xhtml | /administracao/usuarios/usuario/form.xhtml |
| /administracao/contato/list.xhtml | /administracao/usuarios/contato/list.xhtml |
| /administracao/contato/form.xhtml | /administracao/usuarios/contato/form.xhtml |
| /administracao/pagamento/list.xhtml | /administracao/usuarios/pagamento/list.xhtml |
| /administracao/pagamento/form.xhtml | /administracao/usuarios/pagamento/form.xhtml |
| /administracao/acesso/list.xhtml | /administracao/usuarios/acesso/list.xhtml |
| /administracao/controle-acesso/list.xhtml | /administracao/usuarios/controle-acesso/list.xhtml |

### auditoria/
| URL antiga | URL nova |
|---|---|
| /administracao/auditoria/list.xhtml | /administracao/auditoria/list.xhtml |
| /administracao/auditoria/filtro.xhtml | /administracao/auditoria/filtro.xhtml |
| /administracao/auditoria/exportar.xhtml | /administracao/auditoria/exportar.xhtml |

### marketing/
| URL antiga | URL nova |
|---|---|
| /administracao/hashtag/list.xhtml | /administracao/marketing/hashtag/list.xhtml |
| /administracao/cta/list.xhtml | /administracao/marketing/cta/list.xhtml |
| /administracao/convite-grupo/list.xhtml | /administracao/marketing/convite-grupo/list.xhtml |

### email/
| URL antiga | URL nova |
|---|---|
| /administracao/email/list.xhtml | /administracao/email/list.xhtml |
| /administracao/email/filtro.xhtml | /administracao/email/filtro.xhtml |
| /administracao/email/cadastro.xhtml | /administracao/email/cadastro.xhtml |

### configuracao/
| URL antiga | URL nova |
|---|---|
| /administracao/config-latex/config.xhtml | /administracao/configuracao/config-latex/config.xhtml |
| /administracao/cleanup/config.xhtml | /administracao/configuracao/cleanup/config.xhtml |
| /administracao/spam/config.xhtml | /administracao/configuracao/spam/config.xhtml |
| /administracao/config-controle-acesso/config.xhtml | /administracao/configuracao/config-controle-acesso/config.xhtml |
| /administracao/aviso.xhtml | /administracao/configuracao/aviso.xhtml |

---

## O que precisa ser atualizado após mover os arquivos

1. **URLs nos templates XHTML** — `url=` em todos os `<p:menuitem>` e `<a href=>` do template.xhtml
2. **URLs nos backing beans Java** — qualquer `NavigationCase`, redirect ou `return "..."` que referencie essas views
3. **Faces config** — se houver navegação explícita em `faces-config.xml`
4. **Includes entre views** — `<ui:include>` dentro das próprias views de administração que referenciem outras views
