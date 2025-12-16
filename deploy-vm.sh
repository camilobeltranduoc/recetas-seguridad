#!/bin/bash

# Script de deployment para VM Oracle Cloud
# Sistema de Gestión de Recetas con Seguridad

set -e  # Salir si hay error

echo "=========================================="
echo "DEPLOYMENT DE APLICACIÓN DE RECETAS"
echo "=========================================="

# Variables
REPO_URL="TU_REPO_GIT_AQUI"  # Debes reemplazar esto con tu URL de GitHub
APP_DIR="$HOME/recetas-seguridad"

# Generar JWT Secret seguro
JWT_SECRET=$(openssl rand -hex 64)

echo "[1/6] Clonando repositorio..."
if [ -d "$APP_DIR" ]; then
    echo "   El directorio ya existe, actualizando..."
    cd "$APP_DIR"
    git pull
else
    git clone "$REPO_URL" "$APP_DIR"
    cd "$APP_DIR"
fi

echo "[2/6] Creando archivo .env con variables de entorno..."
cat > .env << EOF
# JWT Configuration
JWT_SECRET=$JWT_SECRET

# User passwords
USER1_PASSWORD=ChileReceta2024!
USER2_PASSWORD=RecetasSeguras2024!
ADMIN_PASSWORD=Admin$ecur3_2024!

# Database configuration
MYSQL_ROOT_PASSWORD=RootP@ssw0rd2024!
MYSQL_DATABASE=recetas_db
MYSQL_USER=recetas_user
MYSQL_PASSWORD=Recet@sDB2024!
EOF

echo "[3/6] Configurando permisos..."
chmod 600 .env

echo "[4/6] Construyendo imagen Docker..."
docker build -t recetas-seguridad:latest .

echo "[5/6] Deteniendo contenedores anteriores (si existen)..."
docker-compose down || true

echo "[6/6] Iniciando aplicación con Docker Compose..."
docker-compose up -d

echo ""
echo "=========================================="
echo "✅ DEPLOYMENT COMPLETADO"
echo "=========================================="
echo ""
echo "La aplicación está corriendo en:"
echo "   http://$(curl -s ifconfig.me):8080/recetas"
echo ""
echo "Para ver los logs:"
echo "   docker-compose logs -f recetas-app"
echo ""
echo "Para ver el estado de los contenedores:"
echo "   docker-compose ps"
echo ""
echo "Para detener la aplicación:"
echo "   docker-compose down"
echo ""
echo "⚠️  IMPORTANTE: Guarda tu JWT_SECRET:"
echo "   $JWT_SECRET"
echo ""
