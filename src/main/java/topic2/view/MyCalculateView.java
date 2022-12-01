package topic2.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

    private JPanel jPanelInput;
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

        labelValue1 = new JLabel("1st Value");
        labelValue2 = new JLabel("2nd Value");
        labelAnswer = new JLabel("Answer");
        txtValue1 = new JTextField(50);
        txtValue2 = new JTextField(50);
        txtAnswer = new JTextField(50);

        this.setVisible(true);
    }
}
