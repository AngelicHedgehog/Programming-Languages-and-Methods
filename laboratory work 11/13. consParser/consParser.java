import java.util.Scanner;

public class consParser {

    static StringBuilder result = new StringBuilder();
    static Scanner scan = new Scanner(System.in);
    static String line = "";
    static int row = 0, col = -1;
    static char sym;
    static boolean fin = false;

    private static boolean next() { return next(true); }

    private static boolean next(boolean ignoreSpaces) {
        //System.out.printf("%c -> %s\n", sym, result);
        if (fin) {
            return false;
        }
        if (++col == line.length()) {
            line = '\n' + scan.nextLine();
            if (line.length() == 1) {
                fin = true;
                return false;
            }
            col = 0;
            row++;
        }
        sym = line.charAt(col);
        if (ignoreSpaces && Character.isWhitespace(sym)) {
            return next(true);
        }
        return true;
    }

    private static boolean ParseExpr() {
        result.append("<Expr> ");
        if (fin) {
            return false;
        }
        if (sym == '(') {
            return next() && sym == 'c' && next() && sym == 'o' &&
                   next() && sym == 'n' && next() && sym == 's' &&
                    next() && ParseList();
        } else if (sym == 'n') {
            if (next() && sym == 'i' && next() && sym == 'l') {
                next();
                return true;
            }
            return false;
        } else if ('0' <= sym && sym <= '9') {
            while (next(false) && '0' <= sym && sym <= '9') { }
            if (Character.isWhitespace(sym)) { next(); }
            return true;
        }
        return false;
    }

    private static boolean ParseList() {
        result.append("<List> ");
        return ParseExpr() && ParseTail();
    }

    private static boolean ParseTail() {
        result.append("<Tail> ");
        if (sym == ')') {
            next();
            return true;
        }
        return ParseList();
    }

    public static void main(String[] args) {
        next();
        if (ParseExpr() && fin) {
            System.out.print(result);
        } else {
            System.out.printf("syntax error at (%d, %d)\n", row, col);
        }
    }
}
