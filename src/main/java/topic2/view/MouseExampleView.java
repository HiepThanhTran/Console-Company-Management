package topic2.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MouseExampleView extends JFrame {

    private JPanel jPanelCenter;
    private JPanel jPanelSouth;

    private JLabel jLabelXY;
    private JLabel jLabelX;
    private JLabel jLabelY;
    private JLabel jLabelClick;
    private JLabel jLabelCount;
    private JLabel jLabelCheckIn;
    private JLabel jLabelCheckInValue;

    public MouseExampleView() {
        this.initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Mouse Example");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);

        jPanelCenter = new JPanel();
        jPanelCenter.setSize(600, 400);

        jPanelSouth = new JPanel();
        jPanelSouth.setSize(600, 200);

        this.setLayout(new BorderLayout());
        this.add(jPanelCenter, BorderLayout.CENTER);
        this.add(jPanelSouth, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
