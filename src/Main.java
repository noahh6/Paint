import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Java Paint");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setLayout(new BorderLayout(10, 10)); 

            PaintPanel p = new PaintPanel();
            SidePanel s = new SidePanel(p);
            JPanel top = new JPanel();
            top.setBackground(Color.LIGHT_GRAY);
            JLabel topText = new JLabel("Java Paint");
            topText.setFont(new Font("Arial", Font.BOLD, 20));
            top.add(topText);
            frame.add(s, BorderLayout.LINE_START); // Left side
            frame.add(p, BorderLayout.CENTER);
            frame.add(top, BorderLayout.NORTH);

            frame.setPreferredSize(new Dimension(1500, 1000));
            frame.pack();
            frame.setLocationRelativeTo(null); // Center window on screen
            frame.setVisible(true);
        });
    }
    
}
