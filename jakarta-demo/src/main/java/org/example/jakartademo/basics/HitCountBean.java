package org.example.jakartademo.basics;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;
import java.time.LocalTime;

@Named
@ApplicationScoped
@Getter
public class HitCountBean implements java.io.Serializable {
    private int hitCount = 0;

    public String getTimeAsString() {
        return LocalTime.now().toString();
    }

    public void incrementHitCount() {
        hitCount++;
    }
}
