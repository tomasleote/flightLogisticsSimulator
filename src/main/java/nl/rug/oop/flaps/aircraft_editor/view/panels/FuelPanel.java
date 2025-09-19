package nl.rug.oop.flaps.aircraft_editor.view.panels;

import lombok.Getter;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.listeners.LoadFuelListener;
import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;
import nl.rug.oop.flaps.simulation.model.aircraft.Aircraft;
import nl.rug.oop.flaps.simulation.model.aircraft.FuelTank;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Frame that contains the fuelpanel displayed on the editor frame
 */
@Getter
public class FuelPanel extends JFrame {

    //Following lines set up the dimension of the class components so that we dont have magic numbers
    private static final int TABLE_WIDTH = 190;
    private static final int TABLE_HEIGHT = 75;
    private final static int SUBPANEL_WIDTH = 195;
    private final static int SUBPANEL_HEIGHT = 200;

    //Following lines initialise the frame components
    private  JPanel fuelPanel;
    private JTable fuelTable;
    private JButton confirm;
    private JSlider slider;
    private final EditorFrame editorFrame;

    /**
     *  Constructor of the fuelPanel
     */
    public FuelPanel(EditorFrame editorFrame){
        initPanel();
        this.editorFrame = editorFrame;
    }

    /**
     * Initializes the fuelPanel view
     */
    private void initPanel() {
        fuelPanel = new JPanel();
        fuelPanel.setLayout(new BorderLayout(1,1));
        fuelPanel.setPreferredSize(new Dimension(SUBPANEL_WIDTH, SUBPANEL_HEIGHT));
        fuelPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "FuelTank: "));
        this.confirm = new JButton("Load Fuel");
        JLabel status = new JLabel("Add fuel to the aircraft: ");

        initSlider(status);
        initFuelTable();

        //Following lines add the components to the panel
        fuelPanel.add(fuelTable, BorderLayout.NORTH);
        fuelPanel.add(slider, BorderLayout.EAST);
        fuelPanel.add(status, BorderLayout.WEST);
        fuelPanel.add(confirm, BorderLayout.PAGE_END);
    }

    private void initSlider(JLabel status) {
        //Following lines initialise the slider and listener for the slider
        DefaultBoundedRangeModel model = new DefaultBoundedRangeModel(0, 0, 0, 8000);
        this.slider = new JSlider(model);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                status.setText("Load " + ((JSlider)e.getSource()).getValue() + " liters of fuel to the aircraft. ");
            }
        });
    }

    private void initFuelTable () {
        //Following lines initialise the fuelTable components
        String[] columnNames = {" Name", "Value"} ;
        Object [][] data = { {"Name: ", null} , {"Max Capacity: ", null}, {"Amount: ", null}};
        this.fuelTable = new JTable(data, columnNames);
        fuelTable.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
    }


    public void initData(FuelTank tank, Aircraft aircraft){
        this.getFuelTable().getModel().setValueAt(tank.getName(),0,1);
        this.getFuelTable().getModel().setValueAt(tank.getCapacity(),1,1);
        this.getFuelTable().getModel().setValueAt(aircraft.getFuelAmountForFuelTank(tank),2,1);
    }

    /**
     * Method that adds listener to the Load fuel button
     * @param fuelListener
     */
    public void setConfirm(LoadFuelListener fuelListener){
        confirm.removeMouseListener((MouseListener) fuelListener);
        confirm.addMouseListener((MouseListener) fuelListener);
    }

    public void updateFuelPanel() {
        this.initData(editorFrame.getInformationModel().getSelectedFuelTank(), editorFrame.getAircraft());
        editorFrame.getInformationPanel().initData();
    }

}
