import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class PaintPanel extends JPanel {

    public String tool;
    public int lineStroke;
    public Color lineColor, fillColor;
    public boolean fill;

    private final BufferedImage canvas;
    private final Graphics2D g2;

    private int x1, y1, x2, y2, cX, cY, lastX, lastY;
    private boolean mouseHeld;


    public PaintPanel() {
        setPreferredSize(new Dimension(1250, 900));

        canvas = new BufferedImage(1200, 900, BufferedImage.TYPE_INT_RGB);
        g2 = canvas.createGraphics();

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 1200, 900);

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                cX = x1;
                cY = y1;
                lastX = x1;
                lastY = y1;

                brush(lastX, lastY);
                mouseHeld = true;
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                cX = e.getX();
                cY = e.getY();

                brush(lastX, lastY, cX, cY);

                lastX = cX;
                lastY = cY;

                repaint();
            }

            @Override
            public void mouseReleased (MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                mouseHeld = false;

                switch (tool) {
                    case "line":
                        drawALine(x1, y1, x2, y2);
                        break;
                    case "rect":
                        drawARect(x1, y1, x2, y2);
                        break;
                    case "oval":
                        drawAOval(x1, y1, x2, y2);
                }

                repaint();
            }
        };

        // Register both listener types to the panel
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

        tool = "brush";
        lineColor = Color.BLACK;
        fillColor = Color.BLACK;
        lineStroke = 20;
    }

    private void brush(int x, int y) {

        if (tool.equals("brush")) {
            g2.setStroke(new BasicStroke(lineStroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.setColor(lineColor);
            g2.fillOval(x - lineStroke / 2, y - lineStroke / 2, lineStroke, lineStroke);
        }
        else if (tool.equals("eraser")) {
            g2.setStroke(new BasicStroke(lineStroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.setColor(Color.WHITE);
            g2.fillOval(x - lineStroke / 2, y - lineStroke / 2, lineStroke, lineStroke);
        }
    }

    private void brush(int x1, int y1, int x2, int y2) {

        if (tool.equals("brush")) {
            g2.setStroke(new BasicStroke(lineStroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.setColor(lineColor);
            g2.drawLine(x1, y1, x2, y2);
        }
        else if (tool.equals("eraser")) {
            g2.setStroke(new BasicStroke(lineStroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.setColor(Color.WHITE);
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    private void drawALine(int x1, int y1, int x2, int y2) {

        g2.setColor(lineColor);
        g2.setStroke(new BasicStroke(lineStroke));
        g2.drawLine(x1, y1, x2, y2);
    }

    private void drawARect(int x1, int y1, int x2, int y2) {

        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);


        if (fill) {
            g2.setColor(fillColor);
            g2.fillRect(x, y, width, height);
        }
        else {
            g2.setStroke(new BasicStroke(lineStroke));
            g2.setColor(lineColor);
            g2.drawRect(x, y, width, height);
        }

    }

    private void drawAOval(int x1, int y1, int x2, int y2) {

        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);

        if (fill) {
            g2.setColor(fillColor);
            g2.fillOval(x, y, width, height);
        }
        else {
            g2.setStroke(new BasicStroke(lineStroke));
            g2.setColor(lineColor);
            g2.drawOval(x, y, width, height);
        }

    }

    public void clearCanvas() {
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Restore drawing color
        g2.setColor(Color.BLACK);

        repaint();
    }

    public void toggleFill() {
        fill = !fill;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(canvas, 0, 0, null);

        if (mouseHeld) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setStroke(new BasicStroke(lineStroke));

            int x = Math.min(x1, cX);
            int y = Math.min(y1, cY);
            int width = Math.abs(cX - x1);
            int height = Math.abs(cY - y1);


            switch (tool) {
                case "line":
                    g2d.setColor(lineColor);
                    g2d.drawLine(x1, y1, cX, cY);
                    break;
                case "rect":
                    if (fill) {
                        g2d.setColor(fillColor);
                        g2d.fillRect(x, y, width, height);
                    }
                    else {
                        g2d.setColor(lineColor);
                        g2d.drawRect(x, y, width, height);
                    }
                    break;
                case "oval":
                    if (fill) {
                        g2d.setColor(fillColor);
                        g2d.fillOval(x, y, width, height);
                    }
                    else {
                        g2d.setColor(lineColor);
                        g2d.drawOval(x, y, width, height);
                    }
                    break;
            }
        }
    }

    public void saveCanvas() {
        JFileChooser chooser = new JFileChooser();

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            if (!file.getName().toLowerCase().endsWith(".png")) {
                file = new File(file.getAbsolutePath() + ".png");
            }
            
            try {
                ImageIO.write(canvas, "png", file);
            }
            catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Could not save file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    // Setters
    public void setTool(String tool) {
        this.tool = tool;
    }

    public void setLineStroke(int lineStroke) {
        this.lineStroke = lineStroke;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

}
