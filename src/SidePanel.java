import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class SidePanel extends JPanel {

    private final PaintPanel paintPanel;

    public SidePanel(PaintPanel paintPanel) {
        setBackground(Color.gray);
        this.paintPanel = paintPanel;
        setPreferredSize(new Dimension(250, 900));
        setLayout(new GridLayout(0, 1, 0, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setUpGUI();
    }

    private void setUpGUI() {

        JButton brushTool = new JButton("Brush");
        brushTool.addActionListener(e -> {
            paintPanel.setTool("brush");
        });
        add(brushTool);

        JButton lineTool = new JButton("Line");
        lineTool.addActionListener(e -> {
            paintPanel.setTool("line");
        });
        add(lineTool);

        JSlider lineStroke = new JSlider(JSlider.HORIZONTAL, 0, 80, 20);
        lineStroke.setToolTipText("Line Stroke");
        lineStroke.setMajorTickSpacing(25);
        lineStroke.setMinorTickSpacing(5);
        lineStroke.setPaintTicks(true);
        lineStroke.setPaintLabels(true);
        JLabel label = new JLabel("Line Stroke: ");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.white);
        lineStroke.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Update the text label dynamically as the slider moves
                paintPanel.setLineStroke(lineStroke.getValue());
                repaint();
            }
        });
        add(label);
        add(lineStroke);

        JButton lineColor = new JButton("Line Colour");
        lineColor.setBackground(Color.BLACK);
        lineColor.addActionListener(e -> {
            paintPanel.setLineColor(JColorChooser.showDialog(null, "Choose Line Colour", Color.BLACK));
            lineColor.setBackground(paintPanel.lineColor);
            repaint();
        });
        add(lineColor);

        JButton rectTool = new JButton("Rectangle");
        rectTool.addActionListener(e -> {
            paintPanel.setTool("rect");
        });
        add(rectTool);

        JButton ovalTool = new JButton("Oval");
        ovalTool.addActionListener(e -> {
            paintPanel.setTool("oval");
        });
        add(ovalTool);

        JButton fillButton = new JButton("No Fill");
        fillButton.addActionListener(e -> {
            paintPanel.toggleFill();
            if (paintPanel.fill) fillButton.setText("Fill");
            else fillButton.setText("No Fill");
        });
        add(fillButton);

        JButton fillColor = new JButton("Fill Colour");
        fillColor.setBackground(Color.BLACK);
        fillColor.addActionListener(e -> {
            paintPanel.setFillColor(JColorChooser.showDialog(null, "Choose Fill Colour", Color.BLACK));
            fillColor.setBackground(paintPanel.fillColor);
        });
        add(fillColor);

        JButton eraser = new JButton("Eraser");
        eraser.addActionListener(e -> {
            paintPanel.setTool("eraser");
        });
        add(eraser);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            paintPanel.clearCanvas();
        });
        add(clearButton);

        JButton saveButton = new JButton("Save/Export");
        saveButton.addActionListener(e -> {
            paintPanel.saveCanvas();
        });
        add(saveButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            System.exit(0);
        });
        add(quitButton);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create(); // Create a copy
        try {
            g2.setStroke(new BasicStroke(paintPanel.lineStroke));

            int y = getHeight() - 737;

            g2.setColor(paintPanel.lineColor);
            g2.drawLine(190, y + 2, 190, y + 2);
        } finally {
            g2.dispose(); // Restore original graphics state
        }
    }
}
