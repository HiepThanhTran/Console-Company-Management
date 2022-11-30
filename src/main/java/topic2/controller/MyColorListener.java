package topic2.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import topic2.view.MyColorView;

public class MyColorListener implements ActionListener {

    private final MyColorView view;

    public MyColorListener(MyColorView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        switch (src) {
            case "RED TEXT" -> {
                this.view.getMyColorModel().setForeGround(Color.RED);
                this.view.changeTextColor();
            }
            case "YELLOW TEXT" -> {
                this.view.getMyColorModel().setForeGround(Color.YELLOW);
                this.view.changeTextColor();
            }
            case "GREEN TEXT" -> {
                this.view.getMyColorModel().setForeGround(Color.GREEN);
                this.view.changeTextColor();
            }
            case "RED BACKGROUND" -> {
                this.view.getMyColorModel().setBackground(Color.RED);
                this.view.changeBackgroundColor();
            }
            case "YELLOW BACKGROUND" -> {
                this.view.getMyColorModel().setBackground(Color.YELLOW);
                this.view.changeBackgroundColor();
            }
            case "GREEN BACKGROUND" -> {
                this.view.getMyColorModel().setBackground(Color.GREEN);
                this.view.changeBackgroundColor();
            }
        }
    }
}
