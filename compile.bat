@echo off
REM Compile script for EMA Vaccine Management System (Windows)

REM Create output directory
if not exist out mkdir out

REM Compile all Java files
echo Compiling Java files...
javac -d out src/main/java/ema/vaccine/*.java

if %errorlevel% equ 0 (
    echo.
    echo Compilation successful!
    echo.
    echo To run the application:
    echo   java -cp out ema.vaccine.VaccineManagementApp
) else (
    echo.
    echo Compilation failed!
    exit /b 1
)
