package com.company;

public class Universe {
    private static double randCord(double min, double max){return Math.random() * (max - min) + min;}

    public static void main(String[] args) {

        final int n = 10;
        double min_cord = -50, max_cord = 50;
        Atom[] atoms = new Atom[n];
        for (int i = 0; i < n; i++) {
            atoms[i] = new Atom(randCord(min_cord, max_cord), randCord(min_cord, max_cord), randCord(min_cord, max_cord));
            System.out.printf("Частица №%s: \tx — %2$14.10f;\ty — %3$14.10f;\tz — %4$14.10f;\n", Atom.count(), atoms[i].getX(), atoms[i].getY(), atoms[i].getZ());
        }

        double[] box = new double[6];
        box[0] = atoms[0].getX(); box[1] = atoms[0].getX();
        box[2] = atoms[0].getY(); box[3] = atoms[0].getY();
        box[4] = atoms[0].getZ(); box[5] = atoms[0].getZ();
        for (int i = 1; i < n; i++){
            box[0] = Math.min(box[0], atoms[i].getX()); box[1] = Math.max(box[1], atoms[i].getX());
            box[2] = Math.min(box[2], atoms[i].getY()); box[3] = Math.max(box[3], atoms[i].getY());
            box[4] = Math.min(box[4], atoms[i].getZ()); box[5] = Math.max(box[5], atoms[i].getZ());
        }

        Atom[] extrs = {}, extrs_new;
        double x, y, z;
        for (int i = 0; i < n; i++) {
            x = atoms[i].getX(); y = atoms[i].getY(); z = atoms[i].getZ();
            if (x == box[0] || x == box[1] || y == box[2] || y == box[3] || z == box[4] || z == box[5]) {
                extrs_new = new Atom[extrs.length + 1];
                System.arraycopy(extrs, 0, extrs_new, 0, extrs.length);
                extrs_new[extrs.length] = atoms[i];
                extrs = extrs_new;
            }
        }

        double max_distance = 0;
        for (Atom extr : extrs)
            for (Atom atom : atoms)
                max_distance = Math.max(max_distance, extr.distanceTo(atom));

        System.out.printf("\nВсего частиц во вселенной: %s\n", Atom.count());
        System.out.printf("Максимальное расстояние между двумя частицами вселенной равно: %s", Math.sqrt(max_distance));
    }
}
