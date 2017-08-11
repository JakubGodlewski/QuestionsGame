package com.godlewski.jakub.gui;

import com.godlewski.jakub.classes.Category;
import com.godlewski.jakub.classes.Question;
import com.godlewski.jakub.dao.DatabaseInterfaceImpl;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jakub on 30.07.2017.
 */
public class AddQuestionPanel extends JPanel {

    private JLabel lblCategory = new JLabel("Category: ");
    private JComboBox cbCategory = new JComboBox();
    private JButton btnAddCategory = new JButton("+");
    private JLabel lblQuestion = new JLabel("Question: ");
    private JTextArea taQuestion = new JTextArea(3, 20);
    private JScrollPane spQuestion = new JScrollPane(taQuestion);
    private JLabel lblAnswer1 = new JLabel("Answer 1: ");
    private JTextField tfAnswer1 = new JTextField(10);
    private JRadioButton rbAnswer1 = new JRadioButton();
    private JLabel lblAnswer2 = new JLabel("Answer 2: ");
    private JTextField tfAnswer2 = new JTextField(10);
    private JRadioButton rbAnswer2 = new JRadioButton();
    private JLabel lblAnswer3 = new JLabel("Answer 3: ");
    private JTextField tfAnswer3 = new JTextField(10);
    private JRadioButton rbAnswer3 = new JRadioButton();
    private JLabel lblAnswer4 = new JLabel("Answer 4: ");
    private JTextField tfAnswer4 = new JTextField(10);
    private JRadioButton rbAnswer4 = new JRadioButton();
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JLabel lblCorrect = new JLabel("Correct:");
    private JButton btnAdd = new JButton("Add");
    private JButton btnClear = new JButton("Clear");
    private JButton btnClose = new JButton("Close");
    private java.util.List<Category> categoryList;
    private int categoryId;

    public AddQuestionPanel()
    {
        super(new GridBagLayout());
        GridBagConstraints gbcPosition = new GridBagConstraints();
        JPanel panelCategory = new JPanel(new GridBagLayout());
        categoryList = DatabaseInterfaceImpl.getInstance().selectCategory();
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelCategory.add(lblCategory, gbcPosition);
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 0;
        panelCategory.add(cbCategory, gbcPosition);
        categoryList.forEach(x -> cbCategory.addItem(x.getName()));
        gbcPosition.gridx = 2;
        gbcPosition.gridy = 0;
        panelCategory.add(btnAddCategory, gbcPosition);
        btnAddCategory.addActionListener(x -> addNewCategory(this));
        JPanel panelQuestion = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelQuestion.add(lblQuestion, gbcPosition);
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 1;
        taQuestion.setLineWrap(true);
        taQuestion.setWrapStyleWord(true);
        panelQuestion.add(spQuestion, gbcPosition);
        JPanel panelAnswers = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 2;
        gbcPosition.gridy = 0;
        panelAnswers.add(lblCorrect, gbcPosition);
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 1;
        panelAnswers.add(lblAnswer1, gbcPosition);
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 1;
        panelAnswers.add(tfAnswer1, gbcPosition);
        gbcPosition.gridx = 2;
        gbcPosition.gridy = 1;
        panelAnswers.add(rbAnswer1, gbcPosition);
        buttonGroup.add(rbAnswer1);
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 2;
        panelAnswers.add(lblAnswer2, gbcPosition);
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 2;
        panelAnswers.add(tfAnswer2, gbcPosition);
        gbcPosition.gridx = 2;
        gbcPosition.gridy = 2;
        panelAnswers.add(rbAnswer2, gbcPosition);
        buttonGroup.add(rbAnswer2);
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 3;
        panelAnswers.add(lblAnswer3, gbcPosition);
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 3;
        panelAnswers.add(tfAnswer3, gbcPosition);
        gbcPosition.gridx = 2;
        gbcPosition.gridy = 3;
        panelAnswers.add(rbAnswer3, gbcPosition);
        buttonGroup.add(rbAnswer3);
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 4;
        panelAnswers.add(lblAnswer4, gbcPosition);
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 4;
        panelAnswers.add(tfAnswer4, gbcPosition);
        gbcPosition.gridx = 2;
        gbcPosition.gridy = 4;
        panelAnswers.add(rbAnswer4, gbcPosition);
        buttonGroup.add(rbAnswer4);
        JPanel panelButtons = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelButtons.add(btnAdd, gbcPosition);
        btnAdd.addActionListener(x -> add());
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 0;
        panelButtons.add(btnClear, gbcPosition);
        btnClear.addActionListener(x -> clear());
        gbcPosition.gridx = 2;
        gbcPosition.gridy = 0;
        panelButtons.add(btnClose, gbcPosition);
        btnClose.addActionListener(x -> close());

        JPanel panelMain = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelMain.add(panelCategory, gbcPosition);
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 1;
        panelMain.add(panelQuestion, gbcPosition);
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 2;
        panelMain.add(panelAnswers, gbcPosition);
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 3;
        panelMain.add(panelButtons, gbcPosition);

        add(panelMain);
    }

    private void add()
    {
        String correctAnswer ="";
        if(rbAnswer1.isSelected())
            correctAnswer = tfAnswer1.getText();
        if(rbAnswer2.isSelected())
            correctAnswer = tfAnswer2.getText();
        if(rbAnswer3.isSelected())
            correctAnswer = tfAnswer3.getText();
        if(rbAnswer4.isSelected())
            correctAnswer = tfAnswer4.getText();
        categoryList.forEach(x -> {
            if(x.getName().equals(cbCategory.getSelectedItem().toString()))
                categoryId = x.getId();
            });
        Question question = new Question(0, taQuestion.getText(), tfAnswer1.getText(), tfAnswer2.getText(), tfAnswer3.getText(), tfAnswer4.getText(), correctAnswer, 0, categoryId);
        DatabaseInterfaceImpl.getInstance().insertQuestion(question);
        JOptionPane.showMessageDialog(null, "Question added to database");
        clear();
    }

    private void clear()
    {
        taQuestion.setText("");
        tfAnswer1.setText("");
        tfAnswer2.setText("");
        tfAnswer3.setText("");
        tfAnswer4.setText("");
        buttonGroup.clearSelection();
        cbCategory.setSelectedItem(categoryList.get(0).getName());
    }

    private void close()
    {
        JFrame thisFrame = (JFrame)this.getRootPane().getParent();
        thisFrame.dispose();
    }

    private void addNewCategory(AddQuestionPanel addQuestionPanel)
    {
        JFrame frame = new JFrame("Questions Game");
        AddCategoryPanel panel = new AddCategoryPanel(addQuestionPanel);
        panel.setVisible(true);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
    }

    public void updateCategoryList(Category category)
    {
        this.categoryList.add(category);
        cbCategory.addItem(category.getName());
        cbCategory.setSelectedItem(category.getName());
    }
}
