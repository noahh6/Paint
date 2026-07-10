import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class SidePanel extends JPanel {

    private final PaintPanel paintPanel;

    public SidePanel(PaintPanel paintPanel) {
        setBackground(Color.gray);
        this.paintPanel = paintPanel;
        setPreferredSize(new Dimension(300, 900));
        setLayout(new GridLayout(0, 1, 0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setUpGUI();
    }

    private void setUpGUI() {
        JButton lineTool = new JButton("Line");
        lineTool.addActionListener(e -> {
            PaintPanel.tool = "line";
        });
        add(lineTool);

        JSlider lineStroke = new JSlider(JSlider.HORIZONTAL, 0, 100, 5);
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
                PaintPanel.lineStroke = lineStroke.getValue();
                repaint();
            }
        });
        add(label);
        add(lineStroke);

        JButton lineColor = new JButton("Line Colour");
        lineColor.setBackground(Color.BLACK);
        lineColor.addActionListener(e -> {
            PaintPanel.lineColor = JColorChooser.showDialog(null, "Choose Line Colour", Color.BLACK);
            lineColor.setBackground(PaintPanel.lineColor);
            repaint();
        });
        add(lineColor);

        JButton rectTool = new JButton("Rectangle");
        rectTool.addActionListener(e -> {
            PaintPanel.tool = "rect";
        });
        add(rectTool);

        JButton ovalTool = new JButton("Oval");
        ovalTool.addActionListener(e -> {
            PaintPanel.tool = "oval";
        });
        add(ovalTool);

        JButton fillButton = new JButton("No Fill");
        fillButton.addActionListener(e -> {
            paintPanel.toggleFill();
            if(PaintPanel.fill) {
                fillButton.setText("Fill");
            }
            else {
                fillButton.setText("No Fill");
            }
        });
        add(fillButton);

        JButton fillColor = new JButton("Fill Colour");
        fillColor.setBackground(Color.BLACK);
        fillColor.addActionListener(e -> {
            PaintPanel.fillColor = JColorChooser.showDialog(null, "Choose Fill Colour", Color.BLACK);
            fillColor.setBackground(PaintPanel.fillColor);
        });
        add(fillColor);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            paintPanel.clearCanvas();
        });
        add(clearButton);

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
            g2.setStroke(new BasicStroke(PaintPanel.lineStroke));

            int y = getHeight() - 773;


            g2.setColor(Color.WHITE);
            g2.drawRect(224, y, 4, 4);
            g2.setColor(PaintPanel.lineColor);
            g2.drawLine(226, y + 2, 226, y + 2);
        } finally {
            g2.dispose(); // Restore original graphics state
        }
    }
}
