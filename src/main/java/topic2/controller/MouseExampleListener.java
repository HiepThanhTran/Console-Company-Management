package topic2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import topic2.view.MouseExampleView;

public class MouseExampleListener implements MouseListener, MouseMotionListener, ActionListener {

    private final MouseExampleView mouseExampleView;

    public MouseExampleListener(MouseExampleView mouseExampleView) {
        this.mouseExampleView = mouseExampleView;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouseExampleView.updateXY(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.mouseExampleView.countClick();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            this.mouseExampleView.getjPopupMenu().show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.mouseExampleView.enter();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.mouseExampleView.exit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        switch (src) {
            case "New" -> this.mouseExampleView.changeTxtJLabel("Bạn đã click vào New");
            case "Open" -> this.mouseExampleView.changeTxtJLabel("Bạn đã click vào Open");
            case "Welcome" -> this.mouseExampleView.changeTxtJLabel("Bạn đã click vào Welcome");
            case "Undo" -> this.mouseExampleView.changeTxtJLabel("Bạn đã click vào Undo");
            case "Redo" -> this.mouseExampleView.changeTxtJLabel("Bạn đã click vào Redo");
            case "Copy" -> this.mouseExampleView.changeTxtJLabel("Bạn đã click vào Copy");
            case "Cut" -> this.mouseExampleView.changeTxtJLabel("Bạn đã click vào Cut");
            case "Paste" -> this.mouseExampleView.changeTxtJLabel("Bạn đã click vào Paste");
            case "Exit" -> System.exit(0);
        }
    }
}
