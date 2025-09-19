package nl.rug.oop.flaps.aircraft_editor.view.cargo;

import lombok.Getter;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.listeners.OpenLoadCargo;
import nl.rug.oop.flaps.aircraft_editor.model.InformationModel;
import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;
import nl.rug.oop.flaps.simulation.model.aircraft.Aircraft;
import nl.rug.oop.flaps.simulation.model.aircraft.CargoArea;
import javax.swing.*;
import java.awt.*;

@Getter
public class CargoPanel {

    private final static int TABLE_WIDTH = 190;
    private final static int TABLE_HEIGHT = 75;
    private JTable cargoTable;
    private final static int SUBPANEL_WIDTH = 195;
    private final static int SUBPANEL_HEIGHT = 150;
    private final JPanel cargoPanel = new JPanel();
    private OpenLoadCargo openLoadCargo;
    private final Aircraft aircraft;
    private final InformationModel informationModel;
    private final EditorFrame editorFrame;

    /**
     * Creates the CargoPanel View
     * @param aircraft Current Aircraft
     * @param informationModel Model of the cargoPanel
     * @param editorFrame Current state of editorframe
     */
    public CargoPanel(Aircraft aircraft, InformationModel informationModel, EditorFrame editorFrame){

        this.aircraft = aircraft;
        this.informationModel = informationModel;
        this.editorFrame = editorFrame;
        initPanel();

    }

    private void initPanel() {
        //Initialises the loadCargo button
        openLoadCargo = new OpenLoadCargo(aircraft, informationModel.getSelectedCargoArea(), editorFrame);
        JButton loadCargo = new JButton(openLoadCargo);

        initCargoAreaSettings();
        initCargoAreaTable();

        //Following lines add the cargoPanel components to the panel
        cargoPanel.add(this.cargoTable, BorderLayout.NORTH);
        cargoPanel.add(loadCargo, BorderLayout.SOUTH);
    }

    private void initCargoAreaTable() {
        //Following lines set up the cargo area table
        String [] cargoColumnNames = {"Name", "Value"};
        Object [][] cargoData ={ {"Name: ", null}, {"Weight Capacity: " , null}, {"Current Weight: ", null}};
        this.cargoTable = new JTable(cargoData, cargoColumnNames);
        this.cargoTable.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
    }

    private void initCargoAreaSettings() {
        //Sets size of the panel
        cargoPanel.setPreferredSize(new Dimension(SUBPANEL_WIDTH, SUBPANEL_HEIGHT));
        //Creates Borders around the Panel
        cargoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "CargoArea: "));
        //Cargolayout
        cargoPanel.setLayout(new BorderLayout(1,1));
    }

    /**
     * This method initialises the view of the cargo Panel
     * @param cargo We need this parameter to get the name and max weight of the aircraft
     */
    public void initData (CargoArea cargo) {
        openLoadCargo.setCargoArea(cargo); //view changes controller, not allowed
        cargoTable.getModel().setValueAt(cargo.getName(),0,1);
        cargoTable.getModel().setValueAt(cargo.getMaxWeight(),1,1);
        cargoTable.getModel().setValueAt(cargo.getCurrentWeight(),2,  1);
    }

    public void updateCargoPanel(CargoArea cargo) {
        this.initData(cargo);
    }
}
