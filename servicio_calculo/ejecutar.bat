@echo off
echo Compilando proyecto Java...

REM Crear directorio de salida
if not exist target\classes mkdir target\classes

REM Compilar archivos Java
javac -d target\classes src\main\java\org\saul_cordoba\*.java src\main\java\org\saul_cordoba\controller\*.java src\main\java\org\saul_cordoba\service\*.java src\main\java\org\saul_cordoba\dto\*.java

REM Copiar archivo de propiedades
copy src\main\resources\application.properties target\classes\

echo Ejecutando aplicacion...
java -cp target\classes org.saul_cordoba.CalculoServiceApplication

pause 