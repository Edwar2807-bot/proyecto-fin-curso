# 🧪 CÓMO PROBAR EL SERVICIO DE CÁLCULO CIENTÍFICO

## 🚀 **PASO 1: Ejecutar el servicio**

```bash
# Opción 1: Docker (Recomendado)
docker build -t servicio-calculo .
docker run -p 8082:8082 servicio-calculo

# Opción 2: Docker Compose  
docker-compose up --build
```

## ✅ **PASO 2: Verificar que funciona**

### **🔍 Test básico:**
```bash
curl http://localhost:8082/api/calculo/status
```
**Respuesta esperada:** `"Servicio de Cálculo Científico - Funcionando correctamente"`

## 🧪 **PASO 3: Probar procesamiento de datos**

### **📄 Procesamiento JSON:**
```bash
curl -X POST http://localhost:8082/api/calculo/procesar/json \
     -H "Content-Type: application/json" \
     -d '{"datos":"Hola mundo"}'
```

**Respuesta esperada:**
```json
{
  "tipo": "JSON",
  "datos_originales": "Hola mundo", 
  "resultado": "{\"tipo\":\"JSON\",\"datos_originales\":\"Hola mundo\",\"datos_procesados\":\"HOLA MUNDO\",\"longitud\":10,\"timestamp\":\"2025-06-18T...\"}", 
  "mensaje": "Procesamiento exitoso",
  "timestamp": "2025-06-18 16:30:45"
}
```

### **🔐 Procesamiento Base64:**
```bash
curl -X POST http://localhost:8082/api/calculo/procesar/base64 \
     -H "Content-Type: application/json" \
     -d '{"datos":"Hello World"}'
```

**Respuesta esperada:** Texto codificado en Base64: `SGVsbG8gV29ybGQ=`

### **📄 Procesamiento XML:**
```bash
curl -X POST http://localhost:8082/api/calculo/procesar/xml \
     -H "Content-Type: application/json" \
     -d '{"datos":"Datos de prueba"}'
```

**Respuesta esperada:** XML estructurado con metadatos

### **🔓 Decodificación Base64:**
```bash
curl -X POST http://localhost:8082/api/calculo/procesar/base64/decodificar \
     -H "Content-Type: application/json" \
     -d '{"datos":"SGVsbG8gV29ybGQ="}'
```

**Respuesta esperada:** `"Hello World"`

## 📁 **PASO 4: Verificar persistencia en archivos**

**Los archivos se guardan en:**
- `data/datos_procesados_json_[timestamp].txt`
- `data/datos_procesados_base64_[timestamp].txt`
- `data/datos_procesados_xml_[timestamp].xml`

## 🏃 **PASO 5: Probar ejecución desde consola**

```bash
java -jar target/servicio_calculo-1.0-SNAPSHOT.jar
```

**Debe mostrar:** Ejecución del diagrama UML con las 3 implementaciones

## ✅ **INDICADORES DE QUE FUNCIONA CORRECTAMENTE:**

1. ✅ **API responde** - Status 200 en todos los endpoints
2. ✅ **Procesamiento funcional** - Convierte datos según tipo
3. ✅ **Persistencia activa** - Genera archivos en /data
4. ✅ **Logs informativos** - Muestra procesamiento en consola
5. ✅ **UML ejecuta** - Clase Inicio funciona desde main()

## 🔧 **Script automático de pruebas:**

Ejecutar: `test_endpoints.bat` (Windows) para probar todos los endpoints automáticamente.

## 📱 **Herramientas recomendadas:**

- **Postman** - Interfaz gráfica para APIs
- **curl** - Línea de comandos  
- **Navegador** - Para endpoint GET de status 