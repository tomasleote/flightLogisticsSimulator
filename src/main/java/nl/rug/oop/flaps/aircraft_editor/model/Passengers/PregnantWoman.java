package nl.rug.oop.flaps.aircraft_editor.model.Passengers;

import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;

import javax.swing.*;

/**
 * Subclass of the passenger class.
 * Gives birth during flight, leading to +1 regular passenger on arrival
 */
public class PregnantWoman extends Passenger {
    public PregnantWoman() {
        super();
    }

    public void GiveBirth(int amount, EditorFrame editorFrame){
        int Babies = 0;
        for(int i = 0; i<amount; i++){
            int Activation = (int)(Math.random()*100);
            if(Activation <= 20){
                Babies++;
            }
        }
        if(Babies != 0){
            JOptionPane.showMessageDialog(null, Babies + " Babs were born on this flight talk about a baby boom!");
            if(Babies+editorFrame.getAircraft().getPassengers()>editorFrame.getInformationModel().getPassengersSeats()){
                JOptionPane.showMessageDialog(null, "There was no place, so some babies were thrown off with parachutes!");
            }
        }
    }
}
