# Flight Logistics Simulator (FLAPS)

![FLAPS Banner](images/readme/FLAPS.png)

A comprehensive Java-based flight logistics and aviation planning simulation that allows users to manage aircraft configurations, cargo loading, fuel management, and flight planning operations.

## 🚀 Overview

The Flight Logistics Aviation Planning Simulation (FLAPS) is an interactive desktop application that simulates real-world aviation logistics operations. Users can configure aircraft, manage fuel and cargo loads, calculate flight parameters, and execute flight plans between different airports with varying economic conditions.

## ✨ Key Features

### Core Functionality
- **Aircraft Configuration Editor**: Visual blueprint-based aircraft configuration with drag-and-drop fuel and cargo area management
- **Dynamic Flight Planning**: Real-time calculations for range, center of gravity, and profit optimization
- **Multi-Airport System**: Support for multiple airports with varying fuel prices and cargo demands
- **Economic Simulation**: Revenue, cost, and profit calculations based on realistic market conditions

### Technical Features
- **Interactive GUI**: Swing-based interface following MVC architectural pattern
- **Real-time Updates**: Live calculations and visual feedback for all aircraft modifications
- **Data Persistence**: YAML-based configuration system for airports, aircraft types, and cargo specifications
- **Undo/Redo System**: Complete action history management for all user operations

## 🛠 Technologies Used

- **Java 11+**: Core application development
- **Maven**: Dependency management and build automation
- **Jackson**: YAML/JSON data processing
- **Lombok**: Code generation and boilerplate reduction
- **FlatLaf**: Modern look-and-feel UI theming
- **Swing**: GUI framework

## 📋 Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## 🚀 Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/flightLogisticsSimulator.git
   cd flightLogisticsSimulator
   ```

2. **Build the project:**
   ```bash
   mvn clean compile
   ```

3. **Run the application:**
   ```bash
   mvn exec:java -Dexec.mainClass="nl.rug.oop.flaps.Main"
   ```

## 🎮 How to Use

### Getting Started
1. Launch the application to see the main FLAPS interface
2. Select an aircraft from the available options
3. Open the aircraft configuration editor

### Aircraft Configuration
- **Blueprint View**: Visual representation of the aircraft with fuel tanks (blue indicators) and cargo areas (gray indicators)
- **Selection**: Click on any indicator to select and configure that area
- **Information Panel**: Real-time display of aircraft statistics and calculations

### Fuel Management
- Select any fuel tank area on the blueprint
- View current fuel levels and tank capacity
- Add or remove fuel using the intuitive controls
- Monitor fuel consumption and range calculations

### Cargo Operations
- Select cargo areas to view current load and capacity
- Add cargo by selecting cargo type and quantity
- Remove specific cargo types or amounts
- Track weight distribution and cargo value

### Flight Planning
- Monitor real-time calculations for:
  - Aircraft range based on fuel load
  - Center of gravity (empty and loaded)
  - Estimated revenue, costs, and profit
  - Total aircraft weight
- Execute flights when all safety parameters are met

### Advanced Features
- **Undo/Redo**: Complete history of all operations with full reversibility
- **Safety Checks**: Automatic validation of weight limits, center of gravity, and range requirements
- **Economic Analysis**: Detailed profit/loss calculations based on fuel costs and cargo values

## 📁 Project Structure

```
├── src/main/java/
│   ├── nl/rug/oop/flaps/
│   │   ├── Main.java                 # Application entry point
│   │   ├── aircraft_editor/          # Aircraft configuration system
│   │   ├── simulation/               # Core simulation logic
│   │   │   ├── model/               # Data models and business logic
│   │   │   └── view/                # GUI components and interfaces
├── data/                            # YAML configuration files
├── images/                          # Aircraft blueprints and UI assets
└── config/                          # Application configuration
```

## 🏗 Architecture

The application follows a strict **Model-View-Controller (MVC)** pattern:

- **Model**: Aircraft data, airport information, cargo/fuel management
- **View**: Swing-based GUI components with custom rendering
- **Controller**: Event handlers, user interaction management, and business logic coordination

## 🔧 Configuration

The simulation uses YAML files for configuration:
- `airports.yaml`: Airport definitions, locations, and economic data
- `aircraft_types.yaml`: Aircraft specifications and blueprints
- `cargo_types.yaml`: Available cargo types and pricing
- `fuel_types.yaml`: Fuel specifications and regional pricing

## 🤝 Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues for bugs and feature requests.

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 🌟 Future Enhancements

- **Real-world Data Integration**: Connect to live aviation APIs for real-time fuel prices and weather data
- **Enhanced Economics**: More sophisticated pricing models and market fluctuations
- **Passenger Management**: Extend cargo system to include passenger logistics
- **Route Optimization**: AI-powered route planning and fuel optimization
- **Multiplayer Support**: Multi-user simulation with shared airport resources

## 📸 Screenshots

*Add screenshots of your application here*

## 🏆 Acknowledgments

Built with modern Java technologies and design patterns for educational and simulation purposes.