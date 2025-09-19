package nl.rug.oop.flaps.aircraft_editor.controller.actions;

import lombok.extern.java.Log;
import nl.rug.oop.flaps.aircraft_editor.model.Passengers.*;
import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;
import nl.rug.oop.flaps.simulation.model.aircraft.AircraftType;
import nl.rug.oop.flaps.simulation.model.aircraft.CargoArea;
import nl.rug.oop.flaps.simulation.model.cargo.CargoUnit;
import nl.rug.oop.flaps.simulation.model.world.WorldSelectionModel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * This action is invoked to perform the actual departure of an aircraft from an
 * airport.
 *
 * Use the setEnabled() method to enable/disable the corresponding button
 *
 * @author T.O.W.E.R.
 */
@Log
public class DepartAction extends AbstractAction {
    private final WorldSelectionModel selectionModel;
    private final EditorFrame editorFrame;
    private final Karen karen = new Karen();
    private final Terrorist terrorist = new Terrorist();
    private final PregnantWoman pregnantWoman = new PregnantWoman();
    private final RowdyBogan rowdyBogan = new RowdyBogan();
    private final Thief thief = new Thief();
    private final int[] array;

    public DepartAction(WorldSelectionModel selectionModel, EditorFrame editorFrame, int[] array) {
        super("Depart");
        this.selectionModel = selectionModel;
        this.editorFrame = editorFrame;
        this.array = array;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        var sm = this.selectionModel; // Just to keep things succinct.
        if (sm.getSelectedAirport() != null && sm.getSelectedAircraft() != null && sm.getSelectedDestinationAirport() != null) {
            var aircraft = sm.getSelectedAircraft();
            terrorist.BoomFunny(array[0]);
            karen.AntiMask(array[4], editorFrame);
            rowdyBogan.FightLel(array[3]);
            thief.SneakingThroughTheCracks(array[2]);
            pregnantWoman.GiveBirth(array[1], editorFrame);
            if (aircraft.getType().getTakeoffClipPath() != null) {
                this.playTakeoffClip(aircraft.getType());
            }
            var start = sm.getSelectedAirport();
            var end = sm.getSelectedDestinationAirport();
            start.removeAircraft(aircraft);
            end.addAircraft(aircraft);
            sm.setSelectedAirport(end);
            sm.setSelectedDestinationAirport(null);
            sm.setSelectedAircraft(aircraft);
            aircraft.removeFuel(aircraft.getFuelConsumption(start, end));
            HashMap<CargoArea, Set<CargoUnit>> loadedTypes = new HashMap<>(aircraft.getCargoAreaContents());
            Set<CargoArea> loadedTypesKey = loadedTypes.keySet();
            ArrayList<CargoArea> listOfLoadedKeys = new ArrayList<>(loadedTypesKey);
            for(CargoArea cargoArea: listOfLoadedKeys) {
                aircraft.setCargoAreaContents(cargoArea, null);
                cargoArea.setCurrentWeight(0);
            }
            editorFrame.dispatchEvent(new WindowEvent(editorFrame, WindowEvent.WINDOW_CLOSING));
        }
    }

    private void playTakeoffClip(AircraftType type) {
        new Thread(() -> {
            try (Clip clip = AudioSystem.getClip()) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(type.getTakeoffClipPath().toFile());
                clip.open(ais);
                clip.start();
                Thread.sleep((long) (ais.getFrameLength() / ais.getFormat().getFrameRate()) * 1000);
            } catch (Exception e) {
                log.warning("Could not play takeoff clip: " + e.getMessage());
            }
        }).start();
    }
}
