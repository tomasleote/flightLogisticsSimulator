package nl.rug.oop.flaps.aircraft_editor.model.Passengers;

import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;

import javax.swing.*;
import java.util.Random;

/**
 * Subclass of the passenger class.
 * Anti-mask person that lengthens the flight time, increasing fuel expenditure by 2-14%
 */
public class Karen extends Passenger{

    public Karen() {
        super();
    }

    public void AntiMask(int amount, EditorFrame editorFrame){
        int KarenAmount = 0;
        double FuelFecked = 0;
        for(int i = 0; i<amount; i++){
            int Activation = (int)(Math.random()*100);
            if(Activation <= 20){
                KarenAmount++;
                Random random = new Random();
                FuelFecked+= random.nextDouble() * (14 - 2) + 2;
            }
        }
        if(KarenAmount != 0){
            JOptionPane.showMessageDialog(null, KarenAmount + "Karens have wasted " + FuelFecked + "% Fuel!");
            if(editorFrame.getInformationModel().getRange()+(FuelFecked/100+1)<editorFrame.getSelectionModel().getSelectedAirport().getGeographicCoordinates().distanceTo(editorFrame.getSelectionModel().getSelectedDestinationAirport().getGeographicCoordinates())/1000){
                JOptionPane.showMessageDialog(null, "The Short Haired Tyrants have won and destroyed you, your precious cargo and the cure for cancer!");
            }
        }
    }
}
