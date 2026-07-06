#!/usr/bin/env python3
"""Marca status=inserido para as linhas latex_candidato do piloto ja aplicadas (lote_001)."""
for path in ("piloto.tsv", "manifest.tsv"):
    with open(path, encoding="utf-8") as f:
        lines = f.read().split("\n")
    header = lines[0].split("\t")
    idx = {name: i for i, name in enumerate(header)}
    changed = 0
    out = [lines[0]]
    for line in lines[1:]:
        if not line.strip():
            out.append(line)
            continue
        row = line.split("\t")
        while len(row) < len(header):
            row.append("")
        if row[idx["status"]] == "classificado" and row[idx["tipo_conteudo"]] == "latex_candidato":
            row[idx["status"]] = "inserido"
            changed += 1
        out.append("\t".join(row))
    with open(path, "w", encoding="utf-8", newline="\n") as f:
        f.write("\n".join(out))
    print(f"{path}: {changed} linhas marcadas como inserido")
