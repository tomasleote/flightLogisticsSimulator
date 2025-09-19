package nl.rug.oop.flaps.aircraft_editor.controller.actions.undoRedo;

import nl.rug.oop.flaps.aircraft_editor.model.InformationModel;


import javax.swing.undo.AbstractUndoableEdit;

/**
 * Passengers Undo/Redo
 */
public class PassengersUndo extends AbstractUndoableEdit {
    private final InformationModel informationModel;

    /**
     * Constructor the PassengersUndo
     * @param informationModel
     */
    public PassengersUndo(InformationModel informationModel) {
        this.informationModel = informationModel;
        informationModel.getAircraft().getUndoManager().addEdit(this);
        informationModel.LoadingPassengers(false);
        informationModel.getEditorFrame().getInformationPanel().updateInformationPanel();
    }

    /**
     * Undo
     */
    @Override
    public void undo(){
        super.undo();
        informationModel.LoadingPassengers(true);
        informationModel.getEditorFrame().getInformationPanel().updateInformationPanel();
    }

    /**
     * Redo
     */
    @Override
    public void redo(){
        super.redo();
        informationModel.LoadingPassengers(false);
        informationModel.getEditorFrame().getInformationPanel().updateInformationPanel();
    }
}
