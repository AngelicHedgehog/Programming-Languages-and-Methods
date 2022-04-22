package com.company;

public class Sentences implements Comparable<Sentences> {
    private String[] sentences = new String[4];
    private int order = -1, mas_len = 4, count_sentences = 0;
    private final int[] order_proof = {0, 0}; // {индекс предложения, индекс элемента}

    private void increaseMas() {
        String[] sentences_new = new String[mas_len * 2];
        System.arraycopy(sentences, 0, sentences_new, 0, mas_len);
        sentences = sentences_new;
        mas_len *= 2;
    }

    public int getOrder() {return order;}

    public void addNew(String new_sentence) {
        int len = new_sentence.length(), start = -1;
        char now = ' ', last;
        for(int i = 0, n = 0; i < len; i++) {
            last = now; now = new_sentence.charAt(i);
            if(now == ' ' && last != ' ' && last != ',')
                n++;
            else if(now == ',' || i + 1 == len) {
                if(i + 1 == len) {if(now != ' ') n++;}
                else if(last != ' ' && last != ',') n++;
                if (n > order) {
                    order = n;
                    order_proof[0] = count_sentences;
                    order_proof[1] = start;
                }
                start = i; n = 0;
            }
        }
        if(count_sentences == mas_len) this.increaseMas();
        sentences[count_sentences++] = new_sentence;
    }

    public int compareTo(Sentences obj) {return order - obj.getOrder();}

    public String toString() {
        if(count_sentences == 0)
            return "\t<Класс предложений>\nНе содержит ни ондного.\n\n";

        StringBuilder format = new StringBuilder();
        format.append("\t\t<Экземпляр класса предложений>\nМиксимум слов без запятых: \"\"\"");

        int end = sentences[order_proof[0]].indexOf(',', order_proof[1] + 1);
        if(end == -1) format.append(sentences[order_proof[0]].substring(order_proof[1] + 1));
        else format.append(sentences[order_proof[0]], order_proof[1] + 1, end);

        format.append(String.format("\"\"\"(порядок экземпляра равен %s)\nВсе предложения: \"\"\"", order));
        for(int i = 0; i < count_sentences; i++) format.append(sentences[i]);
        format.append("\"\"\"\n");

        return format.toString();
    }
}
