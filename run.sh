#!/bin/bash
# Run script for EMA Vaccine Management System

if [ ! -d "out" ]; then
    echo "❌ Application not compiled. Please run compile.sh first."
    exit 1
fi

java -cp out ema.vaccine.VaccineManagementApp
