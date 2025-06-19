@echo off
echo ===== PRUEBAS DEL SERVICIO DE CALCULO CIENTIFICO =====
echo.

echo 1. Probando estado del servicio...
curl -X GET http://localhost:8082/api/calculo/status
echo.
echo.

echo 2. Probando procesamiento JSON...
curl -X POST http://localhost:8082/api/calculo/procesar/json ^
     -H "Content-Type: application/json" ^
     -d "{\"datos\":\"Hola mundo desde JSON\"}"
echo.
echo.

echo 3. Probando procesamiento Base64...
curl -X POST http://localhost:8082/api/calculo/procesar/base64 ^
     -H "Content-Type: application/json" ^
     -d "{\"datos\":\"Texto para codificar en Base64\"}"
echo.
echo.

echo 4. Probando procesamiento XML...
curl -X POST http://localhost:8082/api/calculo/procesar/xml ^
     -H "Content-Type: application/json" ^
     -d "{\"datos\":\"Datos para convertir a XML\"}"
echo.
echo.

echo 5. Probando decodificacion Base64...
curl -X POST http://localhost:8082/api/calculo/procesar/base64/decodificar ^
     -H "Content-Type: application/json" ^
     -d "{\"datos\":\"SGVsbG8gV29ybGQ=\"}"
echo.
echo.

echo ===== FIN DE PRUEBAS =====
pause 