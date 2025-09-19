package nl.rug.oop.flaps.aircraft_editor.controller.actions.undoRedo;


import nl.rug.oop.flaps.aircraft_editor.view.cargo.CargoAreaPopUP;
import nl.rug.oop.flaps.simulation.model.aircraft.Aircraft;
import nl.rug.oop.flaps.simulation.model.aircraft.CargoArea;
import javax.swing.*;
import javax.swing.undo.AbstractUndoableEdit;
import nl.rug.oop.flaps.simulation.model.cargo.CargoType;

/**
 * Undo/redo for the loading of cargo.
 */
    public class CargoLoadUndo extends AbstractUndoableEdit {
        private final CargoAreaPopUP cargoAreaPopUP;
        private final Aircraft aircraft;
        private final JSlider slider;


    /**
     * Constructor for the Cargoloading.
     * @param cargoAreaPopUP Panel
     * @param aircraft Current Aircraft
     * @param slider Slider for value
     */
        public CargoLoadUndo(CargoAreaPopUP cargoAreaPopUP, Aircraft aircraft, JSlider slider) {
            this.cargoAreaPopUP = cargoAreaPopUP;
            this.aircraft = aircraft;
            this.slider = slider;
            aircraft.getUndoManager().addEdit(this);
            CargoType cargoType = cargoAreaPopUP.getCargoTypeMap().get(cargoAreaPopUP.getCargoTypes().getSelectedItem());
            CargoArea cargoArea = cargoAreaPopUP.getCargoArea();
            int cargoKG = slider.getValue();
            if (!aircraft.addCargo(cargoArea, cargoType, cargoKG)) {
                cargoAreaPopUP.warningWeight();
            } else { cargoAreaPopUP.updatePopUpInfo();}
            cargoAreaPopUP.getEditorFrame().getInformationPanel().updateInformationPanel();
            cargoAreaPopUP.getEditorFrame().getCargopanel().initData(cargoArea);
            cargoAreaPopUP.getEditorFrame().updatePanels();
            cargoAreaPopUP.getEditorFrame().ReadyForDeparture();
        }

        /**
         * Undo
         */
        @Override
        public void undo(){
            super.undo();
            CargoType cargoType = cargoAreaPopUP.getCargoTypeMap().get(cargoAreaPopUP.getCargoTypes().getSelectedItem());
            cargoAreaPopUP.getAircraft().deleteCargo(cargoAreaPopUP.getCargoArea(), cargoType, cargoAreaPopUP.getEditorFrame().getSelectionModel().getSelectedAirport());
            cargoAreaPopUP.getEditorFrame().getInformationPanel().updateInformationPanel();
            cargoAreaPopUP.getEditorFrame().getCargopanel().initData(cargoAreaPopUP.getCargoArea());
            cargoAreaPopUP.getEditorFrame().updatePanels();
            cargoAreaPopUP.getEditorFrame().ReadyForDeparture();
        }

        /**
         * Redo
         */
        @Override
        public void redo(){
            super.redo();
            CargoType cargoType = cargoAreaPopUP.getCargoTypeMap().get(cargoAreaPopUP.getCargoTypes().getSelectedItem());
            CargoArea cargoArea = cargoAreaPopUP.getCargoArea();
            int cargoKG = slider.getValue();
            if (!aircraft.addCargo(cargoArea, cargoType, cargoKG)) {
                cargoAreaPopUP.warningWeight();
            } else { cargoAreaPopUP.updatePopUpInfo();}
            cargoAreaPopUP.getEditorFrame().getInformationPanel().updateInformationPanel();
            cargoAreaPopUP.getEditorFrame().getCargopanel().initData(cargoArea);
            cargoAreaPopUP.getEditorFrame().updatePanels();
            cargoAreaPopUP.getEditorFrame().ReadyForDeparture();
        }
    }

