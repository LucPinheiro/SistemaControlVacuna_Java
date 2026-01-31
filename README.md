# EMA COVID-19 Vaccine Management System

A Java console application for managing COVID-19 vaccines for the European Medicines Agency (EMA). This system provides comprehensive vaccine tracking, authorization management, and research phase monitoring using Object-Oriented Programming (OOP) principles.

## Features

- **Vaccine Management**: Add, list, view, and delete COVID-19 vaccines
- **Authorization Control**: Authorize or reject vaccines based on research completion
- **Research Phase Tracking**: Monitor vaccines through 4 research phases
- **Validation Rules**: Built-in validation for vaccine data integrity
- **Filtering Capabilities**: List vaccines by authorization status or research phase
- **Standard Java**: Uses only standard Java libraries (no external dependencies)

## Architecture

The application follows OOP principles with the following key components:

### 1. Authorization Interface (`Authorization.java`)
Defines the contract for authorization functionality:
- `authorize()` - Authorize a vaccine
- `reject()` - Reject a vaccine
- `isAuthorized()` - Check authorization status
- `getAuthorizationStatus()` - Get current status

### 2. ResearchVaccine Abstract Class (`ResearchVaccine.java`)
Handles research phases and authorization logic:
- Manages 4 research phases (Safety, Efficacy, Large-scale testing, Post-marketing)
- Enforces that vaccines must complete all phases before authorization
- Implements the Authorization interface
- Provides phase advancement functionality

### 3. Vaccine POJO (`Vaccine.java`)
Complete vaccine data model with validation:
- **Name**: Vaccine name (required, unique)
- **Manufacturer**: Manufacturer name (required)
- **Type**: Vaccine type - must be one of:
  - mRNA
  - Viral Vector
  - Protein Subunit
  - Inactivated
  - Live Attenuated
- **Efficacy Percentage**: 0-100 range validation
- **Development Start Date**: Cannot be in the future
- **Description**: Optional field for additional information

### 4. VaccineStorage Class (`VaccineStorage.java`)
Manages the collection of vaccines:
- Add/remove vaccines
- Find vaccines by name
- Filter by authorization status
- Filter by research phase
- Retrieve all vaccines

### 5. VaccineManagementApp (`VaccineManagementApp.java`)
Main console application with interactive menu:
1. List all vaccines
2. Add new vaccine
3. Delete vaccine
4. Authorize vaccine
5. Reject vaccine
6. Advance research phase
7. View vaccine details
8. List vaccines by authorization status
9. List vaccines by research phase
0. Exit

## Requirements

- Java Development Kit (JDK) 8 or higher
- No external dependencies required

## Installation & Compilation

### On Linux/Mac:

```bash
# Make scripts executable
chmod +x compile.sh run.sh

# Compile the application
./compile.sh
```

### On Windows:

```batch
# Compile the application
compile.bat
```

### Manual Compilation:

```bash
# Create output directory
mkdir -p out

# Compile all Java files
javac -d out src/main/java/ema/vaccine/*.java
```

## Running the Application

### On Linux/Mac:

```bash
./run.sh
```

### On Windows:

```batch
run.bat
```

### Manual Execution:

```bash
java -cp out ema.vaccine.VaccineManagementApp
```

## Usage Examples

### Adding a Vaccine

1. Select option `2` from the menu
2. Enter vaccine details:
   - Name: Pfizer-BioNTech
   - Manufacturer: Pfizer/BioNTech
   - Type: mRNA
   - Efficacy: 95
   - Development Start Date: 20/12/2020 (or press Enter for today)
   - Description: First mRNA COVID-19 vaccine

### Advancing Through Research Phases

1. Add a vaccine (starts at Phase 1)
2. Select option `6` to advance research phase
3. Enter vaccine name
4. Repeat 3 times to reach Phase 4

### Authorizing a Vaccine

1. Ensure vaccine has completed all 4 research phases
2. Select option `4` to authorize
3. Enter vaccine name
4. Vaccine will be marked as "AUTHORIZED"

Note: Vaccines cannot be authorized unless they have completed all 4 research phases.

### Viewing Vaccine Details

Select option `7` and enter vaccine name to see complete information including:
- Manufacturer and type
- Development start date
- Efficacy percentage
- Current research phase with description
- Authorization status
- Description

## Project Structure

```
SistemaControlVacuna_Java/
├── src/
│   └── main/
│       └── java/
│           └── ema/
│               └── vaccine/
│                   ├── Authorization.java          # Authorization interface
│                   ├── ResearchVaccine.java        # Abstract class for research phases
│                   ├── Vaccine.java                # Vaccine POJO with validation
│                   ├── VaccineStorage.java         # Collection management
│                   └── VaccineManagementApp.java   # Main application
├── compile.sh         # Linux/Mac compilation script
├── compile.bat        # Windows compilation script
├── run.sh            # Linux/Mac run script
├── run.bat           # Windows run script
├── .gitignore        # Git ignore file
└── README.md         # This file
```

## Validation Rules

The application enforces the following validation rules:

1. **Vaccine Name**: Cannot be empty, must be unique
2. **Manufacturer**: Cannot be empty
3. **Type**: Must be one of the predefined vaccine types
4. **Efficacy Percentage**: Must be between 0 and 100
5. **Development Start Date**: Cannot be in the future
6. **Authorization**: Can only authorize vaccines that have completed all 4 research phases

## OOP Principles Applied

- **Encapsulation**: Data and methods are encapsulated in appropriate classes
- **Abstraction**: Abstract class `ResearchVaccine` defines common research behavior
- **Interface**: `Authorization` interface defines authorization contract
- **Inheritance**: `Vaccine` extends `ResearchVaccine` which implements `Authorization`
- **Polymorphism**: Methods can work with `Authorization` interface or abstract class
- **Single Responsibility**: Each class has a single, well-defined purpose
- **Data Validation**: Input validation ensures data integrity

## License

This is an educational project for demonstrating Java OOP principles.

## Author

European Medicines Agency (EMA) Training Program