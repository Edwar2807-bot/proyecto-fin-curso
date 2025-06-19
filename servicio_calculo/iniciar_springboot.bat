@echo off
echo ========================================
echo   INICIANDO SERVICIO CALCULO
echo ========================================

REM Configurar Java 17
set JAVA_HOME=C:\Program Files\Microsoft\jdk-17.0.15.6-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%

echo Java configurado:
java -version

echo.
echo Probando diferentes opciones de Maven...

REM Opcion 1: Maven global instalado
if exist "C:\maven\apache-maven-3.9.6\bin\mvn.cmd" (
    echo ✅ Usando Maven global instalado
    set MAVEN_HOME=C:\maven\apache-maven-3.9.6
    set PATH=%MAVEN_HOME%\bin;%PATH%
    mvn spring-boot:run
    goto success
)

REM Opcion 2: Maven Wrapper reparado
if exist ".mvn\wrapper\maven-wrapper.jar" (
    echo ✅ Usando Maven Wrapper
    .\mvnw.cmd spring-boot:run
    goto success
)

REM Opcion 3: Chocolatey Maven
where mvn >nul 2>&1
if %errorlevel% == 0 (
    echo ✅ Usando Maven de Chocolatey/PATH
    mvn spring-boot:run
    goto success
)

REM Si no hay Maven disponible
echo.
echo ❌ MAVEN NO ENCONTRADO
echo.
echo OPCIONES DISPONIBLES:
echo.
echo 1. Ejecutar: .\descargar_maven_corregido.bat
echo 2. Instalar con: choco install maven (como administrador)
echo 3. Descargar manualmente desde: https://maven.apache.org/download.cgi
echo 4. Usar IntelliJ IDEA Community (MAS FACIL - gratis)
echo.
echo Mientras tanto, el diagrama UML funciona con:
echo .\ejecutar_simple_fixed.bat
echo.
goto end

:success
echo.
echo ========================================
echo   SPRING BOOT INICIADO EXITOSAMENTE!
echo ========================================
echo.
echo Abrir en navegador:
echo http://localhost:8082/api/calculo/status
echo.

:end
pause 