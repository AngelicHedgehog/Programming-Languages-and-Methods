package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Stream;

public class RectangleSet {
    HashSet<Rectangle> data;

    public RectangleSet() {data = new HashSet<>();}

    public void add(Rectangle r) {data.add(r);}

    public void add(double[] x, double[] y) {data.add(new Rectangle(x, y));}

    public Stream<Double> convexAreas() {
        ArrayList<Double> result = new ArrayList<>();
        data
                .stream()
                .filter(Rectangle::isConvex)
                .forEach(x -> result.add(x.getArea()));
        return result.stream();
    }

    public Optional<Rectangle> maxDiagsRectangle() {
        Optional<Rectangle> result = Optional.empty();
        Optional<Rectangle> tmp = data
                .stream()
                .max((x, y) -> (int) (x.getSumDiags() - y.getSumDiags()));
        if (tmp.isPresent())
            result = tmp;
        return result;
    }
}
