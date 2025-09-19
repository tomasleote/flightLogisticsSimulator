package nl.rug.oop.flaps.aircraft_editor.controller.actions.undoRedo;

import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Redo
 */
public class Redo extends AbstractAction {

    private final EditorFrame aircraftFrame;

    public Redo(String name, EditorFrame aircraftFrame) {
        super(name);
        this.aircraftFrame = aircraftFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var redo = aircraftFrame.getAircraft().getUndoManager();
        if (redo.canRedo()) {
            redo.redo();
        } else {
            JOptionPane.showMessageDialog(null, "Unable to Redo!!!");
        }
    }
}


