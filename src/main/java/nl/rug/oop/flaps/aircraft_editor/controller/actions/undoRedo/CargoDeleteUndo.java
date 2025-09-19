package nl.rug.oop.flaps.aircraft_editor.controller.actions.undoRedo;

import nl.rug.oop.flaps.aircraft_editor.view.cargo.CargoAreaPopUP;
import nl.rug.oop.flaps.simulation.model.aircraft.CargoArea;
import nl.rug.oop.flaps.simulation.model.cargo.CargoType;

import javax.swing.undo.AbstractUndoableEdit;

/**
 * Undo/redo for the deleting of cargo.
 */

    public class CargoDeleteUndo extends AbstractUndoableEdit {
        private final CargoAreaPopUP cargoAreaPopUP;

    /**
     * Construtor for the undo/redo
     * @param cargoAreaPopUP Panel of the pop up
     */
    public CargoDeleteUndo(CargoAreaPopUP cargoAreaPopUP) {
            this.cargoAreaPopUP = cargoAreaPopUP;
            cargoAreaPopUP.getAircraft().getUndoManager().addEdit(this);
            CargoType cargoType = cargoAreaPopUP.getCargoTypeMap().get(cargoAreaPopUP.getCargoTypes().getSelectedItem());
            cargoAreaPopUP.getAircraft().deleteCargo(cargoAreaPopUP.getCargoArea(), cargoType, cargoAreaPopUP.getEditorFrame().getSelectionModel().getSelectedAirport());
            cargoAreaPopUP.getEditorFrame().getInformationPanel().updateInformationPanel();
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
            CargoArea cargoArea = cargoAreaPopUP.getCargoArea();
            int cargoKG = cargoAreaPopUP.getSlider().getValue();
            if (!cargoAreaPopUP.getAircraft().addCargo(cargoArea, cargoType, cargoKG)) {
                cargoAreaPopUP.warningWeight();
            } else { cargoAreaPopUP.updatePopUpInfo(); }
            cargoAreaPopUP.getEditorFrame().getInformationPanel().updateInformationPanel();
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
            cargoAreaPopUP.getAircraft().deleteCargo(cargoAreaPopUP.getCargoArea(), cargoType, cargoAreaPopUP.getEditorFrame().getSelectionModel().getSelectedAirport());
            cargoAreaPopUP.getEditorFrame().getInformationPanel().updateInformationPanel();
            cargoAreaPopUP.getEditorFrame().updatePanels();
            cargoAreaPopUP.getEditorFrame().ReadyForDeparture();
        }
}
