package nl.rug.oop.flaps.aircraft_editor.model.Passengers;


import lombok.Getter;

import java.util.Random;

/**
 * This class represents the passengers in the aircraft
 */
@Getter
public class Passenger {

    private final String name;
    private final int age;
    private final double weight;

    public Passenger() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        int length = (int) (Math.random() * 10) + 1;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        this.name = sb.toString();;
        this.age = (int) (Math.random() * 100) + 1;
        this.weight = Math.random() * 181 + 20;
    }
}
