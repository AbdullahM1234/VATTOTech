#!/bin/bash

PROJECT_DIR="/Users/abdullahmobashar/IdeaProjects/VATTOTech/File"
SRC_DIR="$PROJECT_DIR/src/main/java"
BIN_DIR="$PROJECT_DIR/bin"
MAIN_CLASS="InventoryManager"

mkdir -p "$BIN_DIR"

if [ ! -d "$SRC_DIR" ] || [ -z "$(ls "$SRC_DIR"/*.java 2>/dev/null)" ]; then
    echo "No Java files found. Check your project."
    exit 1
fi


echo "🔨 Compiling Java files..."
javac -d "$BIN_DIR" "$SRC_DIR"/*.java

if [ $? -eq 0 ]; then
    echo " Program successful."
else
    echo "Program failed. Check your files."
    exit 1
fi

# Run the program
echo "🚀 Running the Inventory Manager..."
java -cp "$BIN_DIR" "$MAIN_CLASS"
