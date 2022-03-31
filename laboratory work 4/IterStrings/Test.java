package com.company;

public class Test {
    public static void main(String[] args) {
        StringArr array = new StringArr();
        array.addElem("123");       // 1 2 3 _ _ _
        array.addElem("456");       // _ _ _ 4 5 6
        array.addElem("126");       // 1 2 _ _ _ 6
        array.addElem("146");       // 1 _ _ 4 _ 6
        array.addElem("345");       // _ _ 3 4 5 _
        array.addElem("234");       // _ 2 3 4 _ _
        for (String[] a : array)
            System.out.printf("%s %s\n", a[0], a[1]);
        System.out.println();
        array.setElem(3, "156"); // 1 _ _ _ 5 6
        for (String[] a : array)
            System.out.printf("%s %s\n", a[0], a[1]);
    }
}
