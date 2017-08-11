package com.godlewski.jakub.gui;

import com.godlewski.jakub.classes.User;
import com.godlewski.jakub.dao.DatabaseInterfaceImpl;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jakub on 29.07.2017.
 */
public class LoginPanel extends JPanel{

    private JLabel lblLogin = new JLabel("Login: ");
    private JLabel lblPassword = new JLabel("Password: ");
    private JTextField tfLogin = new JTextField(10);
    private JPasswordField pfPassword = new JPasswordField(10);
    private JButton btnLogin = new JButton("Login");
    private JButton btnRegister = new JButton("Register");
    private JButton btnClose = new JButton("Close");

    public LoginPanel()
    {
        super(new GridBagLayout());
        GridBagConstraints gbcPosition = new GridBagConstraints();
        JPanel panelFields = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelFields.add(lblLogin, gbcPosition);
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 0;
        panelFields.add(tfLogin, gbcPosition);
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 1;
        panelFields.add(lblPassword, gbcPosition);
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 1;
        panelFields.add(pfPassword, gbcPosition);

        JPanel panelButtons = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelButtons.add(btnLogin, gbcPosition);
        btnLogin.addActionListener(x ->login());
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 0;
        panelButtons.add(btnRegister, gbcPosition);
        btnRegister.addActionListener(x -> register());
        gbcPosition.gridx = 2;
        gbcPosition.gridy = 0;
        panelButtons.add(btnClose, gbcPosition);
        btnClose.addActionListener(x -> close());

        JPanel panelMain = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelMain.add(panelFields, gbcPosition);
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 1;
        panelMain.add(panelButtons, gbcPosition);

        add(panelMain);
    }

    private void login()
    {
        String password = String.valueOf(pfPassword.getPassword());
        User user = DatabaseInterfaceImpl.getInstance().getUserByLogin(tfLogin.getText().toString());
        if(user==null || !user.getPassword().equals(password))
            JOptionPane.showMessageDialog(null, "Invalid login or password!");
        else
            showMainPanel(user);
    }

    private void register()
    {
        JFrame frame = new JFrame("Register");
        RegisterPanel panel = new RegisterPanel();
        panel.setVisible(true);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
    }

    private void showMainPanel(User user)
    {
        JFrame frame = new JFrame("Questions Game");
        MainPanel panel = new MainPanel(user);
        panel.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setJMenuBar(panel.createMenuBar());
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();

        JFrame thisFrame = (JFrame)this.getRootPane().getParent();
        thisFrame.dispose();
    }

    private void close()
    {
        JFrame thisFrame = (JFrame)this.getRootPane().getParent();
        thisFrame.dispose();
    }
}
