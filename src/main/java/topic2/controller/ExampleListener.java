package topic2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import topic2.view.ExampleView;

public class ExampleListener implements MouseListener, MouseMotionListener, ActionListener {

    private final ExampleView exampleView;

    public ExampleListener(ExampleView exampleView) {
        this.exampleView = exampleView;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.exampleView.updateXY(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.exampleView.countClick();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            this.exampleView.getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.exampleView.enter();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.exampleView.exit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("Exit")) {
            System.exit(0);
        } else {
            this.exampleView.changeTxtJLabel("Bạn đã click vào " + src);
        }
    }
}
