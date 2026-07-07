#!/bin/bash

echo "==========================================="
echo "  Deteniendo NeurixLearn Platform"
echo "==========================================="
echo ""

cd /Users/joanbalbin/IdeaProjects/Java/Cursos/Fundamentos/NeurixLearn-Microservices-master

docker-compose down

echo ""
echo "✅ Todos los servicios han sido detenidos"
echo ""
echo "💡 Para iniciar nuevamente, ejecuta:"
echo "   ./start.sh"
echo ""

