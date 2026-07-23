# Java Swing Paint Application

A lightweight, feature-rich desktop painting application built in Java using **Swing** and **AWT**. This project mimics classic desktop drawing tools with a clean user interface, supporting freehand drawing, geometric shapes, custom brush strokes, color pickers, and PNG exporting.

---

## Features

* **Drawing Tools:**
* **Brush:** Freehand painting with real-time stroke tracking.
* **Line:** Click-and-drag straight lines.
* **Rectangle & Oval:** Draw geometric shapes with optional solid fills or outlines.
* **Eraser:** Easily clean up mistakes by erasing strokes.


* **Customization Controls:**
* Adjustable **Line Stroke / Thickness** slider (ranging from 0 to 80).
* Interactive **Color Chooser** for both line/stroke colors and fill colors.
* **Fill / No Fill** toggle button for shapes.


* **Canvas Management:**
* **Clear:** Instantly wipe the entire canvas clean.
* **Save/Export:** Export your artwork directly as a PNG file via a file chooser dialog.
* **Quit:** Exit the application cleanly.



---

## Project Structure

The project is broken down into three core Java classes located in the `src` package:

1. **`Main.java`**: Initializes the main application window (`JFrame`), sets up the primary layout, and binds the components together.
2. **`PaintPanel.java`**: The core drawing canvas. Handles mouse motion events, shape rendering, buffering, and image export functionality.
3. **`SidePanel.java`**: The control panel containing all the buttons, sliders, and color choosers for interacting with the canvas.

---

## Getting Started

### Prerequisites

* **Java Development Kit (JDK)** installed on your system (JDK 8 or higher recommended).

### Running the Application

1. Clone or download this repository.
2. Navigate to the `src` directory or open the project in your favorite Java IDE (such as IntelliJ IDEA, Eclipse, or NetBeans).
3. Compile the Java files:
```bash
javac Main.java PaintPanel.java SidePanel.java

```


4. Run the application:
```bash
java Main

```
