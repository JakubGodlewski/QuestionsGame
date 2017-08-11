package com.godlewski.jakub.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jakub on 31.07.2017.
 */
public class StartNewGamePanel extends JPanel{
    JButton btnStartNewGame = new JButton("Start New Game");

    public StartNewGamePanel()
    {
        super(new GridBagLayout());
        add(btnStartNewGame);
    }
}
