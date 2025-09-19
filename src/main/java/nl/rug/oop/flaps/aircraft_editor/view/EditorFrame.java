package nl.rug.oop.flaps.aircraft_editor.view;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.*;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.undoRedo.Redo;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.undoRedo.Undo;
import nl.rug.oop.flaps.aircraft_editor.model.InformationModel;
import nl.rug.oop.flaps.aircraft_editor.view.cargo.CargoPanel;
import nl.rug.oop.flaps.aircraft_editor.view.panels.*;
import nl.rug.oop.flaps.simulation.model.aircraft.Aircraft;
import nl.rug.oop.flaps.simulation.model.aircraft.CargoArea;
import nl.rug.oop.flaps.simulation.model.aircraft.FuelTank;
import nl.rug.oop.flaps.simulation.model.world.WorldSelectionModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;

/**
 * The main frame in which the editor should be displayed.
 * @author T.O.W.E.R.
 */
@Getter
public class EditorFrame extends JFrame {
    //Following lines are necessary so that we dont have magic numbers and we can easily change the size of our components
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 650;
    private static final int PADDING = 5;
    private int index = 0;
    private double lengthofplane;
    private Image image;


    //Here we initialize arguments of the view that need to be used outside of the EditorFrame method
    @Setter
    private final FuelPanel Fuelpanel = new FuelPanel(this);
    @Setter
    private CargoArea selectedCargoArea;
    @Setter
    private FuelTank selectedFuelTank;
    private InformationPanel informationPanel;
    private JPanel dataJPanel;
    private final Aircraft aircraft;
    private final WorldSelectionModel selectionModel;
    private final InformationModel informationModel;
    private BlueprintPanel blueprintPanel;
    private final JButton departure = new JButton("Depart the Aircraft");
    private CargoPanel Cargopanel;

    /**
     * This method initialises the Editor frame and all the components inside the frame. It constructs our view.
     * @param aircraft We need this parameter to pass it to this class so that it can be used in the above 2 methods and
     *                 on this one to build information panel and the load Cargo button.
     * @param selectionModel We need this parameter to pass it to our Information panel
     * @throws IOException Exception for when the user inputs improper data into the program
     */
    public EditorFrame(Aircraft aircraft, WorldSelectionModel selectionModel) throws IOException, NullPointerException {
        super("Aircraft Editor");
        this.selectionModel = selectionModel;
        this.aircraft = aircraft;
        this.informationModel = new InformationModel(aircraft,selectionModel, this);
        selectedCargoArea = null;
        selectedFuelTank = null;
        setBlueprint();
        initView();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initView() {

        JMenuBar jMenuBar = new JMenuBar();
        JMenu menubar = new JMenu("Undo/Redo");
        JMenuItem Redo = new JMenuItem(new Redo("Redo", this));
        JMenuItem Undo = new JMenuItem(new Undo("Undo", this));
        JSplitPane blueprint = new JSplitPane();

        initDataPanel();
        initFrameSettings();
        ReadyForDeparture();

        //Following lines add the panels to the splitPane
        blueprint.setLeftComponent(blueprintPanel);
        blueprint.setRightComponent(dataJPanel);
        blueprint.setDividerLocation(350);
        add(blueprint);
        //Adding menus to the bar
        menubar.add(Undo);
        menubar.add(Redo);
        jMenuBar.add(menubar);
        this.setJMenuBar(jMenuBar);
    }

    private void initDataPanel() {
        //Initialize Cargopanel
        this.Cargopanel = new CargoPanel(aircraft, informationModel,  this);
        //Following lines initialize the view components
        this.informationPanel = new InformationPanel(aircraft, selectionModel, this);
        //get Fuel, Datapanel and CargoPanel to add them into the DataPanel
        JPanel fuelJPanel = Fuelpanel.getFuelPanel();
        JPanel informationJPanel = informationPanel.getInformationPanel();
        JPanel cargoJPanel = Cargopanel.getCargoPanel();

        //Initialize Datapanel and get datapanel
        DataPanel Datapanel = new DataPanel(cargoJPanel, fuelJPanel, informationJPanel);
        this.dataJPanel = Datapanel.getDatapanel();
    }

    private void initFrameSettings() {
        //Following lines set up the size and layout of each panel
        setLayout(new BorderLayout(1,1));
        //Following lines set up the borders of the panels in the frame
        this.blueprintPanel = new BlueprintPanel(this);
        blueprintPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Blueprint: "));
        //Following lines set up the layout, size and operation to perform when the close button is clicked
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));


    }

    private void setBlueprint () {
        if (aircraft.getType().getName().equals("Grand Caravan 208B")) {
            lengthofplane = 9;
            try {
                image = ImageIO.read(Path.of("data", "aircraft_types", "general_aviation", "grand_caravan", "blueprint.png").toFile());
            } catch (IOException e) {
                System.out.println("Image not found!");
            }
        }

        if (aircraft.getType().getName().equals("Boeing 737-800BCF Freighter")) {
            lengthofplane = 42;
            try {
                image = ImageIO.read(Path.of("data", "aircraft_types", "jets", "737", "blueprint.png").toFile());
            } catch (IOException e) {
                System.out.println("Image not found!");
            }
        }
        if (aircraft.getType().getName().equals("Boeing 747-400F")) {
            lengthofplane = 70.66;
            try {
                image = ImageIO.read(Path.of("data", "aircraft_types", "jets", "747", "blueprint.png").toFile());
            } catch (IOException e) {
                System.out.println("Image not found!");
            }
        }
    }

    public void createMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void ReadyForDeparture() throws NullPointerException{
        double AbsoluteDifference = (aircraft.getType().getEmptyCgX() - informationModel.getFullCenterOfGravityX())+(aircraft.getType().getEmptyCgY() - informationModel.getFullCenterOfGravityY());
        if(selectionModel.getSelectedDestinationAirport() == null){
            createMessageDialog(" Please select a Destination!");
        }
        if(selectionModel.getSelectedDestinationAirport().canAcceptIncomingAircraft() &&
                informationPanel.getInformationmodel().getRange() >= selectionModel.getSelectedAirport().getGeographicCoordinates().distanceTo(selectionModel.getSelectedDestinationAirport().getGeographicCoordinates())/1000
                && AbsoluteDifference/aircraft.getType().getLength() < aircraft.getType().getCgTolerance()
                && informationModel.getTotalWeight() <= aircraft.getType().getMaxTakeoffWeight()
                && index != 0){
            informationPanel.getDeparture().setVisible(true);
            DepartAction departAction = new DepartAction(selectionModel, this, informationModel.getArrayOfSpecial());
            informationPanel.getDeparture().addActionListener(departAction);
        }
        if(informationPanel.getInformationmodel().getRange() < selectionModel.getSelectedAirport().getGeographicCoordinates().distanceTo(selectionModel.getSelectedDestinationAirport().getGeographicCoordinates())/1000
                || AbsoluteDifference/aircraft.getType().getLength() > aircraft.getType().getCgTolerance()
                || informationModel.getTotalWeight() > aircraft.getType().getMaxTakeoffWeight()){
            informationPanel.getDeparture().setVisible(false);
            index = 1;
            DepartAction departAction = new DepartAction(selectionModel, this, informationModel.getArrayOfSpecial());
            informationPanel.getDeparture().removeActionListener(departAction);
        }
    }

    public void updatePanels() {
        //TODO: This method should update the panels inside this frame, should be called everytime view values are updated
        blueprintPanel.updateBlueprintPanel();
        informationPanel.updateInformationPanel();
        Cargopanel.updateCargoPanel(selectedCargoArea);
    }
}



