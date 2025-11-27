#!/bin/bash

echo "🚀 Starting SmartAllies Incident Reporting Backend Setup..."

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 17 or higher."
    exit 1
fi

echo "✅ Java found: $(java -version 2>&1 | head -n 1)"

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven 3.6+."
    exit 1
fi

echo "✅ Maven found: $(mvn -version | head -n 1)"

# Check if Ollama is installed
if ! command -v ollama &> /dev/null; then
    echo "⚠️  Ollama is not installed. Please install from https://ollama.ai"
    echo "   After installation, run: ollama pull llama3.2"
    exit 1
fi

echo "✅ Ollama found"

# Start Ollama service in background if not running
if ! curl -s http://localhost:11434/api/tags > /dev/null 2>&1; then
    echo "📡 Starting Ollama service..."
    ollama serve > /dev/null 2>&1 &
    sleep 3
fi

# Check if llama3.2 model is available
echo "🔍 Checking for llama3.2 model..."
if ! ollama list | grep -q "llama3.2"; then
    echo "📥 Pulling llama3.2 model (this may take a few minutes)..."
    ollama pull llama3.2
else
    echo "✅ llama3.2 model found"
fi

# Build the application
echo "🔨 Building application..."
mvn clean install -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Build successful!"
    echo ""
    echo "🎉 Setup complete! Starting application..."
    echo ""
    mvn spring-boot:run
else
    echo "❌ Build failed. Please check the errors above."
    exit 1
fi
