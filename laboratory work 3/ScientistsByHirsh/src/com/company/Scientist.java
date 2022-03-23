package com.company;

public class Scientist implements Comparable<Scientist> {
    private int[] publics = {};
    private int h = 0;

    private void reevalH() {
        h--;
        for (int a = 0, b = 0; ; h++, a = 0, b = 0) {
            for (int publication : publics)
                if (publication > h)
                    a++;
                else if (publication == h)
                    b++;
            if (a <= h && h <= a + b) break;
        }
    }

    public int getH() {return h;}

    public int countOfPublics() {return publics.length;}

    public boolean publicationLinked(int index) {
        if (index >= publics.length)
            return false;
        else {
            publics[index]++;
            this.reevalH();
            return true;
        }
    }

    public void addPublication(boolean[] links) {
        int count_valid = 0;
        for (boolean link : links)
            if (link)
                count_valid++;

        int[] publics_new = new int[publics.length + 1];
        System.arraycopy(publics, 0, publics_new, 0, publics.length);
        publics_new[publics.length] = count_valid;
        this.publics = publics_new;
        reevalH();
    }

    public int compareTo(Scientist obj) {return h - obj.getH();}

    public String toString() {
        if (publics.length == 0)
            return "Учёный не написал ни одной статьи.\n";

        int max = 0;
        for (int publ : publics)
            max = Math.max(max, publ);

        int[] N = new int[max + 1];
        for (int publication : publics)
            N[publication]++;

        StringBuilder format = new StringBuilder();
        format.append(String.format("Информация по учёному, имеющему индекс Хирша, равный %s:\n", this.getH()));
        for (int i = 0; i < N.length; i++)
            if (N[i] != 0)
                format.append(String.format("\tПо %d раз были процетированны %s статей\n", i, N[i]));

        //for(int p : publics) format.append(String.format("%s ", p)); //вывод количеств упоминаний каждой статьи
        return format.toString();
    }
}
