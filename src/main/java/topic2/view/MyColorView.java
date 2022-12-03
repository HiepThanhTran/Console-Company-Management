package topic2.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import topic2.controller.MyColorListener;
import topic2.model.MyColorModel;

public class MyColorView extends JFrame {

    private final MyColorModel myColorModel;

    private JButton btnTextRed;
    private JButton btnTextYellow;
    private JButton btnTextGreen;
    private JButton btnBackgroundRed;
    private JButton btnBackgroundYellow;
    private JButton btnBackgroundGreen;

    private JLabel jLabel;
    private JPanel jPanel;

    public MyColorView() {
        this.myColorModel = new MyColorModel();
        this.initComponents();
    }

    public MyColorModel getMyColorModel() {
        return myColorModel;
    }

    public void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("My Color");
        this.setSize(1200, 600);
        this.setLocationRelativeTo(null);

        ActionListener ac = new MyColorListener(this);

        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        Font textFont = new Font("Arial", Font.BOLD, 40);

        jLabel = new JLabel("ĐÂY LÀ TEXT", JLabel.CENTER);
        jLabel.setFont(textFont);

        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(2, 3));

        btnTextRed = new JButton("RED TEXT");
        btnTextRed.setFont(buttonFont);
        btnTextRed.setForeground(Color.RED);
        btnTextRed.addActionListener(ac);

        btnTextYellow = new JButton("YELLOW TEXT");
        btnTextYellow.setFont(buttonFont);
        btnTextYellow.setForeground(Color.YELLOW);
        btnTextYellow.addActionListener(ac);

        btnTextGreen = new JButton("GREEN TEXT");
        btnTextGreen.setFont(buttonFont);
        btnTextGreen.setForeground(Color.GREEN);
        btnTextGreen.addActionListener(ac);

        btnBackgroundRed = new JButton("RED BACKGROUND");
        btnBackgroundRed.setFont(buttonFont);
        btnBackgroundRed.setBackground(Color.RED);
        btnBackgroundRed.setBorderPainted(false);
        btnBackgroundRed.addActionListener(ac);

        btnBackgroundYellow = new JButton("YELLOW BACKGROUND");
        btnBackgroundYellow.setFont(buttonFont);
        btnBackgroundYellow.setBackground(Color.YELLOW);
        btnBackgroundYellow.setBorderPainted(false);
        btnBackgroundYellow.addActionListener(ac);

        btnBackgroundGreen = new JButton("GREEN BACKGROUND");
        btnBackgroundGreen.setFont(buttonFont);
        btnBackgroundGreen.setBackground(Color.GREEN);
        btnBackgroundGreen.setBorderPainted(false);
        btnBackgroundGreen.addActionListener(ac);

        jPanel.add(btnTextRed);
        jPanel.add(btnTextYellow);
        jPanel.add(btnTextGreen);
        jPanel.add(btnBackgroundRed);
        jPanel.add(btnBackgroundYellow);
        jPanel.add(btnBackgroundGreen);

        this.setLayout(new BorderLayout());
        this.add(jLabel, BorderLayout.NORTH);
        this.add(jPanel, BorderLayout.CENTER);
//        this.pack();
        this.setVisible(true);
    }

    public void changeTextColor() {
        jLabel.setForeground(myColorModel.getForeGround());
    }

    public void changeBackgroundColor() {
        jLabel.setBackground(myColorModel.getBackground());
        jLabel.setOpaque(true);
    }
}
