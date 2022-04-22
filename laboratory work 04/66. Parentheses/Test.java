package com.company;

public class Test {
    public static void main(String[] args) {
        Parentheses buf = new Parentheses("(()(");
        char elem;
        for (String item : buf) System.out.printf("%s-", item);
        System.out.printf("----%s\n", buf);

        buf.append(')');
        for (String item : buf) System.out.printf("%s-", item);
        System.out.printf("----%s\n", buf);

        buf.extend("()");
        for (String item : buf) System.out.printf("%s-", item);
        System.out.printf("----%s\n", buf);

        buf.insert(3, ')');
        for (String item : buf) System.out.printf("%s-", item);
        System.out.printf("----%s\n", buf);

        elem = buf.pop();
        for (String item : buf) System.out.printf("%s-", item);
        System.out.printf("----%s::poped['%s']\n", buf, elem);

        elem = buf.pop(2);
        for (String item : buf) System.out.printf("%s-", item);
        System.out.printf("----%s::poped['%s']\n", buf, elem);

        buf.remove('(');
        for (String item : buf) System.out.printf("%s-", item);
        System.out.printf("----%s\n", buf);
    }
}
