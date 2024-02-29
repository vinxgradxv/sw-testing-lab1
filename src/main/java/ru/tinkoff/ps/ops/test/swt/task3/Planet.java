package ru.tinkoff.ps.ops.test.swt.task3;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class Planet extends ScreenObject{
    boolean isPopulated;

    public boolean isPopulated() {
        return isPopulated;
    }
}
