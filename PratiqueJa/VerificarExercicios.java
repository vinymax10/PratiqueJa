import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class VerificarExercicios {
    static final Path DIR = Paths.get("C:\\Users\\maximovrm\\git\\PratiqueJa\\PratiqueJa\\exercicios");
    static List<String> erros = new ArrayList<>();

    static class Linha {
        int num;
        String nivel, enunciado, resposta, resolucao;
        Linha(int n, String nv, String e, String r, String s) {
            num = n; nivel = nv; enunciado = e; resposta = r; resolucao = s;
        }
    }

    static void reportar(String arq, int linha, String motivo, String enun, String resp) {
        String e = enun.length() > 200 ? enun.substring(0, 200) : enun;
        String r = resp.length() > 200 ? resp.substring(0, 200) : resp;
        erros.add("[" + arq + ":" + linha + "] " + motivo + "\n   Enun: " + e + "\n   Resp: " + r);
    }

    static List<Linha> parseArquivo(Path p) throws IOException {
        List<Linha> linhas = new ArrayList<>();
        List<String> rawLines = Files.readAllLines(p, StandardCharsets.UTF_8);
        for (int i = 0; i < rawLines.size(); i++) {
            if (i == 0) continue;
            List<String> fields = parseCsvLine(rawLines.get(i));
            if (fields.size() >= 4) {
                linhas.add(new Linha(i + 1, fields.get(0), fields.get(1), fields.get(2), fields.get(3)));
            }
        }
        return linhas;
    }

    static List<String> parseCsvLine(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') { cur.append('"'); i++; }
                    else inQuotes = false;
                } else cur.append(c);
            } else {
                if (c == '"') inQuotes = true;
                else if (c == ',') { out.add(cur.toString()); cur.setLength(0); }
                else cur.append(c);
            }
        }
        out.add(cur.toString());
        return out;
    }

    static int parseCoef(String s) {
        s = s.replaceAll("\\s", "");
        if (s.isEmpty() || s.equals("+")) return 1;
        if (s.equals("-")) return -1;
        return Integer.parseInt(s);
    }

    // ============ INEQUAÇÕES 2º GRAU ============
    static void verificarInequacoesSegundoGrau(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // ax^2 [+-]bx [+-]c op 0
        Pattern pat = Pattern.compile(
            "(\\d*)x\\^2\\s*([+-])\\s*(\\d*)x\\s*([+-])\\s*(\\d+)\\s*(<|>|\\\\leq|\\\\geq)\\s*0");
        Pattern patInc = Pattern.compile(
            "(\\d*)x\\^2\\s*([+-])\\s*(\\d+)\\s*(<|>|\\\\leq|\\\\geq)\\s*0");
        for (Linha l : linhas) {
            int a = 1, b = 0, c = 0;
            String op = null;
            Matcher m = pat.matcher(l.enunciado);
            if (m.find()) {
                a = m.group(1).isEmpty() ? 1 : Integer.parseInt(m.group(1));
                int sb = m.group(2).equals("+") ? 1 : -1;
                b = sb * (m.group(3).isEmpty() ? 1 : Integer.parseInt(m.group(3)));
                int sc = m.group(4).equals("+") ? 1 : -1;
                c = sc * Integer.parseInt(m.group(5));
                op = m.group(6);
            } else {
                Matcher m2 = patInc.matcher(l.enunciado);
                if (m2.find()) {
                    a = m2.group(1).isEmpty() ? 1 : Integer.parseInt(m2.group(1));
                    int sc = m2.group(2).equals("+") ? 1 : -1;
                    c = sc * Integer.parseInt(m2.group(3));
                    op = m2.group(4);
                } else continue;
            }
            long delta = (long)b * b - 4L * a * c;
            if (delta < 0) continue;
            long sqd = (long) Math.round(Math.sqrt(delta));
            if (sqd * sqd != delta) continue;
            // Tratamento delta = 0 (raiz dupla): inequação tem comportamentos especiais
            if (delta == 0) {
                // a > 0: ax² >= 0 sempre, ax² > 0 exceto raiz, ax² <= 0 só raiz, ax² < 0 nunca
                // Verificar conformidade básica - pula para evitar falsos positivos
                continue;
            }
            // raízes podem ser racionais; tratamos só inteiras
            long n1 = -b - sqd, n2 = -b + sqd;
            int den = 2 * a;
            if (n1 % den != 0 || n2 % den != 0) continue;
            long x1 = n1 / den, x2 = n2 / den;
            if (x1 > x2) { long t = x1; x1 = x2; x2 = t; }

            String esperado;
            boolean aPos = a > 0;
            if (op.equals("<")) {
                esperado = aPos
                  ? "(" + x1 + ",\\,"+ x2 + ")"
                  : "(-\\infty,\\," + x1 + ") \\cup (" + x2 + ",\\,+\\infty)";
            } else if (op.equals("\\leq")) {
                esperado = aPos
                  ? "[" + x1 + ",\\," + x2 + "]"
                  : "(-\\infty,\\," + x1 + "] \\cup [" + x2 + ",\\,+\\infty)";
            } else if (op.equals(">")) {
                esperado = aPos
                  ? "(-\\infty,\\," + x1 + ") \\cup (" + x2 + ",\\,+\\infty)"
                  : "(" + x1 + ",\\,"+ x2 + ")";
            } else if (op.equals("\\geq")) {
                esperado = aPos
                  ? "(-\\infty,\\," + x1 + "] \\cup [" + x2 + ",\\,+\\infty)"
                  : "[" + x1 + ",\\," + x2 + "]";
            } else continue;

            if (!l.resposta.contains(esperado)) {
                reportar(arq, l.num, "Resposta divergente. Esperado: " + esperado, l.enunciado, l.resposta);
            }
        }
    }

    // ============ EQUAÇÕES 2º GRAU ============
    static void verificarEquacaoSegundoGrau(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // Captura sinal opcional antes do termo x²
        Pattern pat = Pattern.compile(
            "([+-]?)\\s*(\\d*)x\\^2\\s*([+-])\\s*(\\d*)x\\s*([+-])\\s*(\\d+)\\s*=\\s*0");
        for (Linha l : linhas) {
            Matcher m = pat.matcher(l.enunciado);
            if (!m.find()) continue;
            // Ignora se for soma/produto/x_1 dado
            String e = l.enunciado.toLowerCase();
            boolean perguntaRaizes = !(e.contains("soma das") || e.contains("produto das")
                    || e.contains("encontre x_2") || e.contains("encontre \\(x_2"));
            int signA = m.group(1).equals("-") ? -1 : 1;
            if (!perguntaRaizes) {
                int a = signA * (m.group(2).isEmpty() ? 1 : Integer.parseInt(m.group(2)));
                int sb = m.group(3).equals("+") ? 1 : -1;
                int b = sb * (m.group(4).isEmpty() ? 1 : Integer.parseInt(m.group(4)));
                int sc = m.group(5).equals("+") ? 1 : -1;
                int c = sc * Integer.parseInt(m.group(6));
                if (e.contains("soma das")) {
                    // soma = -b/a
                    int num = -b, den = a;
                    int g = gcd(Math.abs(num), Math.abs(den));
                    num /= g; den /= g;
                    if (den < 0) { num = -num; den = -den; }
                    String esp1 = (den == 1) ? Integer.toString(num) : ("\\dfrac{" + num + "}{" + den + "}");
                    if (!l.resposta.contains(esp1) && (den == 1 || !l.resposta.contains(num + "/" + den))) {
                        reportar(arq, l.num, "Soma raízes esperada: " + num + "/" + den, l.enunciado, l.resposta);
                    }
                } else if (e.contains("produto das")) {
                    int num = c, den = a;
                    int g = gcd(Math.abs(num), Math.abs(den));
                    num /= g; den /= g;
                    if (den < 0) { num = -num; den = -den; }
                    String esp1 = (den == 1) ? Integer.toString(num) : ("\\dfrac{" + num + "}{" + den + "}");
                    if (!l.resposta.contains(esp1) && (den == 1 || !l.resposta.contains(num + "/" + den))) {
                        reportar(arq, l.num, "Produto raízes esperado: " + num + "/" + den, l.enunciado, l.resposta);
                    }
                }
                continue;
            }
            int a = signA * (m.group(2).isEmpty() ? 1 : Integer.parseInt(m.group(2)));
            int sb = m.group(3).equals("+") ? 1 : -1;
            int b = sb * (m.group(4).isEmpty() ? 1 : Integer.parseInt(m.group(4)));
            int sc = m.group(5).equals("+") ? 1 : -1;
            int c = sc * Integer.parseInt(m.group(6));
            long delta = (long)b * b - 4L * a * c;
            if (delta < 0) continue;
            long sqd = (long) Math.round(Math.sqrt(delta));
            if (sqd * sqd != delta) continue;
            // Verifica todas as raízes mencionadas com sinal x_1 ou x_2
            Pattern px = Pattern.compile("x_[12]\\s*=\\s*[^\\n=]*=\\s*(-?\\d+(?:/\\d+)?)");
            Matcher mx = px.matcher(l.resolucao);
            while (mx.find()) {
                String val = mx.group(1);
                long num, den = 1;
                if (val.contains("/")) {
                    String[] parts = val.split("/");
                    num = Long.parseLong(parts[0]);
                    den = Long.parseLong(parts[1]);
                } else num = Long.parseLong(val);
                long check = (long)a * num * num + (long)b * num * den + (long)c * den * den;
                if (check != 0) {
                    reportar(arq, l.num, "Raiz " + val + " não satisfaz "
                            + a + "x²" + (b >= 0 ? "+" : "") + b + "x" + (c >= 0 ? "+" : "") + c + "=0 (=" + check + ")",
                            l.enunciado, l.resolucao);
                    break;
                }
            }
        }
    }

    // ============ SISTEMA DE EQUAÇÕES 2x2 ============
    static void verificarSistemaEquacoes(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        Pattern presp = Pattern.compile("x\\s*=\\s*(-?\\d+)[^\\d-]{0,30}y\\s*=\\s*(-?\\d+)");
        Pattern peq = Pattern.compile("([+-]?\\s*\\d*)\\s*x\\s*([+-]\\s*\\d*)\\s*y\\s*=\\s*(-?\\d+)");
        for (Linha l : linhas) {
            Matcher m = presp.matcher(l.resposta);
            if (!m.find()) continue;
            int xv = Integer.parseInt(m.group(1));
            int yv = Integer.parseInt(m.group(2));
            Matcher me = peq.matcher(l.enunciado);
            int found = 0;
            while (me.find() && found < 2) {
                found++;
                int a = parseCoef(me.group(1));
                int b = parseCoef(me.group(2));
                int cc = Integer.parseInt(me.group(3));
                if (a * xv + b * yv != cc) {
                    reportar(arq, l.num, "x=" + xv + ",y=" + yv + " não satisfaz "
                            + a + "x+" + b + "y=" + cc + " (=" + (a*xv+b*yv) + ")",
                            l.enunciado, l.resposta);
                    break;
                }
            }
        }
    }

    // ============ LOGARITMO ============
    static void verificarLogaritmo(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // \log_{base}(arg) ou \log_base arg
        Pattern pat = Pattern.compile("\\\\log_\\{?(\\d+)\\}?\\s*\\(?(\\d+)\\)?");
        for (Linha l : linhas) {
            Matcher m = pat.matcher(l.enunciado);
            if (!m.find()) continue;
            int base = Integer.parseInt(m.group(1));
            int arg = Integer.parseInt(m.group(2));
            if (base <= 1 || arg <= 0) continue;
            int n = -1;
            long pw = 1;
            for (int i = 0; i <= 20; i++) {
                if (pw == arg) { n = i; break; }
                pw *= base;
                if (pw > 1e15) break;
            }
            if (n < 0) continue;
            Pattern pr = Pattern.compile("=\\s*(-?\\d+)\\b");
            Matcher mr = pr.matcher(l.resposta);
            Integer rv = null;
            while (mr.find()) rv = Integer.parseInt(mr.group(1));
            if (rv != null && rv != n) {
                reportar(arq, l.num, "log_" + base + "(" + arg + ")=" + n + " mas resposta diz " + rv,
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ MMC / MDC ============
    static void verificarMmcMdc(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        Pattern patMmc = Pattern.compile("(?i)mmc\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");
        Pattern patMdc = Pattern.compile("(?i)mdc\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");
        for (Linha l : linhas) {
            Matcher mm = patMmc.matcher(l.enunciado);
            if (mm.find()) {
                int a = Integer.parseInt(mm.group(1));
                int b = Integer.parseInt(mm.group(2));
                int mmc = lcm(a, b);
                // Tenta achar "= X" na resposta
                Matcher rm = Pattern.compile("=\\s*(\\d+)").matcher(l.resposta);
                Integer last = null;
                while (rm.find()) last = Integer.parseInt(rm.group(1));
                if (last != null && last != mmc) {
                    reportar(arq, l.num, "mmc(" + a + "," + b + ")=" + mmc + " mas resposta tem " + last,
                            l.enunciado, l.resposta);
                }
            }
            Matcher md = patMdc.matcher(l.enunciado);
            if (md.find()) {
                int a = Integer.parseInt(md.group(1));
                int b = Integer.parseInt(md.group(2));
                int mdc = gcd(a, b);
                Matcher rm = Pattern.compile("=\\s*(\\d+)").matcher(l.resposta);
                Integer last = null;
                while (rm.find()) last = Integer.parseInt(rm.group(1));
                if (last != null && last != mdc) {
                    reportar(arq, l.num, "mdc(" + a + "," + b + ")=" + mdc + " mas resposta tem " + last,
                            l.enunciado, l.resposta);
                }
            }
        }
    }
    static int gcd(int a, int b) { return b == 0 ? Math.abs(a) : gcd(b, a % b); }
    static int lcm(int a, int b) { return Math.abs(a / gcd(a, b) * b); }

    // ============ ADIÇÃO NATURAL ============
    static void verificarAdicaoNatural(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // Padrão: "A + B = ?" no estilo "Quanto é A+B?"
        // Pular exercícios de verificação/propriedade
        Pattern pat = Pattern.compile("(\\d+)\\s*\\+\\s*(\\d+)\\s*=\\s*\\?");
        for (Linha l : linhas) {
            String e = l.enunciado.toLowerCase();
            if (e.contains("verifi") || e.contains("conferir") || e.contains("prova real")
                || e.contains("substituir") || e.contains("\\square")
                || e.contains("propriedade") || e.contains("estim")
                || e.contains("ordem das") || e.contains("associativ")
                || e.contains("comutativ") || e.contains("sabendo que")
                || e.contains("respeitando")) continue;
            Matcher m = pat.matcher(l.enunciado);
            if (!m.find()) continue;
            long soma = Long.parseLong(m.group(1)) + Long.parseLong(m.group(2));
            if (!l.resposta.contains(Long.toString(soma))) {
                reportar(arq, l.num, "Soma esperada " + soma + " não encontrada",
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ SUBTRAÇÃO NATURAL ============
    static void verificarSubtracaoNatural(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        Pattern pat = Pattern.compile("(\\d+)\\s*-\\s*(\\d+)\\s*=\\s*\\?");
        for (Linha l : linhas) {
            String e = l.enunciado.toLowerCase();
            if (e.contains("verifi") || e.contains("conferir") || e.contains("prova real")
                || e.contains("como se chama") || e.contains("expressão")
                || e.contains("substituir") || e.contains("\\square")
                || e.contains("propriedade")) continue;
            Matcher m = pat.matcher(l.enunciado);
            if (!m.find()) continue;
            long a = Long.parseLong(m.group(1));
            long b = Long.parseLong(m.group(2));
            long dif = a - b;
            if (!l.resposta.contains(Long.toString(dif))) {
                reportar(arq, l.num, "Diferença esperada " + dif + " não encontrada",
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ MULTIPLICAÇÃO NATURAL ============
    static void verificarMultiplicacaoNatural(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // Apenas casos simples: "Calcule A × B"
        Pattern pat = Pattern.compile("Calcule\\s*\\\\\\(\\s*(\\d+)\\s*\\\\times\\s*(\\d+)\\s*\\\\\\)");
        for (Linha l : linhas) {
            String e = l.enunciado.toLowerCase();
            if (e.contains("propriedade") || e.contains("estim")
                || e.contains("substituir") || e.contains("\\square")
                || e.contains("ordem") || e.contains("respeitando")
                || e.contains("sabendo que") || e.contains("sem calcular")
                || e.contains("agrupando") || e.contains("\\times") && e.indexOf("\\times") != e.lastIndexOf("\\times")) continue;
            Matcher m = pat.matcher(l.enunciado);
            if (!m.find()) continue;
            long a = Long.parseLong(m.group(1));
            long b = Long.parseLong(m.group(2));
            long prod = a * b;
            if (!l.resposta.contains(Long.toString(prod))) {
                reportar(arq, l.num, "Produto esperado " + prod + " não encontrado (" + a + "*" + b + ")",
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ DIVISÃO NATURAL ============
    static void verificarDivisaoNatural(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        Pattern pat = Pattern.compile("Calcule\\s*\\\\\\(\\s*(\\d+)\\s*\\\\div\\s*(\\d+)\\s*\\\\\\)");
        for (Linha l : linhas) {
            String e = l.enunciado.toLowerCase();
            if (e.contains("verifi") || e.contains("conferir") || e.contains("prova")
                || e.contains("como se chama") || e.contains("propriedade")
                || e.contains("estim") || e.contains("ordem") || e.contains("respeitando")
                || e.contains("sabendo que") || e.contains("sem calcular")) continue;
            Matcher m = pat.matcher(l.enunciado);
            if (!m.find()) continue;
            long a = Long.parseLong(m.group(1));
            long b = Long.parseLong(m.group(2));
            if (b == 0) continue;
            long q = a / b;
            if (!l.resposta.contains(Long.toString(q))) {
                reportar(arq, l.num, "Quociente esperado " + q + " não encontrado",
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ PROGRESSÃO ARITMÉTICA ============
    // Mais complexo, vamos pular por ora

    // ============ PITÁGORAS ============
    static void verificarPitagoras(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // Caso simples: hipotenusa dado catetos. "catetos a e b"
        Pattern pat = Pattern.compile("(?i)catetos?\\s+(?:de\\s+)?(?:medida[s]?\\s+)?(\\d+)\\s+e\\s+(\\d+)");
        for (Linha l : linhas) {
            Matcher m = pat.matcher(l.enunciado);
            if (!m.find()) continue;
            long a = Long.parseLong(m.group(1));
            long b = Long.parseLong(m.group(2));
            long h2 = a*a + b*b;
            long h = (long)Math.round(Math.sqrt(h2));
            if (h*h != h2) continue;
            // Procura na resposta
            if (!l.resposta.contains(Long.toString(h))) {
                reportar(arq, l.num, "Hipotenusa esperada " + h + " (catetos " + a + "," + b + ")",
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ EQUAÇÕES LINEARES ============
    static void verificarEquacoes(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // Captura "x = N" na resposta e tenta confirmar
        Pattern presp = Pattern.compile("x\\s*=\\s*(-?\\d+(?:/\\d+)?)");
        for (Linha l : linhas) {
            Matcher m = presp.matcher(l.resposta);
            if (!m.find()) continue;
            // Tenta extrair forma: Ax + B = C ou A(x + B) = C, etc.
            // Forma básica: ax + b = cx + d
            Pattern peq = Pattern.compile(
                "(-?\\d*)x\\s*([+-])\\s*(\\d+)\\s*=\\s*(-?\\d*)x?\\s*([+-]\\s*\\d+)?");
            Matcher me = peq.matcher(l.enunciado);
            if (!me.find()) continue;
            try {
                String ax = me.group(1);
                int a = ax.isEmpty() || ax.equals("-") || ax.equals("+")
                    ? (ax.equals("-") ? -1 : 1) : Integer.parseInt(ax);
                int sb = me.group(2).equals("+") ? 1 : -1;
                int b = sb * Integer.parseInt(me.group(3));
                String cx = me.group(4) == null ? "" : me.group(4);
                int c;
                if (cx.isEmpty()) c = 0;
                else if (cx.equals("-")) c = -1;
                else c = Integer.parseInt(cx);
                // c não tem x => grupo 4 é um número, grupo 5 é null
                // Skip complexo
            } catch (Exception ex) { /* skip */ }
        }
    }

    // ============ PA - termo geral ============
    static void verificarPA(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // "a_1 = X" e "r = Y", pergunta "a_n" para n específico
        Pattern p1 = Pattern.compile("a_\\{?1\\}?\\s*=\\s*(-?\\d+)");
        Pattern pr = Pattern.compile("r\\s*=\\s*(-?\\d+)");
        Pattern pn = Pattern.compile("a_\\{?(\\d+)\\}?");
        Pattern presp = Pattern.compile("=\\s*(-?\\d+)");
        for (Linha l : linhas) {
            Matcher m1 = p1.matcher(l.enunciado);
            Matcher mr = pr.matcher(l.enunciado);
            if (!m1.find() || !mr.find()) continue;
            long a1 = Long.parseLong(m1.group(1));
            long r = Long.parseLong(mr.group(1));
            // Encontra menção a_n com n != 1
            Matcher mn = pn.matcher(l.enunciado);
            Long nAlvo = null;
            while (mn.find()) {
                long n = Long.parseLong(mn.group(1));
                if (n != 1) { nAlvo = n; break; }
            }
            if (nAlvo == null) continue;
            long aN = a1 + (nAlvo - 1) * r;
            if (!l.resposta.contains(Long.toString(aN))) {
                reportar(arq, l.num, "a_" + nAlvo + " esperado=" + aN + " (a1=" + a1 + ",r=" + r + ")",
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ PG - termo geral ============
    static void verificarPG(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        Pattern p1 = Pattern.compile("a_\\{?1\\}?\\s*=\\s*(-?\\d+)");
        Pattern pq = Pattern.compile("q\\s*=\\s*(-?\\d+)");
        Pattern pn = Pattern.compile("a_\\{?(\\d+)\\}?");
        for (Linha l : linhas) {
            Matcher m1 = p1.matcher(l.enunciado);
            Matcher mq = pq.matcher(l.enunciado);
            if (!m1.find() || !mq.find()) continue;
            long a1 = Long.parseLong(m1.group(1));
            long q = Long.parseLong(mq.group(1));
            Matcher mn = pn.matcher(l.enunciado);
            Long nAlvo = null;
            while (mn.find()) {
                long n = Long.parseLong(mn.group(1));
                if (n != 1) { nAlvo = n; break; }
            }
            if (nAlvo == null) continue;
            long aN = a1;
            for (long i = 1; i < nAlvo; i++) aN *= q;
            if (!l.resposta.contains(Long.toString(aN))) {
                reportar(arq, l.num, "a_" + nAlvo + " (PG) esperado=" + aN + " (a1=" + a1 + ",q=" + q + ")",
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ FUNÇÃO AFIM - f(N) ============
    static void verificarFuncaoAfim(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // f(x) = ax + b, calcular f(N)
        Pattern pf = Pattern.compile("f\\(x\\)\\s*=\\s*(-?\\d*)x\\s*([+-])\\s*(\\d+)");
        Pattern pcalc = Pattern.compile("f\\((-?\\d+)\\)");
        for (Linha l : linhas) {
            Matcher m = pf.matcher(l.enunciado);
            Matcher mc = pcalc.matcher(l.enunciado);
            if (!m.find() || !mc.find()) continue;
            String as = m.group(1);
            int a = as.isEmpty() ? 1 : (as.equals("-") ? -1 : Integer.parseInt(as));
            int sb = m.group(2).equals("+") ? 1 : -1;
            int b = sb * Integer.parseInt(m.group(3));
            int x = Integer.parseInt(mc.group(1));
            int fx = a * x + b;
            // Procura resposta
            if (!l.resposta.contains(Integer.toString(fx))) {
                reportar(arq, l.num, "f(" + x + ") esperado=" + fx + " (a=" + a + ",b=" + b + ")",
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ POLINÔMIOS - operação simples ============
    // pula por complexidade

    // ============ FUNÇÃO QUADRÁTICA - DISCRIMINANTE ============
    static void verificarFuncaoQuadraticaDelta(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // Discriminante: f(x) = ax² + bx + c, calcular delta
        Pattern pat = Pattern.compile(
            "discriminante.*?f\\(x\\)\\s*=\\s*([+-]?)\\s*(\\d*)x\\^2\\s*([+-])\\s*(\\d*)x\\s*([+-])\\s*(\\d+)");
        for (Linha l : linhas) {
            Matcher m = pat.matcher(l.enunciado);
            if (!m.find()) continue;
            int signA = m.group(1).equals("-") ? -1 : 1;
            int a = signA * (m.group(2).isEmpty() ? 1 : Integer.parseInt(m.group(2)));
            int sb = m.group(3).equals("+") ? 1 : -1;
            int b = sb * (m.group(4).isEmpty() ? 1 : Integer.parseInt(m.group(4)));
            int sc = m.group(5).equals("+") ? 1 : -1;
            int c = sc * Integer.parseInt(m.group(6));
            long delta = (long)b * b - 4L * a * c;
            if (!l.resposta.contains(Long.toString(delta))) {
                reportar(arq, l.num, "Δ esperado=" + delta + " (a=" + a + ",b=" + b + ",c=" + c + ")",
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ FUNÇÃO QUADRÁTICA - X DO VÉRTICE ============
    static void verificarFuncaoQuadraticaXv(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        Pattern pat = Pattern.compile(
            "(?i)X_v.*?f\\(x\\)\\s*=\\s*([+-]?)\\s*(\\d*)x\\^2\\s*([+-])\\s*(\\d*)x\\s*([+-])\\s*(\\d+)");
        for (Linha l : linhas) {
            Matcher m = pat.matcher(l.enunciado);
            if (!m.find()) continue;
            int signA = m.group(1).equals("-") ? -1 : 1;
            int a = signA * (m.group(2).isEmpty() ? 1 : Integer.parseInt(m.group(2)));
            int sb = m.group(3).equals("+") ? 1 : -1;
            int b = sb * (m.group(4).isEmpty() ? 1 : Integer.parseInt(m.group(4)));
            // Xv = -b/(2a)
            int num = -b, den = 2*a;
            int g = gcd(Math.abs(num), Math.abs(den));
            if (g == 0) continue;
            num /= g; den /= g;
            if (den < 0) { num = -num; den = -den; }
            String esp = (den == 1) ? Integer.toString(num) : "\\dfrac{" + num + "}{" + den + "}";
            if (!l.resposta.contains(esp)) {
                reportar(arq, l.num, "Xv esperado=" + num + "/" + den + " (a=" + a + ",b=" + b + ")",
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ EQUAÇÕES LINEARES ============
    static void verificarEquacoesLineares(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // Padrão 1: Ax + B = Cx + D
        Pattern pat2 = Pattern.compile(
            "Encontre\\s+\\\\\\(([a-z])\\\\\\)\\s*\\\\\\(\\s*(-?\\d*)\\1\\s*([+-])\\s*(\\d+)\\s*=\\s*(-?\\d*)\\1\\s*([+-])\\s*(\\d+)\\s*\\\\\\)");
        // Padrão 2: Ax + B = C (sem var no lado direito)
        Pattern pat1 = Pattern.compile(
            "Encontre\\s+\\\\\\(([a-z])\\\\\\)\\s*\\\\\\(\\s*(-?\\d*)\\1\\s*([+-])\\s*(\\d+)\\s*=\\s*(-?\\d+)\\s*\\\\\\)");
        for (Linha l : linhas) {
            int a = 0, b = 0, c = 0, d = 0;
            String var;
            Matcher m2 = pat2.matcher(l.enunciado);
            if (m2.find()) {
                var = m2.group(1);
                String as = m2.group(2);
                a = as.isEmpty() ? 1 : (as.equals("-") ? -1 : Integer.parseInt(as));
                int sb = m2.group(3).equals("+") ? 1 : -1;
                b = sb * Integer.parseInt(m2.group(4));
                String cs = m2.group(5);
                c = cs.isEmpty() ? 1 : (cs.equals("-") ? -1 : Integer.parseInt(cs));
                int sd = m2.group(6).equals("+") ? 1 : -1;
                d = sd * Integer.parseInt(m2.group(7));
            } else {
                Matcher m1 = pat1.matcher(l.enunciado);
                if (!m1.find()) continue;
                var = m1.group(1);
                String as = m1.group(2);
                a = as.isEmpty() ? 1 : (as.equals("-") ? -1 : Integer.parseInt(as));
                int sb = m1.group(3).equals("+") ? 1 : -1;
                b = sb * Integer.parseInt(m1.group(4));
                c = 0;
                d = Integer.parseInt(m1.group(5));
            }
            // a*x + b = c*x + d → (a-c)*x = d - b → x = (d-b)/(a-c)
            int denom = a - c;
            int numer = d - b;
            if (denom == 0) continue;
            int g = gcd(Math.abs(numer), Math.abs(denom));
            if (g == 0) g = 1;
            numer /= g; denom /= g;
            if (denom < 0) { numer = -numer; denom = -denom; }
            String esp1 = (denom == 1) ? Integer.toString(numer) : "\\dfrac{" + numer + "}{" + denom + "}";
            if (!l.resposta.contains(esp1)) {
                reportar(arq, l.num, var + " esperado=" + numer + "/" + denom
                        + " (a=" + a + ",b=" + b + ",c=" + c + ",d=" + d + ")",
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ JUROS SIMPLES ============
    static void verificarJurosSimples(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // Capital, taxa, tempo → J = C*i*t. Encontra padrão "R$ X" + "% ao Y"
        // Pula este por complexidade
    }

    // ============ COMBINATÓRIA ============
    static void verificarCombinatoria(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // Princípio multiplicativo: A·B
        // Detecta padrão "X tipo1 e Y tipo2" e resposta = X*Y
        // Ou Permutação simples: n! para "organizar n elementos"
        // Permutação circular: (n-1)!
        for (Linha l : linhas) {
            String e = l.enunciado;
            // Princípio multiplicativo - extrai TODOS os fatores no enunciado
            if (e.contains("combinações") || e.contains("combinaç")) {
                // Localiza segmento entre "há" e a pergunta
                int idxHa = e.indexOf("há ");
                int idxQuestion = -1;
                for (String t : new String[]{". Quantas", "? Qua", ", quantas"}) {
                    int ix = e.indexOf(t);
                    if (ix > 0 && (idxQuestion == -1 || ix < idxQuestion)) idxQuestion = ix;
                }
                if (idxHa >= 0 && idxQuestion > idxHa) {
                    String segm = e.substring(idxHa, idxQuestion);
                    List<Long> fatores = new ArrayList<>();
                    Matcher mn = Pattern.compile("(\\d+)\\s+tipos|(\\d+)\\s+\\w+").matcher(segm);
                    while (mn.find()) {
                        String s = mn.group(1) != null ? mn.group(1) : mn.group(2);
                        if (s != null) fatores.add(Long.parseLong(s));
                    }
                    if (fatores.size() >= 2) {
                        long prod = 1;
                        for (long f : fatores) prod *= f;
                        if (!l.resposta.contains(Long.toString(prod))) {
                            reportar(arq, l.num, "produto " + fatores + "=" + prod + " esperado",
                                    l.enunciado, l.resposta);
                        }
                    }
                }
            }
            // Fatorial simples
            Pattern pf = Pattern.compile("(?i)organizar\\s+(\\d+)|listar\\s+(\\d+)\\s+pa[ií]s|distribuir\\s+(\\d+)|arranjar\\s+(\\d+)|sequência.*?(\\d+)\\s+letras");
            Matcher mf = pf.matcher(e);
            if (mf.find()) {
                int n = -1;
                for (int i = 1; i <= mf.groupCount(); i++) {
                    if (mf.group(i) != null) { n = Integer.parseInt(mf.group(i)); break; }
                }
                if (n > 0 && n <= 12) {
                    long fact = 1;
                    for (int i = 2; i <= n; i++) fact *= i;
                    if (!l.resposta.contains(Long.toString(fact))) {
                        reportar(arq, l.num, n + "!=" + fact + " esperado", l.enunciado, l.resposta);
                    }
                }
            }
            // Permutação circular: "ao redor de mesa", "em círculo", "em roda"
            if (e.contains("redor") || e.contains("círculo") || e.contains("roda")
                || e.contains("fogueira") || e.contains("circular")) {
                Pattern pc = Pattern.compile("(\\d+)\\s+(?:pessoas|convidados|bailarinos|cadeiras)");
                Matcher mc = pc.matcher(e);
                if (mc.find()) {
                    int n = Integer.parseInt(mc.group(1));
                    if (n > 1 && n <= 13) {
                        long fact = 1;
                        for (int i = 2; i < n; i++) fact *= i;
                        if (!l.resposta.contains(Long.toString(fact))) {
                            reportar(arq, l.num, "(" + n + "-1)!=" + fact + " esperado", l.enunciado, l.resposta);
                        }
                    }
                }
            }
        }
    }

    // ============ PROBABILIDADE - urna/baralho ============
    static void verificarProbabilidade(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // "X bolas A e Y bolas B" → não ser A: Y/(X+Y) reduzido
        Pattern pat = Pattern.compile("(\\d+)\\s+bolas\\s+\\w+\\s+e\\s+(\\d+)\\s+bolas\\s+\\w+");
        for (Linha l : linhas) {
            if (!l.enunciado.contains("não ser")) continue;
            Matcher m = pat.matcher(l.enunciado);
            if (!m.find()) continue;
            int a = Integer.parseInt(m.group(1));
            int b = Integer.parseInt(m.group(2));
            int tot = a + b;
            // P(não ser primeira cor) = b/tot
            int num = b, den = tot;
            int g = gcd(num, den);
            num /= g; den /= g;
            String esp = "\\dfrac{" + num + "}{" + den + "}";
            if (!l.resposta.contains(esp)) {
                reportar(arq, l.num, "P(não ser primeiro)=" + num + "/" + den + " esperado ("+a+"+"+b+")",
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ CÍRCULO - área ============
    static void verificarCirculo(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // "área do círculo de raio R" → R²π
        Pattern pat = Pattern.compile("área do círculo de raio\\s+\\\\\\(\\s*(\\d+)\\s*\\\\\\)");
        for (Linha l : linhas) {
            Matcher m = pat.matcher(l.enunciado);
            if (!m.find()) continue;
            long r = Long.parseLong(m.group(1));
            long area = r * r;
            String esp = area + "\\pi";
            if (!l.resposta.contains(esp)) {
                reportar(arq, l.num, "Área esperada=" + area + "π (r=" + r + ")",
                        l.enunciado, l.resposta);
            }
        }
        // Coroa: R²-r²
        Pattern pcor = Pattern.compile("coroa.*?R=(\\d+).*?r=(\\d+)");
        for (Linha l : linhas) {
            Matcher m = pcor.matcher(l.enunciado);
            if (!m.find()) continue;
            long R = Long.parseLong(m.group(1));
            long r = Long.parseLong(m.group(2));
            long area = R*R - r*r;
            String esp = area + "\\pi";
            if (!l.resposta.contains(esp)) {
                reportar(arq, l.num, "Coroa esperada=" + area + "π (R=" + R + ",r=" + r + ")",
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ DIVISIBILIDADE - resto ============
    static void verificarDivisibilidade(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // "Qual é o resto da divisão de A por B?"
        Pattern pat = Pattern.compile("resto da divisão de\\s+\\\\\\(\\s*(\\d+)\\s*\\\\\\)\\s+por\\s+\\\\\\(\\s*(\\d+)\\s*\\\\\\)");
        for (Linha l : linhas) {
            Matcher m = pat.matcher(l.enunciado);
            if (!m.find()) continue;
            long a = Long.parseLong(m.group(1));
            long b = Long.parseLong(m.group(2));
            long r = a % b;
            if (!l.resposta.contains(Long.toString(r))) {
                reportar(arq, l.num, "resto " + a + "%" + b + "=" + r + " esperado",
                        l.enunciado, l.resposta);
            }
        }
    }

    // ============ REGRA DE TRÊS - proporção ============
    static void verificarRegraTresProp(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // A/B = C/x → x = B*C/A
        // OU A/x = B/C → x = A*C/B
        Pattern pat1 = Pattern.compile("\\\\dfrac\\{(\\d+)\\}\\{(\\d+)\\}\\s*=\\s*\\\\dfrac\\{(\\d+)\\}\\{x\\}");
        Pattern pat2 = Pattern.compile("\\\\dfrac\\{(\\d+)\\}\\{x\\}\\s*=\\s*\\\\dfrac\\{(\\d+)\\}\\{(\\d+)\\}");
        for (Linha l : linhas) {
            Matcher m1 = pat1.matcher(l.enunciado);
            if (m1.find()) {
                long a = Long.parseLong(m1.group(1));
                long b = Long.parseLong(m1.group(2));
                long c = Long.parseLong(m1.group(3));
                if (a == 0) continue;
                if ((b * c) % a != 0) continue;
                long x = b * c / a;
                if (!l.resposta.contains(Long.toString(x))) {
                    reportar(arq, l.num, "x=" + x + " esperado (" + a + "/" + b + "=" + c + "/x)",
                            l.enunciado, l.resposta);
                }
                continue;
            }
            Matcher m2 = pat2.matcher(l.enunciado);
            if (m2.find()) {
                long a = Long.parseLong(m2.group(1));
                long b = Long.parseLong(m2.group(2));
                long c = Long.parseLong(m2.group(3));
                if (b == 0) continue;
                if ((a * c) % b != 0) continue;
                long x = a * c / b;
                if (!l.resposta.contains(Long.toString(x))) {
                    reportar(arq, l.num, "x=" + x + " esperado (" + a + "/x=" + b + "/" + c + ")",
                            l.enunciado, l.resposta);
                }
            }
        }
    }

    // ============ FUNÇÃO QUADRÁTICA - f(N) ============
    static void verificarFuncaoQuadraticaCalc(Path p) throws IOException {
        String arq = p.getFileName().toString();
        List<Linha> linhas = parseArquivo(p);
        // f(N) f(x)=ax²+bx+c
        Pattern pat = Pattern.compile(
            "f\\((-?\\d+)\\)\\s*\\\\\\)\\s*\\\\\\(\\s*f\\(x\\)\\s*=\\s*([+-]?)\\s*(\\d*)x\\^2\\s*([+-])\\s*(\\d*)x\\s*([+-])\\s*(\\d+)");
        for (Linha l : linhas) {
            Matcher m = pat.matcher(l.enunciado);
            if (!m.find()) continue;
            int x = Integer.parseInt(m.group(1));
            int signA = m.group(2).equals("-") ? -1 : 1;
            int a = signA * (m.group(3).isEmpty() ? 1 : Integer.parseInt(m.group(3)));
            int sb = m.group(4).equals("+") ? 1 : -1;
            int b = sb * (m.group(5).isEmpty() ? 1 : Integer.parseInt(m.group(5)));
            int sc = m.group(6).equals("+") ? 1 : -1;
            int c = sc * Integer.parseInt(m.group(7));
            long fx = (long)a*x*x + (long)b*x + c;
            if (!l.resposta.contains(Long.toString(fx))) {
                reportar(arq, l.num, "f(" + x + ") esperado=" + fx + " (a=" + a + ",b=" + b + ",c=" + c + ")",
                        l.enunciado, l.resposta);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        try { verificarInequacoesSegundoGrau(DIR.resolve("inequacoessegundograu.txt")); } catch (Exception ex) { System.out.println("erro ineq: " + ex); }
        try { verificarEquacaoSegundoGrau(DIR.resolve("equacaosegundograu.txt")); } catch (Exception ex) { System.out.println("erro eq: " + ex); }
        try { verificarSistemaEquacoes(DIR.resolve("sistemaequacoes.txt")); } catch (Exception ex) { System.out.println("erro sis: " + ex); }
        try { verificarLogaritmo(DIR.resolve("logaritmo.txt")); } catch (Exception ex) { System.out.println("erro log: " + ex); }
        try { verificarMmcMdc(DIR.resolve("mmcmdc.txt")); } catch (Exception ex) { System.out.println("erro mmc: " + ex); }
        try { verificarAdicaoNatural(DIR.resolve("adicaonatural.txt")); } catch (Exception ex) { System.out.println("erro ad: " + ex); }
        try { verificarSubtracaoNatural(DIR.resolve("subtracaonatural.txt")); } catch (Exception ex) { System.out.println("erro sub: " + ex); }
        try { verificarMultiplicacaoNatural(DIR.resolve("multiplicacaonatural.txt")); } catch (Exception ex) { System.out.println("erro mult: " + ex); }
        try { verificarDivisaoNatural(DIR.resolve("divisaonatural.txt")); } catch (Exception ex) { System.out.println("erro div: " + ex); }
        try { verificarPitagoras(DIR.resolve("pitagoras.txt")); } catch (Exception ex) { System.out.println("erro pit: " + ex); }
        try { verificarPA(DIR.resolve("pa.txt")); } catch (Exception ex) { System.out.println("erro pa: " + ex); }
        try { verificarPG(DIR.resolve("pg.txt")); } catch (Exception ex) { System.out.println("erro pg: " + ex); }
        try { verificarFuncaoAfim(DIR.resolve("funcaoafim.txt")); } catch (Exception ex) { System.out.println("erro afim: " + ex); }
        try { verificarFuncaoQuadraticaDelta(DIR.resolve("funcaoquadratica.txt")); } catch (Exception ex) { System.out.println("erro fqd: " + ex); }
        try { verificarFuncaoQuadraticaXv(DIR.resolve("funcaoquadratica.txt")); } catch (Exception ex) { System.out.println("erro fqxv: " + ex); }
        try { verificarFuncaoQuadraticaCalc(DIR.resolve("funcaoquadratica.txt")); } catch (Exception ex) { System.out.println("erro fqcalc: " + ex); }
        try { verificarEquacoesLineares(DIR.resolve("equacoes.txt")); } catch (Exception ex) { System.out.println("erro eqlin: " + ex); }
        try { verificarCombinatoria(DIR.resolve("combinatoria.txt")); } catch (Exception ex) { System.out.println("erro comb: " + ex); }
        try { verificarProbabilidade(DIR.resolve("probabilidade.txt")); } catch (Exception ex) { System.out.println("erro prob: " + ex); }
        try { verificarCirculo(DIR.resolve("circulo.txt")); } catch (Exception ex) { System.out.println("erro circ: " + ex); }
        try { verificarDivisibilidade(DIR.resolve("divisibilidade.txt")); } catch (Exception ex) { System.out.println("erro div2: " + ex); }
        try { verificarRegraTresProp(DIR.resolve("regratres.txt")); } catch (Exception ex) { System.out.println("erro r3: " + ex); }

        if (erros.isEmpty()) {
            System.out.println("Nenhum erro detectado.");
        } else {
            System.out.println("=== " + erros.size() + " ERROS ===");
            for (String e : erros) System.out.println(e);
        }
    }
}
