package org.example.jakartademo.basics;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;

@Named("hitBean")
@RequestScoped
@Getter
public class HitCountSimple implements Serializable {
    private int hits;

    public int getSteps() {
        return 1;
    }

    public void incrementHits() {
        hits++;
    }
}
