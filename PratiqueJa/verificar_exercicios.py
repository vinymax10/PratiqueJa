"""
Script para verificar consistência matemática nos exercícios gerados.
Procura por discrepâncias entre o enunciado e a resposta/resolução.
"""
import csv
import re
import os
import sys
from pathlib import Path

EXERCICIOS_DIR = Path(r"C:\Users\maximovrm\git\PratiqueJa\PratiqueJa\exercicios")

# Configurar saída para UTF-8
sys.stdout.reconfigure(encoding='utf-8')

erros_encontrados = []


def reportar(arquivo, linha, motivo, enunciado, resposta):
    erros_encontrados.append({
        'arquivo': arquivo,
        'linha': linha,
        'motivo': motivo,
        'enunciado': enunciado[:200],
        'resposta': resposta[:200],
    })


def parse_arquivo(caminho):
    """Lê o CSV e retorna lista de (nivel, enunciado, resposta, resolucao)."""
    linhas = []
    with open(caminho, 'r', encoding='utf-8') as f:
        reader = csv.reader(f)
        header = next(reader, None)
        for i, row in enumerate(reader, start=2):
            if len(row) >= 4:
                linhas.append((i, row[0], row[1], row[2], row[3]))
    return linhas


# ===================== VERIFICADOR: INEQUAÇÕES 2º GRAU =====================
def verificar_inequacoes_segundograu(caminho):
    """Verifica inequações ax^2+bx+c <,>,<=,>= 0 com a=1."""
    linhas = parse_arquivo(caminho)
    arq_nome = caminho.name
    for num_linha, nivel, enun, resp, resol in linhas:
        # Captura: x^2 [+-] B x [+-] C [op] 0
        # Ou: x^2 [+-] C [op] 0  (incompleta)
        m = re.search(r'x\^2\s*([+-]?\s*\d*)\s*x\s*([+-]\s*\d+)?\s*(<|>|<=|>=|\\leq|\\geq)\s*0', enun)
        m_incompleta = re.search(r'x\^2\s*([+-]\s*\d+)\s*(<|>|<=|>=|\\leq|\\geq)\s*0', enun)
        if m:
            b_str = m.group(1).replace(' ', '')
            c_str = m.group(2)
            op = m.group(3)
            if b_str in ('', '+'):
                b = 1
            elif b_str == '-':
                b = -1
            else:
                b = int(b_str)
            c = int(c_str.replace(' ', '')) if c_str else 0
            delta = b*b - 4*c
            if delta < 0:
                continue
            sqd = delta ** 0.5
            if abs(sqd - round(sqd)) > 1e-9:
                continue
            sqd = int(round(sqd))
            x1 = (-b - sqd) / 2
            x2 = (-b + sqd) / 2
            # Esperamos inteiros
            if x1 != int(x1) or x2 != int(x2):
                continue
            x1, x2 = int(x1), int(x2)
            if x1 > x2:
                x1, x2 = x2, x1
            # Determina intervalo esperado conforme op
            op_norm = op.replace('\\leq', '<=').replace('\\geq', '>=')
            if op_norm == '<':
                esperado = f"({x1},\\,{x2})"
                tipo = 'aberto_entre'
            elif op_norm == '<=':
                esperado = f"[{x1},\\,{x2}]"
                tipo = 'fechado_entre'
            elif op_norm == '>':
                esperado = f"(-\\infty,\\,{x1}) \\cup ({x2},\\,+\\infty)"
                tipo = 'aberto_fora'
            elif op_norm == '>=':
                esperado = f"(-\\infty,\\,{x1}] \\cup [{x2},\\,+\\infty)"
                tipo = 'fechado_fora'
            else:
                continue
            # Verifica se a resposta contém o intervalo esperado
            if esperado not in resp:
                reportar(arq_nome, num_linha,
                         f"Resposta divergente. Esperado: {esperado}",
                         enun, resp)
                continue
            # Verifica delta na resolução
            m_delta = re.search(r'\\Delta\s*=\s*(-?\d+)\s*[+-]?\s*\d*\s*=\s*(-?\d+)', resol)
            # Verifica raízes na resolução
            m_x1 = re.search(r'x_1\s*=\s*\\dfrac\{[^}]+\}\{2\}\s*=\s*(-?\d+)', resol)
            m_x2 = re.search(r'x_2\s*=\s*\\dfrac\{[^}]+\}\{2\}\s*=\s*(-?\d+)', resol)
            if m_x1 and m_x2:
                r1 = int(m_x1.group(1))
                r2 = int(m_x2.group(1))
                if {r1, r2} != {x1, x2}:
                    reportar(arq_nome, num_linha,
                             f"Raízes na resolução {{{r1},{r2}}} != esperadas {{{x1},{x2}}}",
                             enun, resol)


# ===================== VERIFICADOR: EQUAÇÕES 2º GRAU =====================
def verificar_equacao_segundograu(caminho):
    """Verifica equações ax^2+bx+c = 0."""
    linhas = parse_arquivo(caminho)
    arq_nome = caminho.name
    for num_linha, nivel, enun, resp, resol in linhas:
        m = re.search(r'(\d*)\s*x\^2\s*([+-]\s*\d*)\s*x\s*([+-]\s*\d+)?\s*=\s*0', enun)
        if not m:
            continue
        a_str = m.group(1).strip()
        a = int(a_str) if a_str else 1
        b_str = m.group(2).replace(' ', '')
        if b_str in ('+', ''):
            b = 1
        elif b_str == '-':
            b = -1
        else:
            b = int(b_str)
        c_str = m.group(3)
        c = int(c_str.replace(' ', '')) if c_str else 0
        delta = b*b - 4*a*c
        if delta < 0:
            continue
        sqd = delta ** 0.5
        if abs(sqd - round(sqd)) > 1e-9:
            continue
        sqd = int(round(sqd))
        # Verifica raízes na resolução
        m_x1 = re.search(r'x_1\s*=\s*[^=]*=\s*(-?\d+(?:/\d+)?)\b', resol)
        m_x2 = re.search(r'x_2\s*=\s*[^=]*=\s*(-?\d+(?:/\d+)?)\b', resol)
        if m_x1 and m_x2:
            try:
                from fractions import Fraction
                r1 = Fraction(m_x1.group(1))
                r2 = Fraction(m_x2.group(1))
            except Exception:
                continue
            # Verifica se r1 e r2 satisfazem a equação
            for r in (r1, r2):
                val = a * r * r + b * r + c
                if val != 0:
                    reportar(arq_nome, num_linha,
                             f"Raiz x={r} não satisfaz {a}x²+{b}x+{c}=0 (resulta em {val})",
                             enun, resol)
                    break


# ===================== VERIFICADOR: ADIÇÃO NATURAL =====================
def verificar_adicao_natural(caminho):
    """Verifica somas simples a + b = ?"""
    linhas = parse_arquivo(caminho)
    arq_nome = caminho.name
    for num_linha, nivel, enun, resp, resol in linhas:
        # Procura padrão "A + B + ... ="
        m = re.search(r'(\d+(?:\s*\+\s*\d+)+)', enun)
        if not m:
            continue
        nums = [int(x) for x in re.findall(r'\d+', m.group(1))]
        if len(nums) < 2:
            continue
        soma = sum(nums)
        # Verifica se a resposta contém a soma correta
        nums_resp = re.findall(r'\d+', resp)
        if not nums_resp:
            continue
        # A resposta é tipicamente o número final
        # Procura o último número grande na resposta
        if str(soma) not in resp:
            reportar(arq_nome, num_linha,
                     f"Soma esperada {soma} não encontrada na resposta",
                     enun, resp)


# ===================== VERIFICADOR: SUBTRAÇÃO NATURAL =====================
def verificar_subtracao_natural(caminho):
    """Verifica subtrações."""
    linhas = parse_arquivo(caminho)
    arq_nome = caminho.name
    for num_linha, nivel, enun, resp, resol in linhas:
        m = re.search(r'(\d+)\s*-\s*(\d+)\s*=', enun)
        if not m:
            continue
        a = int(m.group(1))
        b = int(m.group(2))
        if a < b:
            continue
        dif = a - b
        if str(dif) not in resp:
            reportar(arq_nome, num_linha,
                     f"Diferença esperada {dif} não encontrada",
                     enun, resp)


# ===================== VERIFICADOR: MULTIPLICAÇÃO NATURAL =====================
def verificar_multiplicacao_natural(caminho):
    linhas = parse_arquivo(caminho)
    arq_nome = caminho.name
    for num_linha, nivel, enun, resp, resol in linhas:
        m = re.search(r'(\d+)\s*[×x\\times*·]\s*(\d+)\s*=', enun)
        if not m:
            continue
        a = int(m.group(1))
        b = int(m.group(2))
        prod = a * b
        if str(prod) not in resp:
            reportar(arq_nome, num_linha,
                     f"Produto esperado {prod} não encontrado",
                     enun, resp)


# ===================== VERIFICADOR: DIVISÃO NATURAL =====================
def verificar_divisao_natural(caminho):
    linhas = parse_arquivo(caminho)
    arq_nome = caminho.name
    for num_linha, nivel, enun, resp, resol in linhas:
        m = re.search(r'(\d+)\s*[÷/]\s*(\d+)', enun)
        if not m:
            continue
        a = int(m.group(1))
        b = int(m.group(2))
        if b == 0:
            continue
        q = a // b
        r = a % b
        if str(q) not in resp:
            reportar(arq_nome, num_linha,
                     f"Quociente esperado {q} não encontrado",
                     enun, resp)


# ===================== VERIFICADOR: SISTEMA DE EQUAÇÕES 2x2 =====================
def verificar_sistemaequacoes(caminho):
    """Verifica sistemas lineares 2x2."""
    linhas = parse_arquivo(caminho)
    arq_nome = caminho.name
    for num_linha, nivel, enun, resp, resol in linhas:
        # Procura x = num e y = num na resposta
        m_resp = re.search(r'x\s*=\s*(-?\d+)[^x]+y\s*=\s*(-?\d+)', resp)
        if not m_resp:
            continue
        x_val = int(m_resp.group(1))
        y_val = int(m_resp.group(2))
        # Tenta achar as duas equações no enunciado
        eqs = re.findall(r'([+-]?\s*\d*)\s*x\s*([+-]\s*\d*)\s*y\s*=\s*(-?\d+)', enun)
        if len(eqs) >= 2:
            for a_s, b_s, c_s in eqs[:2]:
                a_s = a_s.replace(' ', '')
                b_s = b_s.replace(' ', '')
                if a_s in ('', '+'):
                    a = 1
                elif a_s == '-':
                    a = -1
                else:
                    a = int(a_s)
                if b_s in ('', '+'):
                    b = 1
                elif b_s == '-':
                    b = -1
                else:
                    b = int(b_s)
                c = int(c_s)
                if a * x_val + b * y_val != c:
                    reportar(arq_nome, num_linha,
                             f"x={x_val}, y={y_val} não satisfaz {a}x+{b}y={c} (=> {a*x_val+b*y_val})",
                             enun, resp)
                    break


# ===================== VERIFICADOR: PA =====================
def verificar_pa(caminho):
    """Verifica progressões aritméticas."""
    linhas = parse_arquivo(caminho)
    arq_nome = caminho.name
    # Padrão básico: pergunta valor de a_n dado a_1 e r
    # Difícil de verificar sem parser específico; pula


# ===================== VERIFICADOR: LOGARITMO =====================
def verificar_logaritmo(caminho):
    """Verifica logaritmos do tipo log_a(b) = ?"""
    linhas = parse_arquivo(caminho)
    arq_nome = caminho.name
    for num_linha, nivel, enun, resp, resol in linhas:
        m = re.search(r'\\log_\{?(\d+)\}?\s*\(?(\d+)\)?', enun)
        if not m:
            continue
        base = int(m.group(1))
        arg = int(m.group(2))
        # Calcula expoente
        if base <= 1 or arg <= 0:
            continue
        import math
        try:
            n = math.log(arg) / math.log(base)
        except Exception:
            continue
        if abs(n - round(n)) > 1e-9:
            continue
        n = int(round(n))
        if base ** n != arg:
            continue
        # Verifica resposta
        m_resp = re.search(r'=\s*(-?\d+)', resp)
        if m_resp:
            r_val = int(m_resp.group(1))
            if r_val != n:
                reportar(arq_nome, num_linha,
                         f"log_{base}({arg})={n}, mas resposta diz {r_val}",
                         enun, resp)


# ===================== DESPACHO =====================
VERIFICADORES = {
    'inequacoessegundograu.txt': verificar_inequacoes_segundograu,
    'equacaosegundograu.txt': verificar_equacao_segundograu,
    'adicaonatural.txt': verificar_adicao_natural,
    'subtracaonatural.txt': verificar_subtracao_natural,
    'multiplicacaonatural.txt': verificar_multiplicacao_natural,
    'divisaonatural.txt': verificar_divisao_natural,
    'sistemaequacoes.txt': verificar_sistemaequacoes,
    'logaritmo.txt': verificar_logaritmo,
}


def main():
    for nome, verificador in VERIFICADORES.items():
        caminho = EXERCICIOS_DIR / nome
        if not caminho.exists():
            print(f"AUSENTE: {nome}")
            continue
        try:
            verificador(caminho)
        except Exception as e:
            print(f"ERRO ao verificar {nome}: {e}")

    if not erros_encontrados:
        print("Nenhum erro detectado pelos verificadores básicos.")
    else:
        print(f"\n=== {len(erros_encontrados)} ERROS ENCONTRADOS ===\n")
        for e in erros_encontrados:
            print(f"[{e['arquivo']}:{e['linha']}] {e['motivo']}")
            print(f"  Enunciado: {e['enunciado']}")
            print(f"  Resposta:  {e['resposta'][:150]}")
            print()


if __name__ == '__main__':
    main()
