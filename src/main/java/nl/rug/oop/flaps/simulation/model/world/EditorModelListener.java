package nl.rug.oop.flaps.simulation.model.world;

import nl.rug.oop.flaps.simulation.model.aircraft.CargoArea;

public interface EditorModelListener {
    default void setCargoArea(CargoArea cargoArea){}
    default void selectedPointUpdated() {}
}
