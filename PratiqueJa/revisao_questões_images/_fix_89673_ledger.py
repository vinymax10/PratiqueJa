#!/usr/bin/env python3
NOVO_LATEX = (
    r"\(\begin{array}{|l|c|c|c|c|}\hline"
    r"\textbf{Fantasias} & \textbf{V (adulto)} & \textbf{Q (adulto)} & \textbf{V (infantil)} & \textbf{Q (infantil)} \\"
    r"\hline"
    r"\text{Baiana} & \text{R\$ 180,00} & 10 & \text{R\$ 100,00} & 5 \\"
    r"\text{Cigana} & \text{R\$ 105,00} & 6 & \text{R\$ 85,00} & 4 \\"
    r"\text{Colombina} & \text{R\$ 120,00} & 5 & \text{R\$ 70,00} & 2 \\"
    r"\text{Marinheiro} & \text{R\$ 95,00} & 2 & \text{R\$ 80,00} & X \\"
    r"\text{Palhaço} & \text{R\$ 110,00} & Y & \text{R\$ 90,00} & 5 \\"
    r"\hline\end{array}\)"
)
NOVA_OBS = (
    "CORRIGIDO (paragrafo novo id=900000017): versao original usava \\multicolumn, que NAO e suportado "
    "pelo MathJax v2 (feature request em aberto desde 2015, github mathjax/MathJax#1102) - reescrito com "
    "cabecalhos diretos (V/Q adulto, V/Q infantil) sem merge de celulas."
)

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
        if row[idx["paragrafo_id"]] == "89673":
            row[idx["latex_proposto"]] = NOVO_LATEX
            row[idx["obs"]] = NOVA_OBS
            changed += 1
        out.append("\t".join(row))
    with open(path, "w", encoding="utf-8", newline="\n") as f:
        f.write("\n".join(out))
    print(f"{path}: {changed} linha(s) corrigida(s)")
