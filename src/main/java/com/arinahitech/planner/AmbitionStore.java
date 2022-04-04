package com.arinahitech.planner;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class AmbitionStore {

    private Ambition ambition;

    @Autowired
    public AmbitionStore(@Qualifier("dream")Ambition ambition) {
        this.ambition = ambition;
    }

    public void printAllAmbitions() {
        if (ambition != null) {
            ambition.printAmbition();
        } else {
            System.out.println("No ambitions");
        }
    }
}
