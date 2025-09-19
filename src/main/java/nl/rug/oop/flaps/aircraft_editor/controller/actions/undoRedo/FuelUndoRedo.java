package nl.rug.oop.flaps.aircraft_editor.controller.actions.undoRedo;

import lombok.Setter;
import nl.rug.oop.flaps.aircraft_editor.model.InformationModel;
import nl.rug.oop.flaps.simulation.model.aircraft.FuelTank;
import javax.swing.undo.AbstractUndoableEdit;


/**
 * Undo/redo for Fuel
 */
public class FuelUndoRedo extends AbstractUndoableEdit {
    @Setter
    private FuelTank tank;
    private final InformationModel informationModel;
    @Setter
    private double previousLoadedFuel;

    /**
     * Constructor for Fuel
     * @param informationModel The model with
     */
    public FuelUndoRedo(InformationModel informationModel) {
        this.informationModel = informationModel;
        informationModel.getAircraft().getUndoManager().addEdit(this);
    }

    /**
     * Undo
     */
    @Override
    public void undo(){
        super.undo();
        informationModel.getAircraft().addFuel(tank, -previousLoadedFuel);
        updateView();
    }

    /**
     * Redo
     */
    @Override
    public void redo(){
        super.redo();
        informationModel.getAircraft().addFuel(tank, previousLoadedFuel);
        updateView();
    }

    /**
     * Observer for the View
     */
    public void updateView() {
        informationModel.getEditorFrame().getFuelpanel().updateFuelPanel();
        informationModel.getEditorFrame().getInformationPanel().updateInformationPanel();
        informationModel.getEditorFrame().ReadyForDeparture();
        informationModel.getEditorFrame().updatePanels();
    }
}


