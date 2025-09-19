package nl.rug.oop.flaps.aircraft_editor.controller.actions.listeners;

import lombok.Setter;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.undoRedo.CargoLoadUndo;
import nl.rug.oop.flaps.aircraft_editor.view.cargo.CargoAreaPopUP;
import nl.rug.oop.flaps.simulation.model.aircraft.Aircraft;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This class is the listener to our Load Cargo button
 */
public class LoadCargoListener extends AbstractAction {
    @Setter
    private CargoAreaPopUP cargoAreaPopUP;
    private final JSlider slider;
    private final Aircraft aircraft;


    /**
     * Constructor to our listener. We need the following parameters to update information's when cargo is loaded
     */
    public LoadCargoListener(CargoAreaPopUP cargoAreaPopUP, JSlider slider, Aircraft aircraft) {
        this.cargoAreaPopUP = cargoAreaPopUP;
        this.slider = slider;
        this.aircraft = aircraft;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new CargoLoadUndo(cargoAreaPopUP,aircraft,slider);
    }
}