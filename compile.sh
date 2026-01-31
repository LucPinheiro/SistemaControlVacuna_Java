#!/bin/bash
# Compile script for EMA Vaccine Management System

# Create output directory
mkdir -p out

# Compile all Java files
echo "Compiling Java files..."
javac -d out src/main/java/ema/vaccine/*.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    echo ""
    echo "To run the application:"
    echo "  java -cp out ema.vaccine.VaccineManagementApp"
else
    echo "❌ Compilation failed!"
    exit 1
fi
