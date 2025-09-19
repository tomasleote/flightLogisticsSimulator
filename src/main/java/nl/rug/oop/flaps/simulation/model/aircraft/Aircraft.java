package nl.rug.oop.flaps.simulation.model.aircraft;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.flaps.simulation.model.airport.Airport;
import nl.rug.oop.flaps.simulation.model.cargo.CargoType;
import nl.rug.oop.flaps.simulation.model.cargo.CargoUnit;
import nl.rug.oop.flaps.simulation.model.world.World;

import javax.swing.undo.UndoManager;
import java.util.*;

/**
 * Represents a single aircraft of a particular type.
 *
 * @author T.O.W.E.R.
 */
@Getter
public class Aircraft implements Comparable<Aircraft> {
    /**
     * A unique identifier for this aircraft, like a call-sign.
     */
    private final String identifier;


    /**
     * The number of passengers on the plane
     */
    private int passengers;

    /**
     * The type of this aircraft.
     */
    private final AircraftType type;

    /**
     * The world that this aircraft exists in.
     */
    private final World world;

    /**
     * A map that contains information for each fuel tank of how much fuel is in there
     * The key is the name of the fuel tank and the Double is the amount of fuel in kg
     */
    private final Map<FuelTank, Double> fuelTankFillStatuses;

    /**
     * A map that contains information for each cargo area what the cargo contents are
     * The key is the name of the cargo area
     */
    private final Map<CargoArea, Set<CargoUnit>> cargoAreaContents;


    /**
     * Undomanager for the aircraft
     */
    private final UndoManager undoManager;


    public Aircraft(String identifier, AircraftType type, World world) {
        this.passengers = 0;
        this.undoManager = new UndoManager();
        this.identifier = identifier;
        this.type = type;
        this.world = world;
        fuelTankFillStatuses = new HashMap<>();
        cargoAreaContents = new HashMap<>();
    }

    /**
     * Retrieves the amount of fuel that is consumed when flying between two airports in kg
     *
     * @param origin The airport the aircraft departs from
     * @param destination The airport the aircraft flies to
     * @return The amount of fuel in kg consumed in the journey
     */
    public double getFuelConsumption(Airport origin, Airport destination) {
        double distance = origin.getGeographicCoordinates().distanceTo(destination.getGeographicCoordinates());
        return (distance/ getType().getCruiseSpeed())*getType().getFuelConsumption();
    }

    /**
     * Progressively removes fuel from tanks until consumedFuel kg has been removed
     *
     * @param consumedFuel The amount of fuel that should be removed in total
     */
    public void removeFuel(double consumedFuel) {
        for (FuelTank fuelTank : fuelTankFillStatuses.keySet()) {
            double fuelInTank = getFuelAmountForFuelTank(fuelTank);
            if (consumedFuel > fuelInTank) {
                setFuelAmountForFuelTank(fuelTank, 0);
                consumedFuel -= fuelInTank;
            } else {
                setFuelAmountForFuelTank(fuelTank, fuelInTank - consumedFuel);
                return;
            }
        }
    }



    /**
     * Retrieves the total amount of fuel (from all fuel tanks) in the aircraft in kg
     *
     * @return The total amount of fuel in the aircraft in kg
     */
    public double getTotalFuel() {
        return fuelTankFillStatuses.keySet().stream().mapToDouble(fuelTankFillStatuses::get).sum() * type.getFuelType().getDensity();
    }

    /**
     * Retrieves the contents of a cargo area
     *
     * @param cargoArea The cargo area the contents should be retrieved from
     *
     * @return A list of cargo units representing the contents of the cargo area
     */
    public Set<CargoUnit> getCargoAreaContents(CargoArea cargoArea) {
        return cargoAreaContents.getOrDefault(cargoArea, new HashSet<>());
    }

    public void setCargoAreaContents(CargoArea cargoArea, Set<CargoUnit> cargoUnits) {
        cargoAreaContents.put(cargoArea, cargoUnits);
    }

    /**
     * Sets the amount of fuel in kg for a specific fuel tank
     *
     * @param fuelTank The fuel tank
     * @param fuelAmount   The amount of fuel in kg this aircraft
     */
    public void setFuelAmountForFuelTank(FuelTank fuelTank, double fuelAmount) {
        fuelTankFillStatuses.put(fuelTank, fuelAmount);
    }

    /**
     * Retrieves the amount of fuel in kg for a specific fuel tank
     *
     * @param fuelTank The fuel tank you want the contents of
     *
     * @return The amount of fuel in kg in the fuel tank with the provided name
     */
    public double getFuelAmountForFuelTank(FuelTank fuelTank) {
        return fuelTankFillStatuses.getOrDefault(fuelTank, 0.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return getIdentifier().equals(aircraft.getIdentifier()) && getType().equals(aircraft.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier(), getType());
    }

    @Override
    public int compareTo(Aircraft o) {
        int typeComparison = this.getType().compareTo(o.getType());
        if (typeComparison != 0) return typeComparison;
        return this.getIdentifier().compareTo(o.getIdentifier());
    }

    public boolean addToCargoUnit (CargoArea cargoArea, CargoType cargoType, double cargoKG ) {

        if(cargoAreaContents.get(cargoArea)==null) {return false;}
        for(CargoUnit cargoUnit: cargoAreaContents.get(cargoArea)){
            if(cargoUnit.getCargoType().equals(cargoType)) {
                cargoUnit.addWeight(cargoKG);
                return true;
            }
        } return false;
    }

    public boolean addCargo(CargoArea cargoArea, CargoType cargoType, double cargoKG){
        if (cargoArea.checkWeight(cargoKG)) {
            cargoArea.addWeight(cargoKG);
            if (addToCargoUnit(cargoArea, cargoType, cargoKG)) {return true;}
            Set<CargoUnit> loadedCargo = getCargoAreaContents(cargoArea);
            CargoUnit cargoUnit = new CargoUnit(cargoType, cargoKG);
            loadedCargo.add(cargoUnit);
            cargoAreaContents.remove(cargoArea);
            cargoAreaContents.put(cargoArea, loadedCargo);
            return true;
        }
        return false;
    }

    public boolean deleteCargo(CargoArea cargoArea, CargoType cargoType, Airport airport){
        try {
            for (CargoUnit cargoUnit : cargoAreaContents.get(cargoArea)) {
                if (cargoUnit.getCargoType().equals(cargoType)) {
                    cargoArea.addWeight(cargoUnit.getWeight() * -1);
                    cargoAreaContents.get(cargoArea).remove(cargoUnit);
                    return true;
                }
            }
        } catch (NullPointerException nullPointerException) {}
        return false;
    }

    public void addFuel(FuelTank tank, double liters) {
        double loadedLiters = 0;
        if (fuelTankFillStatuses.containsKey(tank)) {
            loadedLiters= fuelTankFillStatuses.get(tank);
        }
        fuelTankFillStatuses.put(tank, liters+loadedLiters);
    }

    public void AddPassengers(int amount){ passengers+= amount;}

    public boolean checkWeight(double liters, FuelTank tank) {
        return  liters+getFuelAmountForFuelTank(tank)<=tank.getCapacity();
    }
}

