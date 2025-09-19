package nl.rug.oop.flaps.aircraft_editor.model;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.flaps.aircraft_editor.controller.actions.actions.SelectionController;
import nl.rug.oop.flaps.aircraft_editor.model.Passengers.*;
import nl.rug.oop.flaps.aircraft_editor.view.EditorFrame;
import nl.rug.oop.flaps.simulation.model.aircraft.Aircraft;
import nl.rug.oop.flaps.simulation.model.aircraft.CargoArea;
import nl.rug.oop.flaps.simulation.model.aircraft.FuelTank;
import nl.rug.oop.flaps.simulation.model.airport.Airport;
import nl.rug.oop.flaps.simulation.model.cargo.CargoUnit;
import nl.rug.oop.flaps.simulation.model.world.WorldSelectionModel;
import javax.swing.*;
import java.util.*;

/**
 * This class is the model to the information panel
 */
@Getter
public class InformationModel {

    @Setter
    private FuelTank selectedFuelTank;
    @Setter
    private CargoArea selectedCargoArea;
    @Setter
    private SelectionController selectionController;
    private final Aircraft aircraft;
    private final EditorFrame editorFrame;
    private double range;
    private double cost;
    private double revenue;
    private double profit;
    private final double passengersSeats;
    private final WorldSelectionModel worldSelectionModel;
    private final Airport airport;
    private final Airport destination;
    private double totalWeight;
    private double FullCenterOfGravityY;
    private double FullCenterOfGravityX;
    private final int EmptyCenterOfGravityX;
    private final int EmptyCenterOfGravityY;
    private final ArrayList<FuelTank> fuelTankArray = new ArrayList<>();
    private final ArrayList<CargoArea> cargoAreaArray = new ArrayList<>();
    private final int[] ArrayOfSpecial = new int[5];
    private int[] ArrayOflastSpecial = new int[5];

    /**
     * Constructor of the class
     * @param aircraft Aircraft selected by user
     * @param worldSelectionModel Model of the world where the aircrafts is
     */
    public InformationModel(Aircraft aircraft, WorldSelectionModel worldSelectionModel, EditorFrame editorFrame) {
        this.editorFrame = editorFrame;
        this.aircraft = aircraft;
        this.worldSelectionModel = worldSelectionModel;
        this.airport = worldSelectionModel.getSelectedAirport();
        this.destination = worldSelectionModel.getSelectedDestinationAirport();
        EmptyCenterOfGravityX = (int) aircraft.getType().getEmptyCgX();
        EmptyCenterOfGravityY = (int) aircraft.getType().getEmptyCgY();

        revenue = 0;
        range = (aircraft.getTotalFuel()/aircraft.getType().getFuelConsumption()) * aircraft.getType().getCruiseSpeed();
        cost = airport.getFuelPriceByType(aircraft.getType().getFuelType())*aircraft.getTotalFuel();
        profit = revenue - cost;
        passengersSeats = Math.floor((aircraft.getType().getMaxTakeoffWeight() - aircraft.getType().getEmptyWeight())/300);

        initAreaArrays();
        Centerofgravity();
    }

    private void initAreaArrays() {
        cargoAreaArray.addAll(aircraft.getType().getCargoAreas());
        fuelTankArray.addAll(aircraft.getType().getFuelTanks());
    }

    /**
     * Calculates the Centerofgravity
     */
    private void Centerofgravity(){
        totalWeight = aircraft.getType().getEmptyWeight();
        FullCenterOfGravityX = aircraft.getType().getEmptyCgX()* aircraft.getType().getEmptyWeight();
        FullCenterOfGravityY = aircraft.getType().getEmptyCgY()*aircraft.getType().getEmptyWeight();
        for (FuelTank fuelTank: fuelTankArray) {
            FullCenterOfGravityX += (aircraft.getFuelAmountForFuelTank(fuelTank)*aircraft.getType().getFuelType().getDensity()) * fuelTank.getX();
            FullCenterOfGravityY += (aircraft.getFuelAmountForFuelTank(fuelTank)*aircraft.getType().getFuelType().getDensity()) * fuelTank.getY();
            totalWeight += aircraft.getFuelAmountForFuelTank(fuelTank)*aircraft.getType().getFuelType().getDensity();
        }
        for (CargoArea cargoArea : cargoAreaArray) {
            FullCenterOfGravityX += cargoArea.getCurrentWeight() * cargoArea.getX();
            FullCenterOfGravityY += cargoArea.getCurrentWeight() * cargoArea.getY();
            totalWeight += cargoArea.getCurrentWeight();
        }
        FullCenterOfGravityX = FullCenterOfGravityX/totalWeight;
        FullCenterOfGravityY = FullCenterOfGravityY/totalWeight;
    }

    /**
     * Loads passengers on a plane, Use random number generator to select what passengers board.
     * @param Undo Bool to check if undo needs to be done or not
     */
    public void LoadingPassengers(boolean Undo) {
        if (passengersSeats < aircraft.getPassengers() + editorFrame.getInformationPanel().getSlider().getValue()) {
            editorFrame.createMessageDialog("This exceeds the passenger capacity! Try again");
        } else {
            if(Undo){
                aircraft.AddPassengers(editorFrame.getInformationPanel().getSlider().getValue()*-1);
                for (int i = 0; i < 5; i++){
                    ArrayOfSpecial[i]-=ArrayOflastSpecial[i];
                }
            } else {
                ArrayOflastSpecial = new int[5];
                aircraft.AddPassengers(editorFrame.getInformationPanel().getSlider().getValue());
                for (int i = 0; i < editorFrame.getInformationPanel().getSlider().getValue(); i++) {
                    int Random = (int) (Math.random() * 100);
                    switch (Random / 5) {
                        case 0:
                            Terrorist terrorist1 = new Terrorist();
                            totalWeight += terrorist1.getWeight();
                            ArrayOfSpecial[0]++;
                            ArrayOflastSpecial[0]++;
                            break;
                        case 1:
                            PregnantWoman pregnantWoman = new PregnantWoman();
                            totalWeight += pregnantWoman.getWeight();
                            ArrayOfSpecial[1]++;
                            ArrayOflastSpecial[1]++;
                            break;
                        case 2:
                            Thief thief1 = new Thief();
                            totalWeight += thief1.getWeight();
                            ArrayOfSpecial[2]++;
                            ArrayOflastSpecial[2]++;
                            break;
                        case 3:
                            RowdyBogan rowdyBogan = new RowdyBogan();
                            totalWeight += rowdyBogan.getWeight();
                            ArrayOfSpecial[3]++;
                            ArrayOflastSpecial[3]++;
                            break;
                        case 4:
                        case 5:
                            Karen karen = new Karen();
                            totalWeight += karen.getWeight();
                            ArrayOfSpecial[4]++;
                            ArrayOflastSpecial[4]++;
                            break;
                        default:
                            Passenger passenger = new Passenger();
                            totalWeight += passenger.getWeight();
                    }

                }
            }

        }
    }

    /**
     * Updates the information in the model
     */
    public void informationUpdate(){
        Centerofgravity();
        range = (aircraft.getTotalFuel()/aircraft.getType().getFuelConsumption()) * aircraft.getType().getCruiseSpeed();
        cost = airport.getFuelPriceByType(aircraft.getType().getFuelType())*aircraft.getTotalFuel();
        profit = revenue - cost;
        for (CargoArea cargoArea : cargoAreaArray) {
            for (CargoUnit cargoUnit : aircraft.getCargoAreaContents(cargoArea)) {
                revenue += cargoUnit.getCargoType().getBasePricePerKg()*cargoUnit.getWeight();
            }
        }
    }
}