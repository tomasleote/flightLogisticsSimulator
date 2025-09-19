package nl.rug.oop.flaps.aircraft_editor.controller.actions.listeners;

import lombok.Setter;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.undoRedo.FuelUndoRedo;
import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;
import nl.rug.oop.flaps.simulation.model.aircraft.FuelTank;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This method is the listener to the Load fuel button
 */

public class LoadFuelListener implements MouseListener{

    @Setter
    private FuelTank tank;
    private final EditorFrame editorFrame;
    private final FuelUndoRedo fuelUndoRedo;

    public LoadFuelListener(EditorFrame editorFrame) {
        tank = null;
        this.editorFrame = editorFrame;
        fuelUndoRedo = new FuelUndoRedo(editorFrame.getInformationModel());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        double liters = editorFrame.getFuelpanel().getSlider().getValue();
        if (editorFrame.getAircraft().checkWeight(liters, tank)) {
            fuelUndoRedo.setTank(tank);
            fuelUndoRedo.setPreviousLoadedFuel(liters);
            editorFrame.getAircraft().addFuel(tank, liters);
            editorFrame.getFuelpanel().updateFuelPanel();
            editorFrame.getInformationPanel().updateInformationPanel();
            editorFrame.ReadyForDeparture();
        } else {
            editorFrame.createMessageDialog(" This exceeds the fuel tank capacity!");
        }
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
