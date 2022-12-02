package topic2.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import topic2.controller.MyCalculateListener;
import topic2.model.MyCalculateModel;

public class MyCalculateView extends JFrame {

    private final MyCalculateModel myCalculateModel;

    private JButton btnPlus;
    private JButton btnMinus;
    private JButton btnMultiply;
    private JButton btnDivide;
    private JButton btnMod;
    private JButton btnPow;

    private JLabel labelValue1;
    private JLabel labelValue2;
    private JLabel labelAnswer;

    private JTextField txtValue1;
    private JTextField txtValue2;
    private JTextField txtAnswer;

    private JPanel jPanelIO;
    private JPanel jPanelButtons;

    public MyCalculateView() {
        this.myCalculateModel = new MyCalculateModel();
        this.initComponents();
    }

    public MyCalculateModel getMyCalculateModel() {
        return myCalculateModel;
    }

    public void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("My Calculate");
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        ActionListener ac = new MyCalculateListener(this);

        Font txtFont = new Font("Arial", Font.BOLD, 25);
        Font btnFont = new Font("Arial", Font.BOLD, 20);

        labelValue1 = new JLabel("1st Value", JLabel.CENTER);
        labelValue1.setFont(txtFont);
        labelValue2 = new JLabel("2nd Value", JLabel.CENTER);
        labelValue2.setFont(txtFont);
        labelAnswer = new JLabel("Answer", JLabel.CENTER);
        labelAnswer.setFont(txtFont);
        txtValue1 = new JTextField(50);
        txtValue1.setFont(txtFont);
        txtValue2 = new JTextField(50);
        txtValue2.setFont(txtFont);
        txtAnswer = new JTextField(50);
        txtAnswer.setFont(txtFont);

        jPanelIO = new JPanel();
        jPanelIO.setLayout(new GridLayout(3, 2, 5, 5));
        jPanelIO.add(labelValue1);
        jPanelIO.add(txtValue1);
        jPanelIO.add(labelValue2);
        jPanelIO.add(txtValue2);
        jPanelIO.add(labelAnswer);
        jPanelIO.add(txtAnswer);

        btnPlus = new JButton("+");
        btnPlus.setFont(btnFont);
        btnPlus.addActionListener(ac);
        btnMinus = new JButton("-");
        btnMinus.setFont(btnFont);
        btnMinus.addActionListener(ac);
        btnMultiply = new JButton("*");
        btnMultiply.setFont(btnFont);
        btnMultiply.addActionListener(ac);
        btnDivide = new JButton("/");
        btnDivide.setFont(btnFont);
        btnDivide.addActionListener(ac);
        btnMod = new JButton("%");
        btnMod.setFont(btnFont);
        btnMod.addActionListener(ac);
        btnPow = new JButton("^");
        btnPow.setFont(btnFont);
        btnPow.addActionListener(ac);

        jPanelButtons = new JPanel();
        jPanelButtons.setLayout(new GridLayout(2, 3));
        jPanelButtons.add(btnPlus);
        jPanelButtons.add(btnMinus);
        jPanelButtons.add(btnMultiply);
        jPanelButtons.add(btnDivide);
        jPanelButtons.add(btnMod);
        jPanelButtons.add(btnPow);

        this.setLayout(new BorderLayout(10, 10));
        this.add(jPanelIO, BorderLayout.CENTER);
        this.add(jPanelButtons, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void add() {
        double value1 = Double.parseDouble(txtValue1.getText());
        double value2 = Double.parseDouble(txtValue2.getText());
        this.myCalculateModel.setValue1(value1);
        this.myCalculateModel.setValue2(value2);
        this.myCalculateModel.add();
        this.txtAnswer.setText(this.myCalculateModel.getAnswer() + "");
    }

    public void sub() {
        double value1 = Double.parseDouble(txtValue1.getText());
        double value2 = Double.parseDouble(txtValue2.getText());
        this.myCalculateModel.setValue1(value1);
        this.myCalculateModel.setValue2(value2);
        this.myCalculateModel.sub();
        this.txtAnswer.setText(this.myCalculateModel.getAnswer() + "");
    }

    public void multiply() {
        double value1 = Double.parseDouble(txtValue1.getText());
        double value2 = Double.parseDouble(txtValue2.getText());
        this.myCalculateModel.setValue1(value1);
        this.myCalculateModel.setValue2(value2);
        this.myCalculateModel.multiply();
        this.txtAnswer.setText(this.myCalculateModel.getAnswer() + "");
    }

    public void divide() {
        double value1 = Double.parseDouble(txtValue1.getText());
        double value2 = Double.parseDouble(txtValue2.getText());
        this.myCalculateModel.setValue1(value1);
        this.myCalculateModel.setValue2(value2);
        this.myCalculateModel.divide();
        this.txtAnswer.setText(this.myCalculateModel.getAnswer() + "");
    }

    public void mod() {
        double value1 = Double.parseDouble(txtValue1.getText());
        double value2 = Double.parseDouble(txtValue2.getText());
        this.myCalculateModel.setValue1(value1);
        this.myCalculateModel.setValue2(value2);
        this.myCalculateModel.mod();
        this.txtAnswer.setText(this.myCalculateModel.getAnswer() + "");
    }

    public void pow() {
        double value1 = Double.parseDouble(txtValue1.getText());
        double value2 = Double.parseDouble(txtValue2.getText());
        this.myCalculateModel.setValue1(value1);
        this.myCalculateModel.setValue2(value2);
        this.myCalculateModel.pow();
        this.txtAnswer.setText(this.myCalculateModel.getAnswer() + "");
    }
}
