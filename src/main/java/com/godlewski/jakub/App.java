package com.godlewski.jakub;

import com.godlewski.jakub.gui.LoginPanel;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new LoginPanel());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
    }
}
