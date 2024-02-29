package ru.tinkoff.ps.ops.test.swt.task3;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

@AllArgsConstructor
public class Screen {
    private Map<ScreenObject, Coordinates> objectCoordinates;
    private int width;
    private int height;

    public Map<ScreenObject, Coordinates> getObjectCoordinates() {
        return new HashMap<>(objectCoordinates);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setSize(int height, int width) {
        if (height <= 0 || width <= 0) {
            return;
        }
        if (height < this.height) {
            for (var entry : objectCoordinates.entrySet()) {
                if (entry.getValue().getY() > height) {
                   entry.getValue().setY(height);
                }
            }
        }

        if (width < this.width) {
            for (var entry : objectCoordinates.entrySet()) {
                if (entry.getValue().getX() > width) {
                    entry.getValue().setX(width);
                }
            }
        }

        this.height = height;
        this.width = width;
    }

    public StellarSystem mergeStars(List<Star> stars) {
        for (int i = 0; i < stars.size() - 1; i++) {
            for (int j = i + 1; j < stars.size(); j++) {
                var star1 = stars.get(i);
                var star2 = stars.get(j);

                if (abs(objectCoordinates.get(star1).getX() - objectCoordinates.get(star2).getX()) > 5) {
                    return null;
                }
                if (abs(objectCoordinates.get(star1).getY() - objectCoordinates.get(star2).getY()) > 5) {
                    return null;
                }
            }
        }
        return new StellarSystem(new HashSet<>(stars));
    }

    public PlanetSystem createPlanetSystem(List<Planet> planets, Star star) {
        for (var planet : planets) {
            if (abs(objectCoordinates.get(star).getX() - objectCoordinates.get(planet).getX()) > 5) {
                return null;
            }
            if (abs(objectCoordinates.get(star).getY() - objectCoordinates.get(planet).getY()) > 5) {
                return null;
            }

        }
        return new PlanetSystem(star, planets);
    }

    public boolean addObject(ScreenObject object, int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }
        for (var entry : objectCoordinates.entrySet()) {
            if (entry.getValue().getY() == y && entry.getValue().getX() == x) {
                return false;
            }
        }
        objectCoordinates.put(object, new Coordinates(x, y));
        return true;
    }

    public boolean removeObject(ScreenObject object) {
        var obj = objectCoordinates.remove(object);
        return obj != null;
    }

    public boolean removeObject(int x, int y) {
        for (var entry : objectCoordinates.entrySet()) {
            if (entry.getValue().getX() == x && entry.getValue().getY() == y) {
                objectCoordinates.remove(entry.getKey());
                return true;
            }
        }
        return false;
    }
}
