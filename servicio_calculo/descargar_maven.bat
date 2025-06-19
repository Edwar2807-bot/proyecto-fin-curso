@echo off
echo Descargando e instalando Maven...

REM Crear directorio para Maven
if not exist "C:\maven" mkdir "C:\maven"

echo Descargando Maven 3.9.5...
powershell -Command "Invoke-WebRequest -Uri 'https://downloads.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.zip' -OutFile 'C:\maven\maven.zip'"

echo Extrayendo Maven...
powershell -Command "Expand-Archive -Path 'C:\maven\maven.zip' -DestinationPath 'C:\maven' -Force"

echo Configurando variables de entorno...
set MAVEN_HOME=C:\maven\apache-maven-3.9.5
set PATH=%MAVEN_HOME%\bin;%PATH%

echo Verificando instalacion...
"%MAVEN_HOME%\bin\mvn" --version

echo.
echo Maven instalado exitosamente!
echo Ahora ejecuta: mvn spring-boot:run

pause 