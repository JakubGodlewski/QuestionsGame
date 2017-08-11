package com.godlewski.jakub.gui;

import com.godlewski.jakub.dao.DatabaseInterfaceImpl;
import com.godlewski.jakub.gui.table.CustomTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jakub on 11.08.2017.
 */
public class UsersRankingPanel extends JPanel {

    private CustomTableModel customTableModel;
    private JTable table;
    private JScrollPane scrollPane;

    public UsersRankingPanel() {
        super(new BorderLayout());

        GridBagConstraints gbcMain = new GridBagConstraints();

        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.gridx = 1200;
        customTableModel = new CustomTableModel(DatabaseInterfaceImpl.getInstance().selectUser());
        table = new JTable(customTableModel);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateTable()
    {
        customTableModel.update(DatabaseInterfaceImpl.getInstance().selectUser());
        table.updateUI();
    }
}
