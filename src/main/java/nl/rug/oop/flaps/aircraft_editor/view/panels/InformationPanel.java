package nl.rug.oop.flaps.aircraft_editor.view.panels;

import lombok.Getter;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.listeners.LoadPassengers;
import nl.rug.oop.flaps.aircraft_editor.model.InformationModel;
import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;
import nl.rug.oop.flaps.simulation.model.aircraft.Aircraft;
import nl.rug.oop.flaps.simulation.model.world.WorldSelectionModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * This class builds the Information panel in our Editor Frame
 */
@Getter
public class InformationPanel extends JFrame {
    private static final int TABLE_WIDTH = 500;
    private static final int TABLE_HEIGHT = 140;
    private final static int INFOPANEL_WIDTH = 600;
    private final static int INFOPANEL_HEIGHT = 300;
    private final Aircraft aircraft;
    private InformationModel informationmodel;
    private final WorldSelectionModel worldSelectionModel;
    private JTable informationTable;
    private final EditorFrame editorFrame;
    private JSlider slider;
    private JButton departure;
    @Getter
    private final JPanel informationPanel = new JPanel();

    /**
     * This method constructs the Information panel in our view
     * @param aircraft We need this parameter to pass it to our Information model
     * @param worldSelectionModel We need this parameter to pass it to our Information model
     */
    public InformationPanel(Aircraft aircraft, WorldSelectionModel worldSelectionModel, EditorFrame editorFrame) {
        this.editorFrame = editorFrame;
        this.aircraft = aircraft;
        this.worldSelectionModel = worldSelectionModel;
        initPanel();
    }

    private void initPanel() {

        //Creates the Informationmodel
        this.informationmodel = new InformationModel(aircraft, worldSelectionModel, editorFrame);

        //Following lines set up the size and border of the panel
        informationPanel.setPreferredSize(new Dimension(INFOPANEL_WIDTH, INFOPANEL_HEIGHT));
        informationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Information: "));

        JLabel status = new JLabel("Set number of passengers in the aircraft: ");
        JLabel passSeats = new JLabel("Available passengers seats: "+editorFrame.getInformationModel().getPassengersSeats());
        JLabel passengers = new JLabel("Passengers OnBoard: "+editorFrame.getInformationModel().getAircraft().getPassengers());
        JButton setPass= new JButton("Set Passengers");
        setPass.addMouseListener((MouseListener) new LoadPassengers(editorFrame.getInformationModel()));

        initSlider(status);
        initInformationTable(status, setPass);
    }

    private void initSlider(JLabel status) {
        //Following lines create a slider for the passengers
        DefaultBoundedRangeModel model = new DefaultBoundedRangeModel(0, 0, editorFrame.getInformationModel().getAircraft().getPassengers(),
                (int) editorFrame.getInformationModel().getPassengersSeats());
        this.slider = new JSlider(model);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                status.setText("Set " + ((JSlider)e.getSource()).getValue() + " passengers to the aircraft. ");
            }
        });
    }

    private void initInformationTable (JLabel status, JButton setPass) {
        //Creates the InformationTable
        departure = new JButton("Depart");
        String[] columnNames = {" Name", "Value"} ;
        Object [][] data = { {"Range of the plane: ", null} , {"Estimated cost journey: ", null},
                {"Estimated cargo revenue: ", null}, {"Estimated profit: ", null}, {"Total weight aircraft: ", null},
                {"Available Passengers seats: ", null}, {"Passengers onboard: ", null}};
        informationTable = new JTable(data, columnNames);
        informationTable.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        informationPanel.add(informationTable, BorderLayout.NORTH);
        informationPanel.add(departure, BorderLayout.EAST);
        informationPanel.add(status, BorderLayout.EAST);
        informationPanel.add(slider, BorderLayout.WEST);
        informationPanel.add(setPass, BorderLayout.SOUTH);
        initData();
    }

    /**
     * This method initialises the view of the Information panel data
     */
    public void initData (){
        informationmodel.informationUpdate();
        informationTable.getModel().setValueAt(informationmodel.getRange(),0,1);
        informationTable.getModel().setValueAt(informationmodel.getCost(),1,1);
        informationTable.getModel().setValueAt(informationmodel.getRevenue(),2,1);
        informationTable.getModel().setValueAt(informationmodel.getProfit(),3,1);
        informationTable.getModel().setValueAt(informationmodel.getTotalWeight(),4,1);
        informationTable.getModel().setValueAt(informationmodel.getPassengersSeats(), 5, 1 );
        informationTable.getModel().setValueAt(informationmodel.getAircraft().getPassengers(), 6, 1);
    }

    public void updateInformationPanel() { initData();}
}

