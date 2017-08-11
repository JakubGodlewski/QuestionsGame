package com.godlewski.jakub.gui;

import com.godlewski.jakub.classes.User;
import com.godlewski.jakub.dao.DatabaseInterfaceImpl;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jakub on 29.07.2017.
 */
public class RegisterPanel extends JPanel{

    private JLabel lblLogin = new JLabel("Login: ");
    private JLabel lblPassword = new JLabel("Password: ");
    private JLabel lblConfirmPassword = new JLabel("Confirm Password: ");
    private JLabel lblName = new JLabel("Name: ");
    private JTextField tfLogin = new JTextField(10);
    private JPasswordField pfPassword = new JPasswordField(10);
    private JPasswordField pfConfirmPassword = new JPasswordField(10);
    private JTextField tfName = new JTextField(10);
    private JButton btnRegister = new JButton("Register");
    private JButton btnClose = new JButton("Close");
    private String login;
    private String password1;
    private String password2;

    public RegisterPanel()
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
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 2;
        panelFields.add(lblConfirmPassword, gbcPosition);
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 2;
        panelFields.add(pfConfirmPassword, gbcPosition);
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 3;
        panelFields.add(lblName, gbcPosition);
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 3;
        panelFields.add(tfName, gbcPosition);

        JPanel panelButtons = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelButtons.add(btnRegister, gbcPosition);
        btnRegister.addActionListener(x -> register());
        gbcPosition.gridx = 1;
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

    private void register()
    {
        login = tfLogin.getText();
        password1 = String.valueOf(pfPassword.getPassword());
        password2 = String.valueOf(pfConfirmPassword.getPassword());

        if(!password1.equals(password2))
            JOptionPane.showMessageDialog(null, "Passwords aren't the same");
        else if(DatabaseInterfaceImpl.getInstance().getUserByLogin(login)!=null)
            JOptionPane.showMessageDialog(null, "Given login is already taken");
        else
        {
            User user = new User(0, tfLogin.getText().toString(), password1, tfName.getText(), 0);
            DatabaseInterfaceImpl.getInstance().insertUser(user);
            clear();
            JOptionPane.showMessageDialog(null, "User added to database");
        }
    }

    private void clear()
    {
        tfLogin.setText("");
        pfPassword.setText("");
        pfConfirmPassword.setText("");
        tfName.setText("");
    }

    private void close()
    {
        JFrame thisFrame = (JFrame)this.getRootPane().getParent();
        thisFrame.dispose();
    }
}
