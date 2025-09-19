package nl.rug.oop.flaps.aircraft_editor.controller.actions.listeners;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.flaps.aircraft_editor.view.cargo.CargoAreaPopUP;
import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;
import nl.rug.oop.flaps.simulation.model.aircraft.Aircraft;
import nl.rug.oop.flaps.simulation.model.aircraft.CargoArea;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class is the listener that opens the Load cargo frame when clicked on the Load Cargo button
 */
public class OpenLoadCargo extends AbstractAction implements PropertyChangeListener {

    private final Aircraft aircraft;
    @Setter
    private CargoArea cargoArea;
    private final EditorFrame editorFrame;
    @Getter
    private CargoAreaPopUP areaPopUP;

    public OpenLoadCargo(Aircraft aircraft, CargoArea cargoArea, EditorFrame editorFrame) {  //Problem with cargoArea= null starts here
        super("Load Cargo");
        this.aircraft = aircraft;
        this.cargoArea = cargoArea;
        this.editorFrame = editorFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            if (cargoArea == null){
                JOptionPane.showMessageDialog(null, "No cargo area has been selected!");
            }else areaPopUP = new CargoAreaPopUP(aircraft, cargoArea, editorFrame);

        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
