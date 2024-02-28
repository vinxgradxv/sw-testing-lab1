package ru.tinkoff.ps.ops.test.swt.task3;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public abstract class ScreenObject {
    int size;
    int brightness;
    Color color;
    String name;

    public boolean setSize(int size) {
        if (size <= 0) {
            return false;
        }
        this.size = size;
        return true;
    }

    public boolean setBrightness(int brightness) {
        if (brightness <= 0) {
            return false;
        }
        this.brightness = brightness;
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
