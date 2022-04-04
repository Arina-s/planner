package com.arinahitech.planner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dream implements Ambition {

    @Value("Apartment in Dubai")
    private String name;

    public void printDream() {
        if (name != null) {
            System.out.println("My dream is " + name + "!");
        } else {
            System.out.println("There is no dream yet.");
        }
    }

    @Override
    public void printAmbition() {
        printDream();
    }
}
