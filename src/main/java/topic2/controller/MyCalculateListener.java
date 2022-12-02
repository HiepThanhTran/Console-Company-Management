package topic2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import topic2.view.MyCalculateView;

public class MyCalculateListener implements ActionListener {

    private final MyCalculateView myCalculateView;

    public MyCalculateListener(MyCalculateView myCalculateView) {
        this.myCalculateView = myCalculateView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        switch (src) {
            case "+" -> this.myCalculateView.add();
            case "-" -> this.myCalculateView.sub();
            case "*" -> this.myCalculateView.multiply();
            case "/" -> this.myCalculateView.divide();
            case "%" -> this.myCalculateView.mod();
            case "^" -> this.myCalculateView.pow();
        }
    }
}
