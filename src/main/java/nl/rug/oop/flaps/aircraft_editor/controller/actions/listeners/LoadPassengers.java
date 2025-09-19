package nl.rug.oop.flaps.aircraft_editor.controller.actions.listeners;

import nl.rug.oop.flaps.aircraft_editor.controller.actions.undoRedo.PassengersUndo;
import nl.rug.oop.flaps.aircraft_editor.model.InformationModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener for the loading of Passengers
 */
public class LoadPassengers implements MouseListener {


    private final InformationModel informationModel;
    public LoadPassengers(InformationModel informationModel){
        this.informationModel = informationModel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        new PassengersUndo(informationModel);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
