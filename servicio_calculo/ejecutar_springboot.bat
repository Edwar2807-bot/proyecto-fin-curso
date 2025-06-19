@echo off
echo Iniciando Servicio Calculo Cientifico con Spring Boot...

REM Configurar Java 17
set JAVA_HOME=C:\Program Files\Microsoft\jdk-17.0.15.6-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%

REM Intentar con Maven global si existe
if exist "C:\maven\apache-maven-3.9.5\bin\mvn.cmd" (
    echo Usando Maven global...
    set MAVEN_HOME=C:\maven\apache-maven-3.9.5
    set PATH=%MAVEN_HOME%\bin;%PATH%
    mvn spring-boot:run
    goto end
)

REM Intentar con Maven Wrapper
if exist "mvnw.cmd" (
    echo Usando Maven Wrapper...
    .\mvnw.cmd spring-boot:run
    goto end
)

REM Si no hay Maven, mostrar instrucciones
echo.
echo ========================================
echo   MAVEN NO ENCONTRADO
echo ========================================
echo.
echo Para ejecutar Spring Boot necesitas Maven:
echo.
echo 1. Ejecuta: .\descargar_maven.bat
echo 2. O instala Maven manualmente
echo 3. O usa IntelliJ IDEA Community (gratis)
echo.
echo Mientras tanto, puedes usar:
echo .\ejecutar_simple_fixed.bat
echo.

:end
pause 