package nl.rug.oop.flaps.simulation.controller.actions;

import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;
import nl.rug.oop.flaps.simulation.model.aircraft.Aircraft;
import nl.rug.oop.flaps.simulation.model.world.WorldSelectionModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 *
 * @author T.O.W.E.R.
 */
public class OpenAircraftConfigurer extends AbstractAction implements PropertyChangeListener {

    private final Aircraft aircraft;
    private final WorldSelectionModel selectionModel;

    public OpenAircraftConfigurer(Aircraft aircraft, WorldSelectionModel selectionModel) {
        super("Configure");
        this.aircraft = aircraft;
        this.selectionModel = selectionModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SwingUtilities.invokeLater(() -> {
            try {
                new EditorFrame(aircraft, selectionModel);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (NullPointerException nullPointerException) {}
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
