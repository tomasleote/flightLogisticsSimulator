package nl.rug.oop.flaps.aircraft_editor.model.Passengers;


import javax.swing.*;

/**
 * Subclass of the passenger class.
 * Attempts to fight another passenger at random leading to -1 passenger on arrival.
 */
public class RowdyBogan extends Passenger{
    public RowdyBogan() {
        super();
    }

    public void FightLel(int amount){
        int Dead = 0;
        for(int i = 0; i<amount; i++){
            int Activation = (int)(Math.random()*100);
            if(Activation <= 20){
                Dead++;
            }
        }
        if(Dead != 0){
            JOptionPane.showMessageDialog(null, Dead + " People died on the plane. It was a great feast indeed!");
        }
    }
}
