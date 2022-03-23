package com.company;

import java.util.Arrays;

public class Test {
    private static int randomIndex(int max) {return (int) (Math.random() * max);}

    public static void main(String[] args) {
        int count_scints = 5, count_publics = 50, count_links = 10;

        Scientist[] scints = new Scientist[count_scints];
        for (int i = 0; i < count_scints; i++) scints[i] = new Scientist();

        for (int i = 0, scnt_index; i < count_publics; i++) {
            boolean[] links = new boolean[count_links];
            for (int j = 0; j < count_links; j++) {
                scnt_index = randomIndex(count_scints);
                links[j] = scints[scnt_index].publicationLinked(randomIndex(scints[scnt_index].countOfPublics()));
            }
            scints[randomIndex(count_scints)].addPublication(links);
        }

        Arrays.sort(scints);

        for (Scientist scient : scints)
            System.out.println(scient);
    }
}
