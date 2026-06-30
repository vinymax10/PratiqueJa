# PratiqueJa — Guia para Claude

## Banco de dados (desenvolvimento local)

| Campo      | Valor                                                              |
|------------|--------------------------------------------------------------------|
| URL        | jdbc:mysql://localhost:3306/pratiqueja?serverTimezone=America/Sao_Paulo |
| Usuário    | root                                                               |
| Senha      | tiquinho10                                                         |

Linha de comando MySQL: `mysql -u root -ptiquinho10 pratiqueja`

## Estrutura do projeto

JSF/PrimeFaces + JPA (Hibernate) + Jakarta EE, deployado no WildFly 35.
Build: `mvn -q -o clean compile` (offline após deps baixadas).

## Geradores de exercício (`matematica.*`)

- Base abstrata: `matematica.GeradorExercicio` (template method)
- Ponto de entrada: `matematica.ExercicioFactory.gerar(String classe, int indice)`
- 55 assuntos × 3 níveis = 165 dispatchers; cada dispatcher delega para folhas em `nivelNpackage/`
- Nome do dispatcher armazenado em `ExercicioPadrao.nome`; `getClasse()` monta o FQCN

## Auditoria de geradores

Scripts em `revisao_geradores/` (pasta no .gitignore — só local):
- `audit_db.sql` — 5 verificações estruturais contra o banco
- `resultado_geradores.tsv` — gerado por `AuditorGeradores.java`

Para rodar o SQL: `mysql -u root -ptiquinho10 pratiqueja < revisao_geradores/audit_db.sql`
Para rodar o auditor Java: `mvn -q compile exec:java -Dexec.mainClass=auditoria.AuditorGeradores`
