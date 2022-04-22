package com.company;

import java.util.Iterator;

public class StringArr implements Iterable<String[]> {
    private String[] mas = new String[4];
    private int cap_mas = 4;
    private int count = 0;

    private void includeMas() {
        String[] new_mas = new String[cap_mas * 2];
        System.arraycopy(mas, 0, new_mas, 0, cap_mas);
        mas = new_mas;
        cap_mas *= 2;
    }

    public void addElem(String new_string) {
        if (count == cap_mas)
            includeMas();
        mas[count++] = new_string;
    }

    public void setElem(int i, String new_string) {mas[i] = new_string;}

    public Iterator<String[]> iterator() {return new StringIterator();}

    private class StringIterator implements Iterator<String[]> {
        private int i, j;

        public StringIterator () {i = 0; j = 0;}

        public boolean hasNext () {
            if (++j == count)
                j = ++i + 1;
            if (i == count - 1) return false;

            boolean intersect = true;
            char[] B = mas[j].toCharArray();
            for (char a : mas[i].toCharArray()) {
                for (char b : B)
                    if (a == b) {
                        intersect = false;
                        break;
                    }
                if (!intersect)
                    break;
            }
            return intersect || hasNext();
        }

        public String[] next() {return new String[] {mas[i], mas[j]};}
    }
}
