#!/bin/bash

# Script de instalación para VM Oracle Cloud
# Sistema de Gestión de Recetas con Seguridad

set -e  # Salir si hay error

echo "=========================================="
echo "INSTALACIÓN DE DEPENDENCIAS"
echo "=========================================="

# Actualizar sistema
echo "[1/7] Actualizando sistema..."
sudo apt update && sudo apt upgrade -y

# Instalar Java 21
echo "[2/7] Instalando Java 21..."
sudo apt install -y openjdk-21-jdk

# Instalar Docker
echo "[3/7] Instalando Docker..."
sudo apt install -y apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt update
sudo apt install -y docker-ce docker-ce-cli containerd.io

# Instalar Docker Compose
echo "[4/7] Instalando Docker Compose..."
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# Agregar usuario actual al grupo docker
echo "[5/7] Configurando permisos de Docker..."
sudo usermod -aG docker $USER

# Instalar Git y Maven
echo "[6/7] Instalando Git y Maven..."
sudo apt install -y git maven

# Configurar firewall de Ubuntu
echo "[7/7] Configurando firewall..."
sudo ufw allow 22/tcp      # SSH
sudo ufw allow 8080/tcp    # Spring Boot
sudo ufw allow 3306/tcp    # MySQL (opcional, solo si necesitas acceso externo)
sudo ufw --force enable

echo "=========================================="
echo "VERIFICACIÓN DE INSTALACIÓN"
echo "=========================================="
echo "Java version:"
java -version
echo ""
echo "Docker version:"
docker --version
echo ""
echo "Docker Compose version:"
docker-compose --version
echo ""
echo "Git version:"
git --version
echo ""
echo "Maven version:"
mvn --version

echo "=========================================="
echo "✅ INSTALACIÓN COMPLETADA"
echo "=========================================="
echo ""
echo "⚠️  IMPORTANTE: Debes cerrar la sesión SSH y volver a conectarte"
echo "   para que los permisos de Docker se apliquen correctamente."
echo ""
echo "   Ejecuta: exit"
echo "   Luego vuelve a conectarte con SSH"
