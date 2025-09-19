package nl.rug.oop.flaps.aircraft_editor.model.Passengers;

import javax.swing.*;

/**
 * Subclass of the passenger class. Attempts to hijack the plane.
 * 40% chance of the aircraft going down midflight, a 30% chance of 10-20 random passengers dying and a 30% chance of
 * passengers overpowering and killing the terrorist
 */
public class Terrorist extends Passenger{

    public Terrorist() {
        super();
    }

    public void BoomFunny(int amount){
        int dead = 0;
        for(int i = 0; i<amount; i++){
            int Activation = (int)(Math.random()*100);
            if(Activation <= 20){
               int Random = (int)(Math.random()*100);
               switch (Random/10){
                   case 0:
                   case 1:
                   case 2:
                       dead += (int) (Math.random() * (20 - 10)) + 10;
                       break;
                   case 3:
                   case 4:
                   case 5:
                   case 6:
                       JOptionPane.showMessageDialog(null,  "Plane gone boom boom!!");
                       break;
                   case 7:
                   case 8:
                   case 9:
                       JOptionPane.showMessageDialog(null,  "A terrorist died, YAY!!!");
                       break;
               }
            }
        }
        if(dead != 0){
            JOptionPane.showMessageDialog(null, dead + " People died on the plane. At least a terrorist is dead, I guess?");
        }
    }

}
