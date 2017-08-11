package com.godlewski.jakub.gui.table;

import com.godlewski.jakub.classes.User;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jakub on 15.05.2017.
 */
public class CustomTableModel extends AbstractTableModel{

    private List<User> rows;
    private List<String> columnNames;

    public CustomTableModel(List<User> rows) {
        this.rows = rows;
        columnNames = new ArrayList<>();
        Collections.addAll(columnNames,
                "L.P.",
                "LOGIN",
                "NAME",
                "POINTS"
                );
    }

    public void update(List<User> rows) {
        this.rows = rows;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = rows.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return rowIndex+1;
            case 1:
                return user.getLogin();
            case 2:
                return user.getName();
            default:
                return user.getPoints();
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }
}
