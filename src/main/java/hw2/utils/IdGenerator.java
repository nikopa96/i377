package hw2.utils;

import lombok.Getter;

@Getter
public class IdGenerator {

    private Long id = 0L;

    public void incrementId() {
        this.id++;
    }
}
