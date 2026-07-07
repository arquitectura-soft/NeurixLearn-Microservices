#!/bin/bash

echo "========================================="
echo "  NeurixLearn - Build & Deploy Script"
echo "========================================="
echo ""

# Directorio del proyecto
PROJECT_DIR="/Users/joanbalbin/IdeaProjects/Java/Cursos/Fundamentos/NeurixLearn-Microservices-master"
cd "$PROJECT_DIR" || exit 1

echo "🧹 Limpiando contenedores anteriores..."
docker-compose down -v 2>/dev/null

echo ""
echo "🔨 Compilando todos los módulos con Maven..."
echo "   (Esto puede tardar varios minutos...)"
echo ""

# Configurar JAVA_HOME para usar Java 23
export JAVA_HOME=/opt/homebrew/Cellar/openjdk/23.0.2/libexec/openjdk.jdk/Contents/Home

# Compilar usando Maven directamente
mvn clean package -Dmaven.test.skip=true

# Verificar que se compilaron los JARs
echo ""
echo "✅ Verificando archivos JAR generados..."
missing_jars=0

for service in discovery-server api-gateway learne-service profile-service notification-service; do
    jar_file="$service/target/$service-0.0.1-SNAPSHOT.jar"
    if [ -f "$jar_file" ]; then
        echo "   ✓ $jar_file"
    else
        echo "   ✗ $jar_file - NO ENCONTRADO"
        missing_jars=$((missing_jars + 1))
    fi
done

if [ $missing_jars -gt 0 ]; then
    echo ""
    echo "❌ Error: Faltan $missing_jars archivos JAR. La compilación falló."
    exit 1
fi

echo ""
echo "🐳 Levantando servicios con Docker Compose..."
echo ""

docker-compose up -d --build

echo ""
echo "⏳ Esperando que los servicios se inicien..."
echo "   (Esto puede tardar 2-3 minutos...)"
echo ""

sleep 10

echo "📊 Estado de los contenedores:"
docker-compose ps

echo ""
echo "========================================="
echo "✅ ¡Proceso completado!"
echo "========================================="
echo ""
echo "🔍 URLs disponibles:"
echo ""
echo "   🧭 Eureka Dashboard:    http://localhost:8761"
echo "   🌐 API Gateway:         http://localhost:8080"
echo ""
echo "   📚 Learne Service Swagger:"
echo "      http://localhost:8080/learne/swagger-ui.html"
echo ""
echo "   👤 Profile Service Swagger:"
echo "      http://localhost:8080/profile/swagger-ui.html"
echo ""
echo "========================================="
echo ""
echo "💡 Comandos útiles:"
echo "   • Ver logs: docker-compose logs -f"
echo "   • Ver estado: ./check-services.sh"
echo "   • Detener: docker-compose down"
echo ""




