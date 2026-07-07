#!/bin/bash

echo "==========================================="
echo "  Iniciando NeurixLearn Platform"
echo "==========================================="
echo ""

cd /Users/joanbalbin/IdeaProjects/Java/Cursos/Fundamentos/NeurixLearn-Microservices-master

echo "🚀 Levantando todos los servicios..."
docker-compose up -d

echo ""
echo "⏳ Esperando que los servicios inicien (30 segundos)..."
sleep 30

echo ""
echo "✅ Estado de los servicios:"
docker-compose ps

echo ""
echo "==========================================="
echo "  ✅ NeurixLearn Platform Iniciado"
echo "==========================================="
echo ""
echo "📍 URLs disponibles:"
echo ""
echo "   🧭 Eureka Dashboard:"
echo "      http://localhost:8761"
echo ""
echo "   📚 Swagger - API de Cursos:"
echo "      http://localhost:8080/learne/swagger-ui/index.html"
echo ""
echo "   👥 Swagger - API de Usuarios:"
echo "      http://localhost:8080/profile/swagger-ui/index.html"
echo ""
echo "==========================================="
echo ""
echo "💡 Comandos útiles:"
echo "   • Ver logs:     docker-compose logs -f"
echo "   • Detener todo: docker-compose down"
echo "   • Ver estado:   docker-compose ps"
echo ""

