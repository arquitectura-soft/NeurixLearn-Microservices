#!/bin/bash

echo "========================================="
echo "  NeurixLearn - Estado de los Servicios"
echo "========================================="
echo ""

echo "📦 Contenedores en ejecución:"
docker-compose ps

echo ""
echo "========================================="
echo "🔍 URLs de los servicios:"
echo "========================================="
echo ""
echo "🧭 Eureka Dashboard:    http://localhost:8761"
echo "🌐 API Gateway:         http://localhost:8080"
echo ""
echo "📚 Learne Service Swagger:"
echo "   http://localhost:8080/learne/swagger-ui.html"
echo ""
echo "👤 Profile Service Swagger:"
echo "   http://localhost:8080/profile/swagger-ui.html"
echo ""
echo "========================================="
echo ""
echo "💡 Tip: Espera 2-3 minutos después de 'docker-compose up' antes de acceder a Swagger"
echo ""

