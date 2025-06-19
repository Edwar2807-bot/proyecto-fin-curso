@echo off
echo Descargando e instalando Maven (URL corregida)...

REM Crear directorio para Maven
if not exist "C:\maven" mkdir "C:\maven"

echo Descargando Maven 3.9.6 desde la URL correcta...
powershell -Command "Invoke-WebRequest -Uri 'https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip' -OutFile 'C:\maven\maven.zip'"

if not exist "C:\maven\maven.zip" (
    echo Error: No se pudo descargar Maven. Probando URL alternativa...
    powershell -Command "Invoke-WebRequest -Uri 'https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip' -OutFile 'C:\maven\maven.zip'"
)

if not exist "C:\maven\maven.zip" (
    echo Error: No se pudo descargar Maven desde ninguna URL.
    echo.
    echo SOLUCION ALTERNATIVA:
    echo 1. Descarga manualmente desde: https://maven.apache.org/download.cgi
    echo 2. O usa IntelliJ IDEA Community (gratis y automatico)
    echo 3. O instala con: choco install maven (como administrador)
    pause
    exit /b 1
)

echo Extrayendo Maven...
powershell -Command "Expand-Archive -Path 'C:\maven\maven.zip' -DestinationPath 'C:\maven' -Force"

echo Configurando variables de entorno para esta sesion...
set MAVEN_HOME=C:\maven\apache-maven-3.9.6
set PATH=%MAVEN_HOME%\bin;%PATH%

echo Verificando instalacion...
if exist "%MAVEN_HOME%\bin\mvn.cmd" (
    "%MAVEN_HOME%\bin\mvn.cmd" --version
    echo.
    echo ✅ Maven instalado exitosamente!
    echo.
    echo Ahora ejecuta: mvn spring-boot:run
) else (
    echo ❌ Error: Maven no se instalo correctamente
)

pause 