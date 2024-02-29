package ru.tinkoff.ps.ops.test.swt.task3;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PlanetSystem{
    private Star star;
    private List<Planet> planets;

    public Star getStar() {
        return star;
    }

    public List<Planet> getPlanets() {
        return planets;
    }
}
