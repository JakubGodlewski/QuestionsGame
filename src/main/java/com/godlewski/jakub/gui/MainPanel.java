package com.godlewski.jakub.gui;

import com.godlewski.jakub.classes.Category;
import com.godlewski.jakub.classes.Question;
import com.godlewski.jakub.classes.User;
import com.godlewski.jakub.dao.DatabaseInterfaceImpl;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jakub on 29.07.2017.
 */
public class MainPanel extends JPanel{

    private GamePanel gamePanel;
    private StartNewGamePanel startNewGamePanel;
    private UsersRankingPanel usersRankingPanel;
    private java.util.List<Question> questions;
    private java.util.List<Category> categories;

    public MainPanel(User user) {
        super(new CardLayout());

        this.questions = DatabaseInterfaceImpl.getInstance().selectQuestion();
        this.categories = DatabaseInterfaceImpl.getInstance().selectCategory();
        questions.sort((x1,x2) -> Integer.compare(x1.getDifficulty(), x2.getDifficulty()));
        gamePanel = new GamePanel(questions, categories, this, user);
        startNewGamePanel = new StartNewGamePanel();
        usersRankingPanel = new UsersRankingPanel();

        add(startNewGamePanel, "NEWGAME");
        add(gamePanel, "GAME");
        add(usersRankingPanel, "USERS_RANKING");
    }

    public JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuGame = new JMenu("Game");
        JMenu menuRanking = new JMenu("Ranking");
        JMenu menuHelp = new JMenu("Help");

        JMenuItem menuItemNewGame = new JMenuItem("New game");
        menuItemNewGame.addActionListener(x -> newGame());
        JMenuItem menuItemAddNewQuestion = new JMenuItem("Add question");
        menuGame.add(menuItemNewGame);
        menuGame.addSeparator();
        menuGame.add(menuItemAddNewQuestion);
        menuItemAddNewQuestion.addActionListener(x ->showAddQuestionWindow());

        JMenuItem menuItemUsersRanking = new JMenuItem("Users ranking");
        menuItemUsersRanking.addActionListener(x -> showUsersRanking());
        menuRanking.add(menuItemUsersRanking);

        JMenuItem menuItemAbout = new JMenuItem("About");
        menuItemAbout.addActionListener(x -> about());
        JMenuItem menuItemLogout = new JMenuItem("Log out");
        menuItemLogout.addActionListener(x -> logout());
        JMenuItem menuItemClose = new JMenuItem("Close");
        menuItemClose.addActionListener(x -> close());
        menuHelp.add(menuItemAbout);
        menuHelp.add(menuItemLogout);
        menuHelp.add(menuItemClose);

        menuBar.add(menuGame);
        menuBar.add(menuRanking);
        menuBar.add(menuHelp);

        return menuBar;
    }

    private void showAddQuestionWindow()
    {
        JFrame frame = new JFrame("Add New Question");
        AddQuestionPanel panel = new AddQuestionPanel();
        panel.setVisible(true);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
    }

    private void about()
    {
        JFrame frame = new JFrame("About");
        AboutPanel panel = new AboutPanel();
        panel.setVisible(true);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
    }

    private void logout()
    {
        JFrame frame = new JFrame("Login");
        LoginPanel panel = new LoginPanel();
        panel.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

    private void newGame()
    {
        gamePanel.newGame();
        CardLayout cl = (CardLayout)this.getLayout();
        cl.show(this, "GAME");
    }

    private void showUsersRanking()
    {
        CardLayout cl = (CardLayout)this.getLayout();
        cl.show(this, "USERS_RANKING");
    }

    public void afterGame()
    {
        usersRankingPanel.updateTable();
    }
}
