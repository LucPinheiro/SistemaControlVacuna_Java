@echo off
REM Run script for EMA Vaccine Management System (Windows)

if not exist out (
    echo Application not compiled. Please run compile.bat first.
    exit /b 1
)

java -cp out ema.vaccine.VaccineManagementApp
