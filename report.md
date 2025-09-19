# Flight Logistics Simulator - Technical Report

## Project Overview

This technical report documents the design, implementation, and architecture of the Flight Logistics Aviation Planning Simulation (FLAPS) system. FLAPS is a comprehensive Java-based desktop application that simulates aviation logistics operations, allowing users to configure aircraft, manage cargo and fuel loads, and execute flight plans with real-time economic calculations.

## System Architecture

### Design Philosophy

The application was designed with a focus on modularity, extensibility, and user experience. The core architecture follows the Model-View-Controller (MVC) pattern to ensure clean separation of concerns and maintainable code structure.

### Technology Stack

- **Java 11+**: Chosen for its robust object-oriented features and cross-platform compatibility
- **Maven**: Provides reliable dependency management and build automation
- **Swing**: Selected for its mature GUI capabilities and extensive component library
- **YAML**: Used for human-readable configuration files
- **Lombok**: Reduces boilerplate code and improves development efficiency

## Detailed Implementation

### 1. User Interface Design

#### Main Application Window
The primary interface consists of a sophisticated split-pane design that balances functionality with usability:

**Blueprint Panel (Left Side)**
- Displays aircraft blueprints with proportional scaling
- Interactive visual indicators for fuel tanks (blue circles) and cargo areas (gray circles)
- Click-to-select functionality with visual feedback (selected areas turn red)
- Coordinate mapping system that translates aircraft coordinates to screen coordinates

**Control Panel (Right Side)**
- **Fuel Management Section**: Real-time fuel level displays, capacity monitoring, and fuel loading controls
- **Information Dashboard**: Live calculations for range, center of gravity, profit analysis, and weight distribution
- **Cargo Management Section**: Cargo area selection, load monitoring, and cargo operation controls

#### Cargo Loading Interface
A dedicated modal window was implemented for complex cargo operations:
- **Cargo Type Selection**: Dropdown list of available cargo types with current market pricing
- **Quantity Control**: Slider-based input system for precise weight specification
- **Current Load Display**: Real-time list of loaded cargo with individual weight tracking
- **Validation System**: Automatic weight limit checking and user feedback

### 2. Data Management System

#### Model Architecture

**EditorModel**
- Manages aircraft state and user interactions
- Implements coordinate mapping between aircraft blueprint space and screen space
- Handles selection logic for fuel tanks and cargo areas
- Provides event notification system for UI updates

**InformationModel**
- Performs real-time calculations for flight parameters
- Implements center of gravity calculations using weighted averages
- Calculates aircraft range based on fuel consumption and cruise speed
- Provides economic analysis including revenue, cost, and profit projections

#### Data Persistence
The system uses YAML configuration files for flexible data management:
- **Aircraft Types**: Specifications, blueprints, and performance characteristics
- **Airport Data**: Geographic coordinates, fuel prices, and cargo demands
- **Economic Parameters**: Market pricing for fuel and cargo types

### 3. Control System Implementation

#### Event Handling Architecture
The application implements a comprehensive event-driven architecture:

**Mouse Interaction Controllers**
- **SelectionController**: Processes mouse clicks on blueprint areas and translates coordinates to aircraft components
- **Area Selection Logic**: Determines which fuel tank or cargo area corresponds to user clicks

**Action Controllers**
- **LoadFuelListener**: Manages fuel addition operations with capacity validation
- **LoadCargoListener**: Handles cargo loading with weight limit enforcement
- **RemoveCargoListener**: Processes cargo removal operations
- **DepartAction**: Executes flight operations including fuel consumption and cargo unloading

#### Validation System
Comprehensive validation ensures operational safety and realism:
- **Weight Limits**: Prevents overloading of individual areas and total aircraft
- **Center of Gravity**: Validates that loaded aircraft remains within safe CG limits
- **Range Verification**: Ensures sufficient fuel for planned flights
- **Airport Capacity**: Checks destination airport availability

### 4. Advanced Features

#### Undo/Redo System
Implemented using Java's built-in UndoableEdit framework:
- **Operation Tracking**: All user actions are wrapped in undoable edit objects
- **State Management**: UndoManager maintains complete operation history
- **UI Integration**: Menu items and keyboard shortcuts provide easy access

#### Real-time Calculations

**Range Calculation**
```
Range = (Total Fuel / Fuel Consumption Rate) × Cruise Speed
```

**Center of Gravity Calculation**
Implements weighted average calculation across all aircraft components:
```
CG_x = Σ(Component_Weight × Component_X) / Total_Weight
CG_y = Σ(Component_Weight × Component_Y) / Total_Weight
```

**Economic Analysis**
- **Cost Calculation**: Based on fuel consumption and origin airport fuel prices
- **Revenue Calculation**: Derived from cargo weights and destination airport cargo prices
- **Profit Analysis**: Real-time profit/loss calculations with market price fluctuations

## System Performance and Reliability

### Error Handling
The application implements comprehensive error handling:
- **User Input Validation**: Prevents invalid operations before they occur
- **Exception Management**: Graceful handling of unexpected conditions with informative user feedback
- **Data Integrity**: Ensures consistent application state during all operations

### Performance Optimization
- **Efficient Rendering**: Optimized drawing operations for smooth blueprint display
- **Memory Management**: Proper resource cleanup and object lifecycle management
- **Responsive UI**: Event-driven updates prevent interface blocking during calculations

## Testing and Quality Assurance

### Functional Testing
Comprehensive testing was performed across all major features:
- **Aircraft Configuration**: Verified correct fuel and cargo loading operations
- **Calculation Accuracy**: Validated all mathematical computations against known values
- **UI Responsiveness**: Tested user interface across different screen sizes and resolutions
- **Data Persistence**: Confirmed proper loading and saving of configuration data

### Edge Case Handling
Special attention was given to boundary conditions:
- **Capacity Limits**: Testing maximum fuel and cargo loads
- **Invalid Operations**: Ensuring graceful handling of impossible flight configurations
- **Data Consistency**: Maintaining accurate state during rapid user interactions

## Future Development Opportunities

### Immediate Enhancements
- **Bug Resolution**: Address remaining issues in cargo removal operations
- **UI Polish**: Enhanced visual design with modern styling and improved iconography
- **Performance Optimization**: Further improvements to rendering and calculation speed

### Advanced Features
- **Real-world Integration**: Connection to live aviation data feeds
- **Route Optimization**: AI-powered flight planning algorithms
- **Multi-user Support**: Collaborative simulation environment
- **Enhanced Physics**: More sophisticated flight dynamics modeling

### Architectural Improvements
- **Modular Plugin System**: Allow third-party extensions and customizations
- **Database Integration**: Replace YAML files with robust database backend
- **Web Interface**: Browser-based version for improved accessibility

## Conclusion

The Flight Logistics Simulator successfully demonstrates complex software engineering principles while providing an engaging and educational simulation experience. The application's modular architecture, comprehensive feature set, and attention to user experience create a solid foundation for future development and enhancement.

The project showcases practical application of object-oriented design patterns, event-driven programming, and user interface development, while maintaining code quality and system reliability. The flexible configuration system and extensible architecture ensure that the application can evolve to meet changing requirements and incorporate new features.