Write-Host "========================================" -ForegroundColor Green
Write-Host "  INSTALANDO MAVEN Y SPRING BOOT" -ForegroundColor Green  
Write-Host "========================================" -ForegroundColor Green

# Configurar Java 17
$env:JAVA_HOME = "C:\Program Files\Microsoft\jdk-17.0.15.6-hotspot"
$env:PATH = "$env:JAVA_HOME\bin;" + $env:PATH

Write-Host "Java configurado:"
java -version

Write-Host "`nVerificando Maven existente..."

# Verificar si Maven ya está instalado
try {
    $mvnVersion = mvn --version 2>$null
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✅ Maven ya está instalado!" -ForegroundColor Green
        Write-Host $mvnVersion
        Write-Host "`nEjecutando Spring Boot..."
        mvn spring-boot:run
        exit
    }
} catch {
    Write-Host "Maven no encontrado, instalando..."
}

# Crear directorio para Maven
$mavenDir = "C:\maven"
if (!(Test-Path $mavenDir)) {
    New-Item -ItemType Directory -Path $mavenDir -Force
}

Write-Host "`nDescargando Maven 3.9.6..."
try {
    $url = "https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip"
    $zipPath = "$mavenDir\maven.zip"
    
    Invoke-WebRequest -Uri $url -OutFile $zipPath -UseBasicParsing
    
    Write-Host "✅ Maven descargado exitosamente"
    
    Write-Host "Extrayendo Maven..."
    Expand-Archive -Path $zipPath -DestinationPath $mavenDir -Force
    
    # Configurar Maven
    $env:MAVEN_HOME = "$mavenDir\apache-maven-3.9.6"
    $env:PATH = "$env:MAVEN_HOME\bin;" + $env:PATH
    
    Write-Host "✅ Maven instalado en: $env:MAVEN_HOME"
    
    # Verificar instalación
    mvn --version
    
    Write-Host "`n========================================" -ForegroundColor Green
    Write-Host "  EJECUTANDO SPRING BOOT" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
    
    mvn spring-boot:run
    
} catch {
    Write-Host "❌ Error al descargar Maven: $($_.Exception.Message)" -ForegroundColor Red
    Write-Host "`nSOLUCIONES ALTERNATIVAS:" -ForegroundColor Yellow
    Write-Host "1. Instalar con Chocolatey (como admin): choco install maven"
    Write-Host "2. Descargar manualmente: https://maven.apache.org/download.cgi"
    Write-Host "3. Usar IntelliJ IDEA Community (MÁS FÁCIL)"
    Write-Host "`nMientras tanto, el diagrama UML funciona con:"
    Write-Host ".\ejecutar_simple_fixed.bat" -ForegroundColor Cyan
}

Read-Host "`nPresiona Enter para continuar" 