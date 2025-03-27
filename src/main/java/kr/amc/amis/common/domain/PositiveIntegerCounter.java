package kr.amc.amis.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PositiveIntegerCounter {
    private int count;

    public PositiveIntegerCounter() {
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    public void increase() {
        count++;
    }

    public void decrease() {
        if (count <= 0) {
            return;
        }
        this.count--;
    }
}
