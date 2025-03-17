#!/bin/bash

# Set correct project structure
PROJECT_DIR="/Users/abdullahmobashar/IdeaProjects/VATTOTech/File"
SRC_DIR="$PROJECT_DIR/src/main/java"
BIN_DIR="$PROJECT_DIR/bin"
MAIN_CLASS="InventoryManager"

# Ensure bin directory exists
mkdir -p "$BIN_DIR"

# Check if Java files exist
if [ ! -d "$SRC_DIR" ] || [ -z "$(ls -A $SRC_DIR/*.java 2>/dev/null)" ]; then
    echo "❌ No Java files found in $SRC_DIR. Check your project structure."
    exit 1
fi

# Compile Java files
echo "🔨 Compiling Java files..."
javac -d "$BIN_DIR" "$SRC_DIR"/*.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "✅ Compilation successful."
else
    echo "❌ Compilation failed. Check your Java files."
    exit 1
fi

# Run the program
echo "🚀 Running the Inventory Manager..."
java -cp "$BIN_DIR" "$MAIN_CLASS"
