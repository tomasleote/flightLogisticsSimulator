package nl.rug.oop.flaps.aircraft_editor.model.Passengers;

import javax.swing.*;

/**
 * subclass of the passenger class. Thief steals 1-5kg of the most valuable cargo
 */
public class Thief extends Passenger {

    public Thief() {
        super();
    }

    public void SneakingThroughTheCracks(int amount) {
        int Thiefs = 0;
        double Stolengoods = 0;
        for (int i = 0; i < amount; i++) {
            int Activation = (int) (Math.random() * 100);
            if (Activation <= 20) {
                Thiefs++;
                Stolengoods += (int) (Math.random() * (1 - 5) + 1);
            }
        }
        if(Thiefs != 0){
            JOptionPane.showMessageDialog(null,  Thiefs + " Thiefs stole " + Stolengoods + " KG of your goods, BAD BOIS!");
        }
    }
}
