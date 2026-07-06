#!/usr/bin/env python3
texto = (
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
print(texto)
print("---HEX---")
print(texto.encode("utf-8").hex())

with open("_fix_89673_sql.sql", "w", encoding="utf-8", newline="\n") as f:
    f.write(f"UPDATE paragrafo SET texto = UNHEX('{texto.encode('utf-8').hex()}') WHERE id = 900000017;\n")
