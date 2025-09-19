package nl.rug.oop.flaps.aircraft_editor.controller.actions.actions;

import lombok.Getter;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.listeners.LoadFuelListener;
import nl.rug.oop.flaps.aircraft_editor.model.InformationModel;
import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;
import nl.rug.oop.flaps.simulation.model.aircraft.*;
import nl.rug.oop.flaps.simulation.model.world.EditorModelListener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;


/**
 * This class is the controller that deals with selecting the cargo areas and fuel tanks that the user selects
 */
public class SelectionController extends MouseAdapter {

    private final int radius = 15;
    private final Aircraft aircraft;
    @Getter
    private final EditorFrame editorFrame;
    @Getter
    private final Set<EditorModelListener> cargoAreaListeners;
    @Getter
    private final InformationModel informationModel;
    private LoadFuelListener loadFuelListener;

    public SelectionController(Aircraft aircraft, EditorFrame editorFrame, InformationModel informationModel) {
        this.aircraft = aircraft;
        this.editorFrame = editorFrame;
        this.informationModel = informationModel;
        this.informationModel.setSelectionController(this);
        cargoAreaListeners = new HashSet<>();
        loadFuelListener = new LoadFuelListener( editorFrame);
    }

    private FuelTank selectFuelAreaByCoords(double x, double y) { //controller for each
        Point2D.Double clickedPosition = new Point2D.Double(x, y);
        for (FuelTank fuelTank: informationModel.getFuelTankArray()) {
            Point2D fuelTankCoords = fuelTank.getCoords();
            fuelTankCoords.setLocation((int) fuelTankCoords.getX() * (300 / editorFrame.getLengthofplane()) + 168,
                    (int) fuelTankCoords.getY() * (300 / editorFrame.getLengthofplane()) + 157);
            if (fuelTankCoords.distance(clickedPosition) < radius) {
                informationModel.setSelectedFuelTank(fuelTank);
                editorFrame.setSelectedFuelTank(fuelTank);
                editorFrame.getFuelpanel().initData(fuelTank,aircraft);
                loadFuelListener.setTank(fuelTank);
                editorFrame.getFuelpanel().setConfirm(loadFuelListener);
                editorFrame.getBlueprintPanel().selectedAreaPointUpdated();
                return fuelTank;
            }
        }return null;
    }

    private CargoArea selectCargoAreaByCoords (double x, double y) {
        Point2D.Double clickedPosition = new Point2D.Double(x, y);
        for (CargoArea cargoArea : informationModel.getCargoAreaArray()) {
            Point2D cargoAreaCoords = cargoArea.getCoords();
            cargoAreaCoords.setLocation((int) cargoAreaCoords.getX() * (300 / editorFrame.getLengthofplane()) + 168,
                    (int) cargoAreaCoords.getY() * (300 / editorFrame.getLengthofplane()) + 157);
            if (cargoAreaCoords.distance(clickedPosition) < radius) {
                informationModel.setSelectedCargoArea(cargoArea);
                editorFrame.setSelectedCargoArea(cargoArea);
                editorFrame.getCargopanel().initData(cargoArea);
                editorFrame.getBlueprintPanel().selectedAreaPointUpdated();
                return cargoArea;
            }
        } return null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        SwingUtilities.invokeLater(() ->{
            var clickedCargoArea = selectCargoAreaByCoords(e.getX(), e.getY());
            if (clickedCargoArea==null) {
                var clickedFuelTank = selectFuelAreaByCoords(e.getX(), e.getY());
            }
        });
    }
}
