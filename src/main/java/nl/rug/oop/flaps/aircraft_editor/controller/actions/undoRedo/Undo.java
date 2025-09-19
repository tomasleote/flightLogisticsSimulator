package nl.rug.oop.flaps.aircraft_editor.controller.actions.undoRedo;

import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Undo
 */
public class Undo extends AbstractAction {

    private final EditorFrame aircraftFrame;

    public Undo(String name, EditorFrame aircraftFrame) {
        super(name);
        this.aircraftFrame = aircraftFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var undo = aircraftFrame.getAircraft().getUndoManager();
        if (undo.canUndo()) {
            undo.undo();
        } else {
            JOptionPane.showMessageDialog(null, "Unable to Undo!!!");
        }
    }
}

