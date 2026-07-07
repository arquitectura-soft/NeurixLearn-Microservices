#!/bin/bash

echo "==========================================="
echo "  Reiniciando NeurixLearn Platform"
echo "==========================================="
echo ""

cd /Users/joanbalbin/IdeaProjects/Java/Cursos/Fundamentos/NeurixLearn-Microservices-master

echo "⏹️  Deteniendo servicios..."
docker-compose down

echo ""
echo "🚀 Iniciando servicios nuevamente..."
docker-compose up -d

echo ""
echo "⏳ Esperando que los servicios inicien (30 segundos)..."
sleep 30

echo ""
echo "✅ Estado de los servicios:"
docker-compose ps

echo ""
echo "==========================================="
echo "  ✅ NeurixLearn Platform Reiniciado"
echo "==========================================="
echo ""
echo "📍 URLs disponibles:"
echo "      http://localhost:8761"
echo "      http://localhost:8080/learne/swagger-ui/index.html"
echo "      http://localhost:8080/profile/swagger-ui/index.html"
echo ""

