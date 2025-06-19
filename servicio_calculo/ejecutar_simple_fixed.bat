@echo off
chcp 65001 >nul
echo Ejecutando version simple del Diagrama UML...

REM Configurar Java 17
set JAVA_HOME=C:\Program Files\Microsoft\jdk-17.0.15.6-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%

echo Java version:
java -version

echo.
echo Compilando clases UML basicas con encoding UTF-8...

REM Crear directorio de salida
if not exist target\classes mkdir target\classes

REM Compilar las clases en el orden correcto con encoding UTF-8
echo Compilando Interface IProcesarDatos...
"%JAVA_HOME%\bin\javac" -encoding UTF-8 -d target\classes src\main\java\org\saul_cordoba\service\IProcesarDatos.java

echo Compilando ProcesarJSONSimple...
"%JAVA_HOME%\bin\javac" -encoding UTF-8 -cp target\classes -d target\classes src\main\java\org\saul_cordoba\service\ProcesarJSONSimple.java

echo Compilando ProcesarBase64Simple...
"%JAVA_HOME%\bin\javac" -encoding UTF-8 -cp target\classes -d target\classes src\main\java\org\saul_cordoba\service\ProcesarBase64Simple.java

echo Compilando ProcesarXMLSimple...
"%JAVA_HOME%\bin\javac" -encoding UTF-8 -cp target\classes -d target\classes src\main\java\org\saul_cordoba\service\ProcesarXMLSimple.java

echo Compilando DemostracionUML...
"%JAVA_HOME%\bin\javac" -encoding UTF-8 -cp target\classes -d target\classes src\main\java\org\saul_cordoba\service\DemostracionUML.java

echo.
echo ==============================
echo Ejecutando demostracion...
echo ==============================
echo.

"%JAVA_HOME%\bin\java" -cp target\classes org.saul_cordoba.service.DemostracionUML

echo.
echo ========================================
echo   EJECUCION COMPLETADA EXITOSAMENTE
echo ========================================
echo Revisa el directorio 'data' para ver los archivos generados

pause 