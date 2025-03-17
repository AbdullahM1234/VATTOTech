# Unix Scrip Documentation


## Scriptt Breakdown

### 1. Define Project Paths
```bash
PROJECT_DIR="/Users/abdullahmobashar/IdeaProjects/VATTOTech/File"
SRC_DIR="$PROJECT_DIR/src/main/java"
BIN_DIR="$PROJECT_DIR/bin"
MAIN_CLASS="InventoryManager"
```
- `PROJECT_DIR` Defines Root Directory of The Project.
- `SRC_DIR` Defines Directory of all Java Files.
- `BIN_DIR` is where compiled `.class` files will be stored.
- `MAIN_CLASS` Program that is to be executed .

### 2. Create the Binary Directory
```bash
mkdir -p "$BIN_DIR"
```
- This checks and creates that the `bin` directory exists before compilation.

### 3. Check for Java Files
```bash
if [ ! -d "$SRC_DIR" ] || [ -z "$(ls "$SRC_DIR"/*.java 2>/dev/null)" ]; then
    echo "No Java files found. Check your project."
    exit 1
fi

```
- Verifies whether the source directory exists and contains `.java` files.

### 4. Compile the Java Files
```bash
echo "🔨 Compiling Java files..."
javac -d "$BIN_DIR" "$SRC_DIR"/*.java
```
- Uses `javac` to compile all `.java` files from `SRC_DIR` and stores the `.class` files in `BIN_DIR`.

### 5. Check Compilation Success
```bash
if [ $? -eq 0 ]; then
    echo  Compilation successful."
else
    echo "Compilation failed. Check your files."
    exit 1
fi
```
- If the compilation fails, the script exits with an error message.

### 6. Run the Compiled Java Program
```bash
echo "🚀 Running the Inventory Manager..."
java -cp "$BIN_DIR" "$MAIN_CLASS"
```
- Executes the compiled Java program using `java -cp`.

## How to Run the Script
1. Open a terminal and navigate to the script location.
2. Grant execute permission if needed:
   ```bash
   chmod +x script.sh
   ```
3. Run the script:
   ```bash
   ./script.sh
   ```
