#!/usr/bin/env python3
"""Gera lote_NNN.sql a partir de piloto.tsv (linhas tipo_conteudo=latex_candidato).

Para cada parágrafo candidato:
  - desloca ordem >= ordem_da_imagem em (n) unidades (n = qtd de novos paragrafos, via <<P>>)
  - insere os n novos paragrafos de texto nas ordens liberadas, antes da imagem
  - usa UNHEX('...') para o texto (evita MySQL interpretar \\ como escape de string)
  - novo id vem de uma faixa alta isolada, sequencial, a partir de --start-id

IMPORTANTE: quando a MESMA questao tem mais de um paragrafo-imagem convertido no mesmo lote, os itens
dessa questao sao processados em ordem DECRESCENTE de `ordem` (maior ordem primeiro). Se fosse crescente,
o UPDATE do primeiro item (ordem menor) empurraria a posicao dos itens seguintes (ordem maior) que ainda
usariam o valor de ordem ORIGINAL (desatualizado) capturado antes do deslocamento, resultando no novo
paragrafo inserido no lugar errado (testado e confirmado com bug real na questao 8271 do lote_005).
Processando do maior ordem pro menor, cada UPDATE so afeta ordens que ainda nao foram tocadas.
"""
import sys
import argparse
from collections import defaultdict

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("tsv_path")
    ap.add_argument("--start-id", type=int, required=True)
    ap.add_argument("--out", required=True)
    ap.add_argument("--ids-out", required=True, help="arquivo p/ registrar paragrafo_id -> novos ids gerados (auditoria)")
    args = ap.parse_args()

    with open(args.tsv_path, encoding="utf-8") as f:
        lines = f.read().split("\n")
    header = lines[0].split("\t")
    idx = {name: i for i, name in enumerate(header)}

    candidatos = []
    for line in lines[1:]:
        if not line.strip():
            continue
        row = line.split("\t")
        while len(row) < len(header):
            row.append("")
        if row[idx["status"]] != "classificado" or row[idx["tipo_conteudo"]] != "latex_candidato":
            continue
        candidatos.append({
            "pid_imagem": row[idx["paragrafo_id"]],
            "questao_id": row[idx["questao_id"]],
            "ordem_imagem": int(row[idx["ordem"]]),
            "latex": row[idx["latex_proposto"]],
        })

    por_questao = defaultdict(list)
    for c in candidatos:
        por_questao[c["questao_id"]].append(c)
    # mesma ordem de questoes em que apareceram no arquivo; dentro de cada questao, maior ordem primeiro
    questoes_ordenadas = list(dict.fromkeys(c["questao_id"] for c in candidatos))
    for qid in questoes_ordenadas:
        por_questao[qid].sort(key=lambda c: c["ordem_imagem"], reverse=True)

    next_id = args.start_id
    sql_lines = []
    audit_lines = ["paragrafo_id_imagem\tquestao_id\tordem_imagem_original\tqtd_novos_paragrafos\tnovos_ids"]
    total_items = 0
    total_paragraphs = 0

    for questao_id in questoes_ordenadas:
        for c in por_questao[questao_id]:
            pid_imagem = c["pid_imagem"]
            ordem_imagem = c["ordem_imagem"]
            latex = c["latex"]
            paragrafos = latex.split("<<P>>")

            n = len(paragrafos)
            sql_lines.append(f"-- questao_id={questao_id} paragrafo_imagem_id={pid_imagem} ordem_original={ordem_imagem} ({n} paragrafo(s) novo(s))")
            sql_lines.append(
                f"UPDATE paragrafo SET ordem = ordem + {n} WHERE questao_id = {questao_id} AND ordem >= {ordem_imagem};"
            )
            novos_ids = []
            for i, texto in enumerate(paragrafos):
                texto = texto.strip()
                new_id = next_id
                next_id += 1
                novos_ids.append(str(new_id))
                hex_texto = texto.encode("utf-8").hex()
                nova_ordem = ordem_imagem + i
                sql_lines.append(
                    f"INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) "
                    f"VALUES ({new_id}, {nova_ordem}, UNHEX('{hex_texto}'), {questao_id}, NULL);"
                )
            sql_lines.append("")
            audit_lines.append(f"{pid_imagem}\t{questao_id}\t{ordem_imagem}\t{n}\t{','.join(novos_ids)}")
            total_items += 1
            total_paragraphs += n

    with open(args.out, "w", encoding="utf-8", newline="\n") as f:
        f.write("\n".join(sql_lines) + "\n")
    with open(args.ids_out, "w", encoding="utf-8", newline="\n") as f:
        f.write("\n".join(audit_lines) + "\n")

    print(f"{total_items} itens, {total_paragraphs} paragrafos novos gerados.")
    print(f"Faixa de id usada: {args.start_id} .. {next_id - 1}")
    print(f"Proximo id livre: {next_id}")
    print(f"SQL: {args.out}")
    print(f"Auditoria: {args.ids_out}")


if __name__ == "__main__":
    main()
