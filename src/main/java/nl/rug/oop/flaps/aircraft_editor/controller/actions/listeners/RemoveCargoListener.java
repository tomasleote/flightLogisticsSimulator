package nl.rug.oop.flaps.aircraft_editor.controller.actions.listeners;

import lombok.Setter;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.undoRedo.CargoDeleteUndo;
import nl.rug.oop.flaps.aircraft_editor.view.cargo.CargoAreaPopUP;
import nl.rug.oop.flaps.simulation.model.aircraft.CargoArea;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Listener class that deals with removing a cargo from our aircraft
 */
public class RemoveCargoListener extends AbstractAction {
    @Setter
    private CargoArea cargoArea;
    private final CargoAreaPopUP cargoAreaPopUP;

    public RemoveCargoListener(CargoAreaPopUP cargoAreaPopUP) {
        this.cargoAreaPopUP = cargoAreaPopUP;
        cargoArea = cargoAreaPopUP.getCargoArea();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new CargoDeleteUndo(cargoAreaPopUP);
    }
}
