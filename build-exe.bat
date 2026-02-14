@echo off
echo ========================================
echo   VolleyballSR - Build EXE Script
echo ========================================
echo.

REM --- Hardcode Liberica Full JDK 25 path ---
set LIBERICA=C:\Program Files\BellSoft\LibericaJDK-25-Full
set LIBERICA_BIN=C:\Program Files\BellSoft\LibericaJDK-25-Full\bin

REM --- Verify Liberica Full exists ---
if not exist "%LIBERICA%" (
    echo [ERROR] Liberica Full JDK 25 not found at %LIBERICA%
    echo Please install it from https://bell-sw.com/pages/downloads/
    echo Make sure to download the FULL JDK version.
    pause
    exit /b 1
)

echo Liberica Full JDK found. Java version:
"%LIBERICA_BIN%\java.exe" -version
echo.

echo [1/3] Building JAR with Maven...
call mvn clean package -q -Dmaven.compiler.source=21 -Dmaven.compiler.target=21
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Maven build failed. Check the output above for errors.
    pause
    exit /b 1
)
echo       Done!
echo.

echo [2/3] Preparing output folder...
if exist "dist" rmdir /s /q dist
mkdir dist
echo       Done!
echo.

echo [3/3] Packaging as Windows EXE with jpackage...
"%LIBERICA_BIN%\jpackage.exe" ^
  --input target ^
  --name VolleyballSR ^
  --main-jar VolleyballSR-1.0.0.jar ^
  --main-class com.volleyballsr.Main ^
  --type app-image ^
  --dest dist ^
  --runtime-image "%LIBERICA%" ^
  --java-options "--add-modules javafx.controls,javafx.fxml,javafx.graphics" ^
  --java-options "--enable-native-access=javafx.graphics"

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo [ERROR] jpackage failed.
    pause
    exit /b 1
)

echo.
echo ========================================
echo   SUCCESS! Your EXE is ready at:
echo   dist\VolleyballSR\VolleyballSR.exe
echo ========================================
echo.
pause