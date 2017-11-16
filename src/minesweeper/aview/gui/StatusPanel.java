package minesweeper.aview.gui;

import minesweeper.controller.IMinesweeperController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class StatusPanel extends JPanel {
    private JTextArea statusLabel;
    private IMinesweeperController controller;

    private static final long serialVersionUID = 1L;

    StatusPanel(final IMinesweeperController controller) {
        this.controller = controller;
        setBorder(BorderFactory.createLoweredBevelBorder());
        statusLabel = new JTextArea(2, 30);
        statusLabel.setEditable(false);
        statusLabel.setHighlighter(null);
        statusLabel.setLineWrap(true);
        statusLabel.setWrapStyleWord(true);
        statusLabel.setBorder(new EmptyBorder(0, 5, 0, 5));
        statusLabel.setBackground(getBackground());
        statusLabel.setFont(getFont());
        add(statusLabel);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    }

    void updateText() {
        statusLabel.setText(controller.getStatusLine());
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

}
