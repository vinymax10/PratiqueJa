#!/usr/bin/env python3
"""Decode the hex-dumped image blobs into individual files under imagens/."""
import os
import sys

BASE = os.path.dirname(os.path.abspath(__file__))
SRC = os.path.join(BASE, "_raw_hex_dump.tsv")
OUT_DIR = os.path.join(BASE, "imagens")

os.makedirs(OUT_DIR, exist_ok=True)

count = 0
errors = 0
with open(SRC, "r", encoding="utf-8", errors="replace") as f:
    for line in f:
        line = line.rstrip("\n")
        if not line:
            continue
        parts = line.split("\t")
        if len(parts) != 3:
            errors += 1
            print(f"linha malformada (campos={len(parts)}): {line[:80]}", file=sys.stderr)
            continue
        pid, endimagem, hexdata = parts
        if not endimagem or "." not in endimagem:
            endimagem = f"{pid}.png"
        safe_name = endimagem.replace("/", "_").replace("\\", "_")
        fname = f"{pid}_{safe_name}"
        path = os.path.join(OUT_DIR, fname)
        try:
            data = bytes.fromhex(hexdata)
        except ValueError as e:
            errors += 1
            print(f"hex invalido p/ paragrafo {pid}: {e}", file=sys.stderr)
            continue
        with open(path, "wb") as out:
            out.write(data)
        count += 1

print(f"Exportadas {count} imagens ({errors} erros) em {OUT_DIR}")
