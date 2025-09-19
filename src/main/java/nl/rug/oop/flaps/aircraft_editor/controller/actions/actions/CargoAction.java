package nl.rug.oop.flaps.aircraft_editor.controller.actions.actions;

import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;
import nl.rug.oop.flaps.aircraft_editor.view.cargo.CargoAreaPopUP;
import nl.rug.oop.flaps.simulation.model.aircraft.Aircraft;
import nl.rug.oop.flaps.simulation.model.aircraft.CargoArea;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action to open a new Pop Up
 */
public class CargoAction extends AbstractAction {

   private Aircraft aircraft;
   private CargoArea cargoArea;
   private EditorFrame editorFrame;

   public void CargoAction(Aircraft aircraft, CargoArea cargoArea, EditorFrame editorFrame) {
        this.aircraft = aircraft;
        this.cargoArea = cargoArea;
        this.editorFrame = editorFrame;
   }


    @Override
    public void actionPerformed(ActionEvent e) { new CargoAreaPopUP(aircraft, cargoArea, editorFrame); }
}
