import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class PaintPanel extends JPanel {

    public static String tool;
    public static int lineStroke;
    public static Color lineColor;
    public static Color fillColor;
    public static boolean fill;

    private BufferedImage canvas;
    private Graphics2D g2;

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private boolean mouseHeld;


    public PaintPanel() {
        setPreferredSize(new Dimension(1200, 1000));

        canvas = new BufferedImage(1200, 1000, BufferedImage.TYPE_INT_RGB);
        g2 = canvas.createGraphics();

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 1200, 1000);

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                mouseHeld = true;
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
                }

                repaint();
            }
        };

        // Register both listener types to the panel
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

        tool = "line";
        lineColor = Color.BLACK;
        fillColor = Color.BLACK;
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

    public void clearCanvas() {
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Restore drawing color
        g2.setColor(Color.BLACK);

        repaint();
    }

    public void toggleFill() {
        this.fill = !this.fill;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(canvas, 0, 0, null);
    }


}
