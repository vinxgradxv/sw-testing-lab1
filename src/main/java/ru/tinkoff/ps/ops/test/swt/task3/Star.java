package ru.tinkoff.ps.ops.test.swt.task3;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Star extends ScreenObject{
    private boolean isDead = false;

    public boolean kill() {
        if (isDead) {
            return false;
        }
        this.isDead = true;
        this.color = Color.BLACK;
        return true;
    }

    public boolean setColor(Color color) {
        if (isDead && !color.equals(Color.BLACK) || !isDead && color.equals(Color.BLACK)) {
            return false;
        }
        this.color = color;
        return true;
    }
}
