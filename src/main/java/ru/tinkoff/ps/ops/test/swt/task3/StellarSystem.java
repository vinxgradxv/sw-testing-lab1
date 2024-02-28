package ru.tinkoff.ps.ops.test.swt.task3;

import java.util.HashSet;
import java.util.Set;

public class StellarSystem{
    private final Set<Star> stars;

    public StellarSystem(Set<Star> stars) {
        this.stars = stars;
    }

    private boolean addStar(Star star) {
        if (stars.contains(star)) {
            return false;
        }
        stars.add(star);
        return true;
    }

    private boolean removeStar(Star star) {
        return stars.remove(star);
    }

    private int getStarsCount() {
        return stars.size();
    }
}
