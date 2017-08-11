package com.godlewski.jakub.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by jakub on 31.07.2017.
 */
public class AboutPanel extends JPanel{

    private JLabel lblAbout = new JLabel("<html>Author: Jakub Godlewski<br>Made as a part of<br>KM-programs course<br>km-programs.pl</html>");
    private JButton btnClose = new JButton("Close");

    public AboutPanel()
    {
        super(new GridBagLayout());
        GridBagConstraints gbcPosition = new GridBagConstraints();
        JPanel panelMain = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelMain.add(lblAbout, gbcPosition);
        lblAbout.setBorder(new EmptyBorder(10,10,10,10));
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 1;
        panelMain.add(btnClose, gbcPosition);
        btnClose.addActionListener(x -> close());

        add(panelMain);
    }

    private void close()
    {
        JFrame thisFrame = (JFrame)this.getRootPane().getParent();
        thisFrame.dispose();
    }
}
