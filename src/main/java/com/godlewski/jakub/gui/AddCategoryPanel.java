package com.godlewski.jakub.gui;

import com.godlewski.jakub.classes.Category;
import com.godlewski.jakub.dao.DatabaseInterfaceImpl;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jakub on 30.07.2017.
 */
public class AddCategoryPanel extends JPanel {

    private JLabel lblName = new JLabel("Name: ");
    private JTextField tfName = new JTextField(10);
    private JButton btnAdd = new JButton("Add");
    private JButton btnClear = new JButton("Clear");
    private JButton btnClose = new JButton("Close");
    AddQuestionPanel addQuestionPanel;

    public AddCategoryPanel(AddQuestionPanel addQuestionPanel) {
        super(new GridBagLayout());
        this.addQuestionPanel = addQuestionPanel;
        GridBagConstraints gbcPosition = new GridBagConstraints();
        JPanel panelFields = new JPanel(new GridBagLayout());
        gbcPosition.gridx=0;
        gbcPosition.gridy=0;
        panelFields.add(lblName, gbcPosition);
        gbcPosition.gridx=1;
        gbcPosition.gridy=0;
        panelFields.add(tfName, gbcPosition);
        JPanel panelButtons = new JPanel(new GridBagLayout());
        gbcPosition.gridx=0;
        gbcPosition.gridy=0;
        panelButtons.add(btnAdd, gbcPosition);
        btnAdd.addActionListener(x -> add());
        gbcPosition.gridx=1;
        gbcPosition.gridy=0;
        panelButtons.add(btnClear, gbcPosition);
        btnClear.addActionListener(x -> clear());
        gbcPosition.gridx=2;
        gbcPosition.gridy=0;
        panelButtons.add(btnClose, gbcPosition);
        btnClose.addActionListener(x ->close());

        JPanel panelMain = new JPanel(new GridBagLayout());
        gbcPosition.gridx =0;
        gbcPosition.gridy =0;
        panelMain.add(panelFields, gbcPosition);
        gbcPosition.gridx =0;
        gbcPosition.gridy =1;
        panelMain.add(panelButtons, gbcPosition);

        add(panelMain);

    }

    private void add()
    {
        if(tfName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fill category name");
            return;
        }
        Category category = new Category(0, tfName.getText());
        DatabaseInterfaceImpl.getInstance().insertCategory(category);
        clear();
        JOptionPane.showMessageDialog(null, "Category added to database");
        addQuestionPanel.updateCategoryList(category);
    }

    private void clear()
    {
        tfName.setText("");
    }

    private void close()
    {
        JFrame thisFrame = (JFrame)this.getRootPane().getParent();
        thisFrame.dispose();
    }
}
