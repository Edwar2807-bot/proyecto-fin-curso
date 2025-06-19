@echo off
echo Ejecutando con Java 17...

REM Configurar Java 17
set JAVA_HOME=C:\Program Files\Microsoft\jdk-17.0.15.6-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%

echo Java version:
java -version

echo.
echo Compilando proyecto Java...

REM Crear directorio de salida
if not exist target\classes mkdir target\classes

REM Compilar archivos Java con Java 17
"%JAVA_HOME%\bin\javac" -d target\classes src\main\java\org\saul_cordoba\*.java src\main\java\org\saul_cordoba\controller\*.java src\main\java\org\saul_cordoba\service\*.java src\main\java\org\saul_cordoba\dto\*.java

REM Copiar archivo de propiedades
copy src\main\resources\application.properties target\classes\

echo.
echo Ejecutando aplicacion Spring Boot...
echo Abrir en navegador: http://localhost:8082/api/calculo/status
echo.

"%JAVA_HOME%\bin\java" -cp target\classes org.saul_cordoba.CalculoServiceApplication

pause 