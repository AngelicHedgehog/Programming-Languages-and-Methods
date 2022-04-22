package com.company;

import java.util.Iterator;

public class Parentheses implements Iterable<String> {
    private boolean[] main_string = new boolean[4];
    private int cap = 4;
    private int count = 0;

    private void include() {
        boolean[] new_string = new boolean[cap * 2];
        System.arraycopy(main_string, 0, new_string, 0, cap);
        cap *= 2;
        main_string = new_string;
    }

    public Parentheses() {}

    public Parentheses(String elements) {extend(elements);}

    public void append(char element) {
        if (element == '(')
            main_string[count++] = true;
        else if (element == ')')
            main_string[count++] = false;
        if (count == cap)
            include();
    }

    public void extend(String elements) {
        for (char elem : elements.toCharArray()) {
            if (elem == '(')
                main_string[count++] = true;
            else if (elem == ')')
                main_string[count++] = false;
            if (count == cap)
                include();
        }
    }

    public void insert(int i, char element) {
        if (element == '(' || element == ')') {
            if (i > count)
                i = count;
            else if (i < 0) {
                i += count;
                if (i < 0)
                    i = 0;
            }
            if (++count == cap)
                include();
            boolean[] new_string = new boolean[cap];
            System.arraycopy(main_string, 0, new_string, 0, i);
            new_string[i] = element == '(';
            System.arraycopy(main_string, i, new_string, i + 1, count - i);
            main_string = new_string;
        }
    }

    public char pop() {
        return main_string[count--] ? '(' : ')';
    }

    public char pop(int index) {
        if (index < 0)
            index += count;
        char elem = main_string[index] ? '(' : ')';
        boolean[] new_string = new boolean[cap];
        System.arraycopy(main_string, 0, new_string, 0, index);
        System.arraycopy(main_string, index + 1, new_string, index, --count - index);
        main_string = new_string;
        return elem;
    }

    public void remove(char element) {
        if (element == '(' || element == ')')
            for (int i = 0; i < count; i++)
                if (main_string[i] == (element == '(')) {
                    char e = pop(i);
                    break;
                }
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < count; i++)
            buf.append(main_string[i] ? '(' : ')');
        return buf.toString();
    }

    public Iterator<String> iterator() {return new StringIterator();}

    private class StringIterator implements Iterator<String> {
        private int i, j, index;

        public StringIterator () {i = 0; j = 0;}

        public boolean hasNext () {
            if (++j == count)
                j = ++i + 1;
            if (i == count - 1) return false;

            int balance = 0;
            for (index = i; index <= j; index++) {
                balance += main_string[index] ? 1 : -1;
                if (balance < 0)
                    break;
            }
            return balance == 0 || hasNext();
        }

        public String next() {
            StringBuilder buf = new StringBuilder();
            for (index = i; index <= j; index++)
                buf.append(main_string[index] ? '(' : ')');
            return buf.toString();
        }
    }
}
