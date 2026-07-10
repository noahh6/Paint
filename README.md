# Java Swing Paint Application

A lightweight, desktop-based digital canvas application built using Java Swing and AWT. This project provides a clean GUI with essential drawing tools, a dynamic color palette, and adjustable strokes for quick sketching and doodling.

---

## Features

*   **Shape Tools:** Toggle easily between free-hand lines and rectangle drawing modes.
*   **Dynamic Stroke Control:** Adjust the brush thickness precisely from `0` to `100` pixels using an interactive slider.
*   **Custom Color Picking:** Choose unique line and fill colors using native `JColorChooser` dialogs.
*   **Fill Toggle:** Switch between filled shapes or empty outlines with a single click.
*   **Canvas Clear:** Wipe the entire canvas clean with a dedicated reset action to start a fresh masterpiece.

---

## Project Structure

The codebase is modularized into three main components:
*   `Main.java`: Initializes the main application window (`JFrame`) and coordinates the layout.
*   `SidePanel.java`: Contains the toolbar interface, buttons, sliders, and event listeners.
*   `PaintPanel.java`: Handles the custom `BufferedImage` canvas drawing logic and mouse motion tracking via `Graphics2D`.

---

## How to Run

### Prerequisites
Make sure you have the Java Development Kit (JDK 8 or higher) installed on your system.

### Option 1: Using the Terminal
1. Clone the repository or download the source files:
   ```bash
   git clone [https://github.com/noahh6/Paint.git](https://github.com/noahh6/Paint.git)
   cd Paint/src
