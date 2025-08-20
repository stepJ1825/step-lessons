package by.step.functionalinterfaces;

import java.time.LocalDateTime;

public class MyObject {
    private LocalDateTime createdAt;

    public MyObject(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
