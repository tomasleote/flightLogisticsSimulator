package nl.rug.oop.flaps.aircraft_editor.controller.actions.listeners;

import nl.rug.oop.flaps.aircraft_editor.view.cargo.CargoAreaPopUP;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Slider Listener for Values.
 */

public class SlidderListener implements ChangeListener {
    private final CargoAreaPopUP cargoAreaPopUP;

    public SlidderListener(CargoAreaPopUP cargoAreaPopUP) {
        this.cargoAreaPopUP = cargoAreaPopUP;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        cargoAreaPopUP.getStatus().setText("Load/Delete " + ((JSlider)e.getSource()).getValue()
                + " kg of "+ cargoAreaPopUP.getCargoTypes().getSelectedItem() +" to the aircraft. ");
    }
}

