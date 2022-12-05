package topic2.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import topic2.controller.MouseExampleListener;
import topic2.model.MouseExampleModel;

public class MouseExampleView extends JFrame {

    private final MouseExampleModel mouseExampleModel;

    private JPanel jPanelCenter;
    private JPanel jPanelSouth;

    private JToolBar jToolBar;
    private JButton jButtonUndo;
    private JButton jButtonRedo;
    private JButton jButtonCopy;
    private JButton jButtonCut;
    private JButton jButtonPaste;

    private JPopupMenu jPopupMenu;
    private JMenu jMenuFont;
    private JMenuItem jMenuItemType;
    private JMenuItem jMenuItemSize;
    private JMenuItem jMenuItemCopy;
    private JMenuItem jMenuItemCut;
    private JMenuItem jMenuItemPaste;

    private JMenuBar jMenuBar;
    private JMenu jMenuFile;
    private JMenuItem jMenuItemNew;
    private JMenuItem jMenuItemOpen;
    private JMenuItem jMenuItemExit;
    private JMenu jMenuHelp;
    private JMenuItem jMenuItemWelcome;

    private JLabel jLabelPositions;
    private JLabel jLabelX;
    private JLabel jLabelY;
    private JLabel jLabelCount;
    private JLabel jLabelCountValue;
    private JLabel jLabelEmpty1;
    private JLabel jLabelCheckin;
    private JLabel jLabelCheckinValue;
    private JLabel jLabelEmpty2;
    private JLabel jLabelTxtCenter;

    public MouseExampleView() {
        this.mouseExampleModel = new MouseExampleModel();
        this.initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Mouse Example");
        this.setSize(1280, 800);
        this.setLocationRelativeTo(null);

        MouseExampleListener mouseExampleListener = new MouseExampleListener(this);

        Font txtFont = new Font("Arial", Font.BOLD, 30);

        // Tạo các button
        jButtonUndo = new JButton("Undo");
        jButtonUndo.setFocusable(false);
        jButtonUndo.setFont(txtFont);
        jButtonUndo.setToolTipText("Ấn vào để quay lại thao tác trước");
        jButtonUndo.addActionListener(mouseExampleListener);
        jButtonRedo = new JButton("Redo");
        jButtonRedo.setFocusable(false);
        jButtonRedo.setFont(txtFont);
        jButtonRedo.setToolTipText("Ấn vào đây để đi đến thao tác tiếp theo");
        jButtonRedo.addActionListener(mouseExampleListener);
        jButtonCopy = new JButton("Copy");
        jButtonCopy.setFocusable(false);
        jButtonCopy.setFont(txtFont);
        jButtonCopy.setToolTipText("Ấn vào đây để sao chép");
        jButtonCopy.addActionListener(mouseExampleListener);
        jButtonCut = new JButton("Cut");
        jButtonCut.setFocusable(false);
        jButtonCut.setFont(txtFont);
        jButtonCut.setToolTipText("Ấn vào đây để cắt");
        jButtonCut.addActionListener(mouseExampleListener);
        jButtonPaste = new JButton("Paste");
        jButtonPaste.setFocusable(false);
        jButtonPaste.setFont(txtFont);
        jButtonPaste.setToolTipText("Ấn vào đây để dán");
        jButtonPaste.addActionListener(mouseExampleListener);
        // Tạo thanh công cụ Toolbar
        jToolBar = new JToolBar("TOOLBAR", JToolBar.CENTER);
        jToolBar.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        jToolBar.setFloatable(false);
        jToolBar.add(jButtonUndo);
        jToolBar.add(jButtonRedo);
        jToolBar.add(jButtonCopy);
        jToolBar.add(jButtonCut);
        jToolBar.add(jButtonPaste);

        // Tạo menu File
        jMenuFile = new JMenu("File");
        jMenuFile.setMnemonic('F');
        jMenuItemNew = new JMenuItem("New");
        jMenuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        jMenuItemNew.addMouseListener(mouseExampleListener);
        jMenuItemNew.addActionListener(mouseExampleListener);
        jMenuItemOpen = new JMenuItem("Open");
        jMenuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        jMenuItemOpen.addMouseListener(mouseExampleListener);
        jMenuItemOpen.addActionListener(mouseExampleListener);
        jMenuItemExit = new JMenuItem("Exit");
        jMenuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_DOWN_MASK));
        jMenuItemExit.addMouseListener(mouseExampleListener);
        jMenuItemExit.addActionListener(mouseExampleListener);
        jMenuFile.add(jMenuItemNew);
        jMenuFile.add(jMenuItemOpen);
        jMenuFile.addSeparator();
        jMenuFile.add(jMenuItemExit);
        // Tạo menu Help
        jMenuHelp = new JMenu("Help");
        jMenuHelp.setMnemonic('H');
        jMenuItemWelcome = new JMenuItem("Welcome");
        jMenuItemWelcome.addMouseListener(mouseExampleListener);
        jMenuItemWelcome.addActionListener(mouseExampleListener);
        jMenuHelp.add(jMenuItemWelcome);
        // Tạo thanh Menu Bar
        jMenuBar = new JMenuBar();
        jMenuBar.add(jMenuFile);
        jMenuBar.add(jMenuHelp);

        // Tạo Popup Menu
        jMenuFont = new JMenu("Font");
        jMenuItemType = new JMenuItem("Type");
        jMenuItemSize = new JMenuItem("Size");
        jMenuFont.add(jMenuItemType);
        jMenuFont.add(jMenuItemSize);
        jMenuItemCopy = new JMenuItem("Copy");
        jMenuItemCut = new JMenuItem("Cut");
        jMenuItemPaste = new JMenuItem("Paste");
        jPopupMenu = new JPopupMenu();
        jPopupMenu.add(jMenuFont);
        jPopupMenu.add(jMenuItemCopy);
        jPopupMenu.add(jMenuItemCut);
        jPopupMenu.add(jMenuItemPaste);

        // Tạo Panel ở giữa để thao tác với chuột
        jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new BorderLayout());
        jPanelCenter.setOpaque(true);
        jPanelCenter.setBackground(Color.PINK);
        jPanelCenter.addMouseListener(mouseExampleListener);
        jPanelCenter.addMouseMotionListener(mouseExampleListener);
        jLabelTxtCenter = new JLabel("ĐÂY LÀ TEXT ...", JLabel.CENTER);
        jLabelTxtCenter.setFont(txtFont);
        jPanelCenter.add(jLabelTxtCenter, BorderLayout.CENTER);

        // Tạo Panel ở dưới chứa các thông tin
        jPanelSouth = new JPanel();
        jPanelSouth.setOpaque(true);
        jPanelSouth.setBackground(Color.GRAY);
        jPanelSouth.setLayout(new GridLayout(3, 3));
        jLabelPositions = new JLabel("Positions: ");
        jLabelPositions.setFont(txtFont);
        jLabelX = new JLabel("X = ");
        jLabelX.setFont(txtFont);
        jLabelY = new JLabel("Y = ");
        jLabelY.setFont(txtFont);
        jLabelCount = new JLabel("Number of clicks: ");
        jLabelCount.setFont(txtFont);
        jLabelCountValue = new JLabel("...");
        jLabelCountValue.setFont(txtFont);
        jLabelEmpty1 = new JLabel();
        jLabelEmpty1.setFont(txtFont);
        jLabelCheckin = new JLabel("Mouse is in components: ");
        jLabelCheckin.setFont(txtFont);
        jLabelCheckinValue = new JLabel("NO");
        jLabelCheckinValue.setFont(txtFont);
        jLabelEmpty2 = new JLabel();
        jLabelEmpty2.setFont(txtFont);
        jPanelSouth.add(jLabelPositions);
        jPanelSouth.add(jLabelX);
        jPanelSouth.add(jLabelY);
        jPanelSouth.add(jLabelCount);
        jPanelSouth.add(jLabelCountValue);
        jPanelSouth.add(jLabelEmpty1);
        jPanelSouth.add(jLabelCheckin);
        jPanelSouth.add(jLabelCheckinValue);
        jPanelSouth.add(jLabelEmpty2);

        this.setLayout(new BorderLayout(10, 10));
        this.addMouseListener(mouseExampleListener);
        this.add(jPanelCenter, BorderLayout.CENTER);
        this.add(jPanelSouth, BorderLayout.SOUTH);
        this.add(jToolBar, BorderLayout.NORTH);
        this.add(jPopupMenu);
        this.setJMenuBar(jMenuBar);
        this.setVisible(true);
    }

    public void countClick() {
        this.mouseExampleModel.setCount(this.mouseExampleModel.getCount() + 1);
        this.jLabelCountValue.setText(this.mouseExampleModel.getCount() + "");
    }

    public void enter() {
        this.jLabelCheckinValue.setText("YES");
    }

    public void exit() {
        this.jLabelCountValue.setText("...");
        this.jLabelCheckinValue.setText("NO");
    }

    public void updateXY(double x, double y) {
        this.jLabelX.setText("X = " + x);
        this.jLabelY.setText("Y = " + y);
    }

    public void changeTxtJLabel(String txt) {
        this.jLabelTxtCenter.setText(txt);
    }

    public JPopupMenu getjPopupMenu() {
        return jPopupMenu;
    }

    public void setjPopupMenu(JPopupMenu jPopupMenu) {
        this.jPopupMenu = jPopupMenu;
    }
}
