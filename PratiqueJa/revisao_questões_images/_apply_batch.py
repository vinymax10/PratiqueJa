#!/usr/bin/env python3
"""Apply a _batchNNN_updates.tsv onto piloto.tsv and manifest.tsv (status/tipo_conteudo/latex_proposto/obs).
Plain tab-split (no csv module) since fields never contain literal tab chars, only backslashes/%/quotes."""
import sys


def load_updates(path):
    updates = {}
    with open(path, encoding="utf-8") as f:
        lines = f.read().split("\n")
    for line in lines[1:]:
        if not line.strip():
            continue
        parts = line.split("\t")
        pid = parts[0]
        tipo = parts[1] if len(parts) > 1 else ""
        latex = parts[2] if len(parts) > 2 else ""
        obs = parts[3] if len(parts) > 3 else ""
        updates[pid] = (tipo, latex, obs)
    return updates


def apply_to_file(path, updates):
    with open(path, encoding="utf-8") as f:
        lines = f.read().split("\n")
    header = lines[0].split("\t")
    idx = {name: i for i, name in enumerate(header)}
    changed = 0
    out_lines = [lines[0]]
    for line in lines[1:]:
        if not line.strip():
            out_lines.append(line)
            continue
        row = line.split("\t")
        while len(row) < len(header):
            row.append("")
        pid = row[idx["paragrafo_id"]]
        if pid in updates:
            tipo, latex, obs = updates[pid]
            row[idx["status"]] = "classificado"
            row[idx["tipo_conteudo"]] = tipo
            row[idx["latex_proposto"]] = latex
            row[idx["obs"]] = obs
            changed += 1
        out_lines.append("\t".join(row))
    with open(path, "w", encoding="utf-8", newline="\n") as f:
        f.write("\n".join(out_lines))
    print(f"{path}: {changed} linhas atualizadas")


if __name__ == "__main__":
    batch_file = sys.argv[1]
    updates = load_updates(batch_file)
    print(f"{len(updates)} atualizacoes carregadas de {batch_file}")
    apply_to_file("piloto.tsv", updates)
    apply_to_file("manifest.tsv", updates)
