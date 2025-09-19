package nl.rug.oop.flaps.aircraft_editor.view.panels;

import lombok.SneakyThrows;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.actions.SelectionController;
import nl.rug.oop.flaps.aircraft_editor.model.InformationModel;
import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;
import nl.rug.oop.flaps.simulation.model.world.EditorModelListener;
import nl.rug.oop.flaps.simulation.model.aircraft.Aircraft;
import nl.rug.oop.flaps.simulation.model.aircraft.CargoArea;
import nl.rug.oop.flaps.simulation.model.aircraft.FuelTank;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * The blueprint panel that display the aircraft blueprint
 */
public class BlueprintPanel extends JPanel implements EditorModelListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 650;
    private final int POINT_SIZE = 14;

    private final EditorFrame editorFrame;
    private final InformationModel informationModel;

    /**
     * Constructor of the class
     * @param editorFrame
     */
    public BlueprintPanel(EditorFrame editorFrame) {
        this.editorFrame = editorFrame;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        informationModel = editorFrame.getInformationModel();
        SelectionController selectionController = new SelectionController(informationModel.getAircraft(),editorFrame, informationModel);
        addMouseMotionListener(selectionController);
        addMouseListener(selectionController);
    }

    /**
     * This method draws the blueprint of the aircraft on the panel.
     * @param g2d parameter that contains the corresponding blueprint of the aircraft
     */
    private void drawImage(Graphics2D g2d){
        g2d.drawImage(editorFrame.getImage(), 25, 150, 300, 300, null);
    }

    /**
     * This method, as the name says, draws the fuel and cargo areas points
     * @param g2d points to be drawn in the blueprint
     */
    private void drawPoints(Graphics2D g2d) throws IOException {
        drawFuelAreas(g2d);
        g2d.setColor(Color.GREEN);
        g2d.fillRect( informationModel.getEmptyCenterOfGravityX() *(300/(int) editorFrame.getLengthofplane()) + 168,
                informationModel.getEmptyCenterOfGravityY() *
                        (300/ (int) editorFrame.getLengthofplane())+157, POINT_SIZE, POINT_SIZE);

        g2d.setColor(Color.CYAN);
        g2d.fillRect( (int) informationModel.getFullCenterOfGravityX() *(300/(int) editorFrame.getLengthofplane()) + 168,
                (int) informationModel.getFullCenterOfGravityY()*(300/ (int) editorFrame.getLengthofplane())+157,
                POINT_SIZE, POINT_SIZE);
        drawCargoAreas(g2d);
    }

    /**
     * This method that is called inside the drawPoints will handle the drawing of FuelTankAreas
     * @param graphics2D
     */
    private void drawFuelAreas(Graphics2D graphics2D) {
        List<FuelTank> fuelTankList = this.editorFrame.getAircraft().getType().getFuelTanks();

        for (FuelTank fuelTank : fuelTankList) {
            if(informationModel.getSelectedFuelTank()!=null && informationModel.getSelectedFuelTank().equals(fuelTank)) {
                graphics2D.setColor(Color.RED);
            } else { graphics2D.setColor(Color.BLUE); }
            Point2D point = fuelTank.getCoords();
            point.setLocation((int) point.getX()*(300/editorFrame.getLengthofplane()) + 168,
                    (int) point.getY()*(300/editorFrame.getLengthofplane())+157);
            graphics2D.fillOval((int) point.getX(), (int) point.getY(), POINT_SIZE, POINT_SIZE);

        }
    }

    /**
     * This method that is called inside the drawPoints will handle the drawing of CargoAreas
     * @param graphics2D
     */
    private void drawCargoAreas (Graphics2D graphics2D) {
        List<CargoArea> cargoAreaList = this.editorFrame.getAircraft().getType().getCargoAreas();
        for (CargoArea cargoArea : cargoAreaList) {
            if (informationModel.getSelectedCargoArea()!=null && informationModel.getSelectedCargoArea().equals(cargoArea)) {
                graphics2D.setColor(Color.RED);
            } else {graphics2D.setColor(Color.darkGray); }
            Point2D point = cargoArea.getCoords();
            point.setLocation((int) point.getX()*(300/editorFrame.getLengthofplane()) + 168,
                    (int) point.getY()*(300/editorFrame.getLengthofplane())+157);
            graphics2D.fillOval((int) point.getX(), (int) point.getY(), POINT_SIZE, POINT_SIZE);
        }
    }

    @SneakyThrows
    @Override
    protected void paintComponent(Graphics G){
        super.paintComponent(G);
        Graphics2D graphics2D = (Graphics2D) G;
        drawImage(graphics2D);
        drawPoints(graphics2D);
    }

    public void selectedAreaPointUpdated() {
            repaint();
    }

    public void updateBlueprintPanel() {repaint(); }
}
