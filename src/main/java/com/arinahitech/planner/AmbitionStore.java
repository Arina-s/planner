package com.arinahitech.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AmbitionStore {

    @Autowired
    @Qualifier("dream")
    private Ambition ambition;

    public AmbitionStore() {
    }

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
