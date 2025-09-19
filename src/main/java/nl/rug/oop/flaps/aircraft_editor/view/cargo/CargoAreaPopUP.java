package nl.rug.oop.flaps.aircraft_editor.view.cargo;

import lombok.Getter;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.listeners.LoadCargoListener;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.listeners.RemoveCargoListener;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.listeners.SlidderListener;
import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;
import nl.rug.oop.flaps.simulation.model.aircraft.Aircraft;
import nl.rug.oop.flaps.simulation.model.aircraft.CargoArea;
import nl.rug.oop.flaps.simulation.model.cargo.CargoType;
import nl.rug.oop.flaps.simulation.model.cargo.CargoUnit;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * PopUp frame that is displayed when the user clicks on the Load Cargo button in the editor frame
 */
@Getter
public class CargoAreaPopUP extends JFrame {

    //Following lines are the private arguments of the class
    private final int PANELHEIGHT = 205;
    private final int PANELWIDTH = 800;
    private JSlider slider;
    private final CargoArea cargoArea;
    private final EditorFrame editorFrame;
    private final Aircraft aircraft;
    private final Map<String, CargoType> cargoTypeMap;
    private JComboBox cargoTypes;
    private  JPanel cargoList;
    private JLabel addCargo;
    private JLabel status;
    private JButton load;
    private JButton delete;

    /**
     * This method initializes the view of the pop-up menu that shows up when
     * the Load Cargo button is clicked
     * @throws HeadlessException
     */
    public CargoAreaPopUP(Aircraft aircraft, CargoArea cargoArea, EditorFrame editorFrame) throws HeadlessException {
        super("Cargo Load Menu: ");
        this.aircraft = aircraft;
        this.editorFrame = editorFrame;
        this.cargoArea = cargoArea;
        cargoTypeMap = aircraft.getWorld().getCargoTypes();
        initFrame();
    }

    /**
     * This method initializes the CargoAreaPopUp frame
     */
    private void initFrame() {
        cargoList = new JPanel();
        load = new JButton("Load Cargo");
        delete = new JButton("Delete Cargo");
        addCargo = new JLabel("Select cargo to add/delete: ");
        status = new JLabel("Kg of cargo the be added: ");
        cargoTypes = new JComboBox(cargoTypeMap.keySet().toArray());

        //Following lines set up the slider of this frame
        DefaultBoundedRangeModel model = new DefaultBoundedRangeModel(0,5, 0,
                (int) editorFrame.getInformationModel().getSelectedCargoArea().getMaxWeight());
        slider = new JSlider(model);
        slider.addChangeListener(new SlidderListener(this));

        initGroupLayout();
        initLoadRemoveListeners();
        initPanelSettings();
    }

    /**
     * Small method that initializes the size, borders, and other settings of the panel
     */
    public void initPanelSettings () {
        cargoList.setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
        cargoList.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Cargo List"));
        add(cargoList);
        setVisible(true);
        setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();

    }

    /**
     * Small method that initializes and load the load and remove cargo listeners
     */
    public void initLoadRemoveListeners() {
        LoadCargoListener loadCargoListener = new LoadCargoListener(this, slider, aircraft);
        RemoveCargoListener removeCargoListener = new RemoveCargoListener(this);
        load.addActionListener(loadCargoListener);
        delete.addActionListener(removeCargoListener);
    }

    /**
     * Small method that initializes the GroupLayout of the panel
     */
    public void initGroupLayout() {
        GroupLayout layout = new GroupLayout(cargoList);
        cargoList.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(addCargo).addComponent(status)
                        .addComponent(load))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(cargoTypes).addComponent(slider)
                        .addComponent(delete)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(addCargo).addComponent(cargoTypes))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(status).addComponent(slider))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(load)).addComponent(delete)
        );

    }

    /**
     * This method shows a warning pop up message to inform the user that adding such cargo will exceed the max weight
     */
    public void warningWeight () {
        JOptionPane.showMessageDialog(null, "This exceeds the max weight! Try again. ");
    }

    public void updatePopUpInfo() {
        editorFrame.updatePanels();
    }

}
