package com.godlewski.jakub.gui;

import com.godlewski.jakub.classes.Category;
import com.godlewski.jakub.classes.Question;
import com.godlewski.jakub.classes.User;
import com.godlewski.jakub.dao.DatabaseInterfaceImpl;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by jakub on 31.07.2017.
 */
public class GamePanel extends JPanel{

    private JButton btn5050 = new JButton("<html>50:50</html>");
    private JButton btnAskYourFriend = new JButton("<html>Ask Your<br>Friend</html>");
    private JButton btnAskTheAudience = new JButton("<html>Ask The<br>Audience</html>");
    private JLabel lblQuestion = new JLabel("Question:");
    private JLabel lblQuestionContent = new JLabel("");
    private JLabel lblAnswerA = new JLabel("A:");
    private JButton btnAnswerA = new JButton("");
    private JLabel lblAnswerB = new JLabel("B:");
    private JButton btnAnswerB = new JButton("");
    private JLabel lblAnswerC = new JLabel("C:");
    private JButton btnAnswerC = new JButton("");
    private JLabel lblAnswerD = new JLabel("D:");
    private JButton btnAnswerD = new JButton("");
    private JLabel lblAward = new JLabel("Your award: ");
    private JButton btnResign = new JButton("Resign");
    private java.util.List<JLabel> lblAwardsList;
    private java.util.List<Question> questions;
    private java.util.List<Category> categories;
    private JPanel panelQuestionAnswers;
    int idQuestion = 0;
    int gridY = 0;
    int[] awards = {1000000, 500000, 250000, 125000, 75000, 40000, 20000, 10000, 5000, 2000, 1000, 500, 0};
    int idAward;
    User user;
    MainPanel mainPanel;
    boolean is5050;

    public GamePanel(java.util.List<Question> questions, java.util.List<Category> categories, MainPanel mainPanel, User user)
    {
        super(new GridBagLayout());

        this.questions = questions;
        this.mainPanel = mainPanel;
        this.categories = categories;
        this.user = user;
        btn5050.setPreferredSize(new Dimension(85, 40));
        btnAskTheAudience.setPreferredSize(new Dimension(85, 40));
        btnAskYourFriend.setPreferredSize(new Dimension(85, 40));

        GridBagConstraints gbcPosition = new GridBagConstraints();
        JPanel panelLifeLines = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridx = 0;
        panelLifeLines.add(btn5050, gbcPosition);
        btn5050.addActionListener(x -> lifeline5050());
        gbcPosition.gridx = 0;
        gbcPosition.gridx = 1;
        panelLifeLines.add(btnAskTheAudience, gbcPosition);
        btnAskTheAudience.addActionListener(x -> lifelineAskAudience());
        gbcPosition.gridx = 0;
        gbcPosition.gridx = 2;
        panelLifeLines.add(btnAskYourFriend, gbcPosition);
        btnAskYourFriend.addActionListener(x -> lifelineAskYourFriend());
        panelLifeLines.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK,3, true),
                "LIFELINES",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                new Font("sans-serif", Font.BOLD, 17),
                Color.BLACK
        ));

        JPanel panelQuestion = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelQuestion.add(lblQuestion, gbcPosition);
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 0;
        panelQuestion.add(lblQuestionContent, gbcPosition);

        JPanel panelAnswers = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelAnswers.add(lblAnswerA, gbcPosition);
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 0;
        panelAnswers.add(btnAnswerA, gbcPosition);
        btnAnswerA.addActionListener(x -> answer(btnAnswerA.getText()));
        btnAnswerA.setPreferredSize(new Dimension(85, 40));
        gbcPosition.gridx = 2;
        gbcPosition.gridy = 0;
        panelAnswers.add(lblAnswerB, gbcPosition);
        gbcPosition.gridx = 3;
        gbcPosition.gridy = 0;
        panelAnswers.add(btnAnswerB, gbcPosition);
        btnAnswerB.addActionListener(x -> answer(btnAnswerB.getText()));
        btnAnswerB.setPreferredSize(new Dimension(85, 40));
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 1;
        panelAnswers.add(lblAnswerC, gbcPosition);
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 1;
        panelAnswers.add(btnAnswerC, gbcPosition);
        btnAnswerC.addActionListener(x -> answer(btnAnswerC.getText()));
        btnAnswerC.setPreferredSize(new Dimension(85, 40));
        gbcPosition.gridx = 2;
        gbcPosition.gridy = 1;
        panelAnswers.add(lblAnswerD, gbcPosition);
        gbcPosition.gridx = 3;
        gbcPosition.gridy = 1;
        panelAnswers.add(btnAnswerD, gbcPosition);
        btnAnswerD.addActionListener(x -> answer(btnAnswerD.getText()));
        btnAnswerD.setPreferredSize(new Dimension(85, 40));


        this.panelQuestionAnswers = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelQuestionAnswers.add(panelQuestion, gbcPosition);
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 1;
        panelQuestionAnswers.add(panelAnswers, gbcPosition);
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 2;
        panelQuestionAnswers.add(btnResign, gbcPosition);
        btnResign.addActionListener(x -> resign());

        JPanel panelAwards = new JPanel(new GridBagLayout());
        lblAwardsList = new ArrayList<>();
        for(int i = 0; i<awards.length; i++)
        {
            gbcPosition.gridx = 0;
            gbcPosition.gridy = gridY;
            lblAwardsList.add(new JLabel(""));
            panelAwards.add(lblAwardsList.get(i), gbcPosition);
            gridY++;
        }
        panelAwards.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK,3, true),
                "YOUR AWARD",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                new Font("sans-serif", Font.BOLD, 17),
                Color.BLACK
        ));

        JPanel panelUserGameAndLifeLines = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelUserGameAndLifeLines.add(panelLifeLines, gbcPosition);
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 1;
        panelUserGameAndLifeLines.add(panelQuestionAnswers, gbcPosition);

        JPanel panelMain = new JPanel(new GridBagLayout());
        gbcPosition.gridx = 0;
        gbcPosition.gridy = 0;
        panelMain.add(panelUserGameAndLifeLines, gbcPosition);
        gbcPosition.gridx = 1;
        gbcPosition.gridy = 0;
        panelMain.add(panelAwards, gbcPosition);

        add(panelMain);

        newGame();
    }

    private void fillFileds()
    {
        Question question = questions.get(idQuestion);
        Category category = null;
        for(Category c: categories) {
            if(c.getId() == question.getCategoryId())
                category = c;
        }
        if(category == null) {
            category = new Category(0, "Inne");
        }
        panelQuestionAnswers.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK,3, true),
                category.getName().toUpperCase(),
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                new Font("sans-serif", Font.BOLD, 17),
                Color.BLACK
        ));
        lblQuestion.setText(question.getQuestion());
        List<String> answers = new ArrayList<>();
        answers.add(question.getAnswer1());
        answers.add(question.getAnswer2());
        answers.add(question.getAnswer3());
        answers.add(question.getAnswer4());
        randomQuestions(btnAnswerA, answers);
        randomQuestions(btnAnswerB, answers);
        randomQuestions(btnAnswerC, answers);
        randomQuestions(btnAnswerD, answers);
    }

    private void randomQuestions(JButton btn, java.util.List<String> answers)
    {
        int questionIndex = (int)(Math.random()*answers.size());
        String question = answers.get(questionIndex);
        answers.remove(questionIndex);
        btn.setText(question);
    }

    private void answer(String answer)
    {
        Question question = questions.get(idQuestion);
        String correctAnswer = question.getCorrectAnswer();
        if(answer.equals(correctAnswer))
        {
            JOptionPane.showMessageDialog(null,"Good Answer");

            idQuestion++;
            question.setDifficulty(question.getDifficulty() - 10);
            DatabaseInterfaceImpl.getInstance().updateQuestion(question);
            idAward--;
            makeYourAwardRed();
            if(idQuestion<12)
                fillFileds();
            else
                endOfGame();
            is5050 = false;
        }
        else
        {
            question.setDifficulty(question.getDifficulty() + 10);
            DatabaseInterfaceImpl.getInstance().updateQuestion(question);
            JOptionPane.showMessageDialog(null, "<html>Bad Answer! Good answer is '"+correctAnswer+"'</html>");
            endOfGame();
        }

        if(!is5050)
        {
            btnAnswerA.setEnabled(true);
            btnAnswerB.setEnabled(true);
            btnAnswerC.setEnabled(true);
            btnAnswerD.setEnabled(true);
        }
    }

    private void endOfGame()
    {
        int yourAward;
        if(idAward == 1)
        {
            yourAward = awards[0];
            JOptionPane.showMessageDialog(null, "<html>Congratulation! You won main price! <br>"+yourAward+"$</html>");
        }
        else if(idAward <= 6)
        {
            yourAward = awards[5];
            JOptionPane.showMessageDialog(null, "<html>Game over! You won <br>"+yourAward+"$</html>");
        }
        else if(idAward <= 11)
        {
            yourAward = awards[10];
            JOptionPane.showMessageDialog(null, "<html>Game over! You won <br>"+yourAward+"$</html>");
        }
        else
        {
            yourAward = awards[awards.length-1];
            JOptionPane.showMessageDialog(null, "<html>Game over! You won <br>"+yourAward+"$</html>");
        }

        user.setPoints(user.getPoints()+yourAward);
        DatabaseInterfaceImpl.getInstance().updateUser(user);
        mainPanel.afterGame();

        CardLayout cl = (CardLayout)mainPanel.getLayout();
        cl.show(mainPanel, "NEWGAME");

    }

    private void makeYourAwardRed()
    {
        int i=0;
        for(JLabel x: lblAwardsList)
        {
            if(i!=idAward-1)
                x.setText(String.valueOf(awards[i]));
            else
                x.setText("<html><p style='color:red'>"+String.valueOf(awards[i])+"</p></html>");
            i++;
        }
    }

    private void resign()
    {
        int yourAward = awards[idAward-1];
        Question question = questions.get(idQuestion);
        question.setDifficulty(question.getDifficulty()+5);
        DatabaseInterfaceImpl.getInstance().updateQuestion(question);
        JOptionPane.showMessageDialog(null, "<html>Game over! You won <br>"+yourAward+"$</html>");
        user.setPoints(user.getPoints()+yourAward);
        DatabaseInterfaceImpl.getInstance().updateUser(user);
        mainPanel.afterGame();
        CardLayout cl = (CardLayout)mainPanel.getLayout();
        cl.show(mainPanel, "NEWGAME");
    }

    public void newGame()
    {
        is5050 = false;
        this.questions = DatabaseInterfaceImpl.getInstance().selectQuestion();
        questions.sort((x1,x2) -> Integer.compare(x1.getDifficulty(), x2.getDifficulty()));
        idQuestion = 0;
        idAward = awards.length;
        fillFileds();
        makeYourAwardRed();
        btn5050.setEnabled(true);
        btnAskYourFriend.setEnabled(true);
        btnAskTheAudience.setEnabled(true);
        btnAnswerA.setEnabled(true);
        btnAnswerB.setEnabled(true);
        btnAnswerC.setEnabled(true);
        btnAnswerD.setEnabled(true);
    }

    private void lifeline5050()
    {
        is5050 = true;
        Question question = questions.get(idQuestion);
        String correct = question.getCorrectAnswer();
        List<JButton> btnList = new ArrayList<>();
        if(!btnAnswerA.getText().equals(correct))
            btnList.add(btnAnswerA);
        if(!btnAnswerB.getText().equals(correct))
            btnList.add(btnAnswerB);
        if(!btnAnswerC.getText().equals(correct))
            btnList.add(btnAnswerC);
        if(!btnAnswerD.getText().equals(correct))
            btnList.add(btnAnswerD);
        int btn1, btn2;
        btn1 = (int)(Math.random()*btnList.size());
        btnList.get(btn1).setEnabled(false);
        btnList.remove(btn1);

        btn2 = (int)(Math.random()*btnList.size());
        btnList.get(btn2).setEnabled(false);
        btnList.remove(btn2);

        question.setDifficulty(question.getDifficulty()+5);
        DatabaseInterfaceImpl.getInstance().updateQuestion(question);

        btn5050.setEnabled(false);
    }

    private void lifelineAskYourFriend()
    {
        Question question = questions.get(idQuestion);
        List<Question> questions = DatabaseInterfaceImpl.getInstance().selectAllQuestions();

        long minDifficulty = findMinDifficulty(questions);
        long maxDifficulty = findMaxDifficulty(questions);
        long val;
        long questionDifficulty = question.getDifficulty();
        int random = (int)(Math.random()*101);
        int random2;
        String friendResponse ="";
        String correct = getCorrect(question);
        String incorrect ="";

        if(!is5050)
        {
            do {
                random2 = (int)(Math.random()*4);
                if(random2==0)
                    incorrect = "A";
                else if(random2==1)
                    incorrect = "B";
                else if(random2==2)
                    incorrect = "C";
                else if(random2==3)
                    incorrect = "D";

            }while(incorrect==correct);
        }
        else
        {
            incorrect = getIncorrect(question);
        }

        if(minDifficulty!=maxDifficulty)
        {
            val = (maxDifficulty - minDifficulty)/10;

            if(questionDifficulty<=minDifficulty+val)
            {
                friendResponse = "I'm sure that correct is "+correct;
            }
            else if(questionDifficulty<=minDifficulty+2*val)
            {
                if(random<=5)
                    friendResponse = "I'm not sure, but I think it will be "+incorrect;
                else if(random<=10)
                    friendResponse = "I'm not sure, but I think it will be "+correct;
                else
                    friendResponse = "I'm sure that correct is "+correct;
            }
            else if(questionDifficulty<=minDifficulty+3*val)
            {
                if(random<=5)
                    friendResponse = "I don't know. Sorry!";
                else if(random<=15)
                    friendResponse = "I'm not sure, but I think it will be "+incorrect;
                else if(random<=30)
                    friendResponse = "I'm not sure, but I think it will be "+correct;
                else
                    friendResponse = "I'm sure that correct is "+correct;
            }
            else if(questionDifficulty<=minDifficulty+4*val)
            {
                if(random<=5)
                    friendResponse = "I don't know. Sorry!";
                else if(random<=20)
                    friendResponse = "I'm not sure, but I think it will be "+incorrect;
                else if(random<=50)
                    friendResponse = "I'm not sure, but I think it will be "+correct;
                else
                    friendResponse = "I'm sure that correct is "+correct;
            }
            else if(questionDifficulty<=minDifficulty+5*val)
            {
                if(random<=20)
                    friendResponse = "I don't know. Sorry!";
                else if(random<=45)
                    friendResponse = "I'm not sure, but I think it will be "+incorrect;
                else if(random<=70)
                    friendResponse = "I'm not sure, but I think it will be "+correct;
                else
                    friendResponse = "I'm sure that correct is "+correct;
            }
            else if(questionDifficulty<=minDifficulty+6*val)
            {
                if(random<=30)
                    friendResponse = "I don't know. Sorry!";
                else if(random<=55)
                    friendResponse = "I'm not sure, but I think it will be "+incorrect;
                else if(random<=80)
                    friendResponse = "I'm not sure, but I think it will be "+correct;
                else
                    friendResponse = "I'm sure that correct is "+correct;
            }
            else if(questionDifficulty<=minDifficulty+7*val)
            {
                if(random<=50)
                    friendResponse = "I don't know. Sorry!";
                else if(random<=80)
                    friendResponse = "I'm not sure, but I think it will be "+incorrect;
                else if(random<=95)
                    friendResponse = "I'm not sure, but I think it will be "+correct;
                else
                    friendResponse = "I'm sure that correct is "+correct;
            }
            else if(questionDifficulty<=minDifficulty+8*val)
            {
                if(random<=70)
                    friendResponse = "I don't know. Sorry!";
                else if(random<=85)
                    friendResponse = "I'm not sure, but I think it will be "+incorrect;
                else if(random<=95)
                    friendResponse = "I'm not sure, but I think it will be "+correct;
                else
                    friendResponse = "I'm sure that correct is "+correct;
            }
            else if(questionDifficulty<=minDifficulty+9*val)
            {
                if(random<=90)
                    friendResponse = "I don't know. Sorry!";
                else if(random<=95)
                    friendResponse = "I'm not sure, but I think it will be "+incorrect;
                else
                    friendResponse = "I'm not sure, but I think it will be "+correct;
            }
            else
            {
                friendResponse = "I don't know. Sorry!";
            }
        }
        else
        {
            if(random<=25)
                friendResponse = "I don't know. Sorry!";
            else if(random<=50)
                friendResponse = "I'm not sure, but I think it will be "+incorrect;
            else if(random<=75)
                friendResponse = "I'm not sure, but I think it will be "+correct;
            else if(random<=100)
                friendResponse = "I'm sure that it will be "+correct;
        }

        JOptionPane.showMessageDialog(null, friendResponse);
        question.setDifficulty(question.getDifficulty()+5);
        DatabaseInterfaceImpl.getInstance().updateQuestion(question);
        btnAskYourFriend.setEnabled(false);
    }

    private void lifelineAskAudience()
    {
        Question question = questions.get(idQuestion);
        List<Question> questions = DatabaseInterfaceImpl.getInstance().selectAllQuestions();
        long minDifficulty = findMinDifficulty(questions);
        long maxDifficulty = findMaxDifficulty(questions);
        StringBuilder message = new StringBuilder("");
        String correct = getCorrect(question);
        long val= (maxDifficulty - minDifficulty)/10;
        long questionDifficulty = question.getDifficulty();
        int correctPecent;

        if(!is5050)
        {
            int[] incorrectPercents = new int[3];

            if(questionDifficulty<=minDifficulty+val)
            {
                correctPecent = (int)(Math.random()*11)+75;

            }
            else if(questionDifficulty<=minDifficulty+2*val)
            {
                correctPecent = (int)(Math.random()*11)+70;
            }
            else if(questionDifficulty<=minDifficulty+3*val)
            {
                correctPecent = (int)(Math.random()*11)+60;
            }
            else if(questionDifficulty<=minDifficulty+4*val)
            {
                correctPecent = (int)(Math.random()*11)+50;
            }
            else if(questionDifficulty<=minDifficulty+5*val)
            {
                correctPecent = (int)(Math.random()*11)+40;
            }
            else if(questionDifficulty<=minDifficulty+6*val)
            {
                correctPecent = (int)(Math.random()*11)+30;
            }
            else if(questionDifficulty<=minDifficulty+7*val)
            {
                correctPecent = (int)(Math.random()*21)+25;
            }
            else if(questionDifficulty<=minDifficulty+8*val)
            {
                correctPecent = (int)(Math.random()*21)+25;
            }
            else if(questionDifficulty<=minDifficulty+9*val)
            {
                correctPecent = (int)(Math.random()*21)+30;
            }
            else
            {
                correctPecent = (int)(Math.random()*21)+20;
            }

            do {
                for(int i=0; i<incorrectPercents.length; i++)
                {
                    incorrectPercents[i] = (int)(Math.random()*correctPecent);
                }
            }
            while(correctPecent+incorrectPercents[0]+incorrectPercents[1]+incorrectPercents[2]!=100);

            int i=0;
            if("A".equals(correct))
                message.append("A: "+correctPecent+"%<br>");
            else if(!"A".equals(correct))
            {
                message.append("A: "+incorrectPercents[i]+"%<br>");
                i++;
            }

            if("B".equals(correct))
                message.append("B: "+correctPecent+"%<br>");
            else if(!"B".equals(correct))
            {
                message.append("B: "+incorrectPercents[i]+"%<br>");
                i++;
            }

            if("C".equals(correct))
                message.append("C: "+correctPecent+"%<br>");
            else if(!"C".equals(correct))
            {
                message.append("C: "+incorrectPercents[i]+"%<br>");
                i++;
            }

            if("D".equals(correct))
                message.append("D: "+correctPecent+"%<br>");
            else if(!"D".equals(correct))
            {
                message.append("D: "+incorrectPercents[i]+"%<br>");
                i++;
            }
        }
        else
        {
            int incorrectPercent;
            String incorrect = getIncorrect(question);
            if(questionDifficulty<=minDifficulty+val)
            {
                correctPecent = (int)(Math.random()*11)+75;
                incorrectPercent = 100 - correctPecent;
            }
            else if(questionDifficulty<=minDifficulty+2*val)
            {
                correctPecent = (int)(Math.random()*11)+70;
                incorrectPercent = 100 - correctPecent;
            }
            else if(questionDifficulty<=minDifficulty+3*val)
            {
                correctPecent = (int)(Math.random()*11)+65;
                incorrectPercent = 100 - correctPecent;
            }
            else if(questionDifficulty<=minDifficulty+4*val)
            {
                correctPecent = (int)(Math.random()*11)+60;
                incorrectPercent = 100 - correctPecent;
            }
            else if(questionDifficulty<=minDifficulty+5*val)
            {
                correctPecent = (int)(Math.random()*11)+50;
                incorrectPercent = 100 - correctPecent;
            }
            else if(questionDifficulty<=minDifficulty+6*val)
            {
                correctPecent = (int)(Math.random()*16)+45;
                incorrectPercent = 100 - correctPecent;
            }
            else if(questionDifficulty<=minDifficulty+7*val)
            {
                correctPecent = (int)(Math.random()*11)+45;
                incorrectPercent = 100 - correctPecent;
            }
            else if(questionDifficulty<=minDifficulty+8*val)
            {
                correctPecent = (int)(Math.random()*16)+40;
                incorrectPercent = 100 - correctPecent;
            }
            else if(questionDifficulty<=minDifficulty+9*val)
            {
                correctPecent = (int)(Math.random()*16)+40;
                incorrectPercent = 100 - correctPecent;
            }
            else
            {
                correctPecent = (int)(Math.random()*11)+40;
                incorrectPercent = 100 - correctPecent;
            }

            if("A".equals(correct))
                message.append("A: "+correctPecent+"%<br>");
            else if ("A".equals(incorrect))
                message.append("A: "+incorrectPercent+"%<br>");

            if("B".equals(correct))
                message.append("B: "+correctPecent+"%<br>");
            else if ("B".equals(incorrect))
                message.append("B: "+incorrectPercent+"%<br>");

            if("C".equals(correct))
                message.append("C: "+correctPecent+"%<br>");
            else if ("C".equals(incorrect))
                message.append("C: "+incorrectPercent+"%<br>");

            if("D".equals(correct))
                message.append("D: "+correctPecent+"%<br>");
            else if ("D".equals(incorrect))
                message.append("D: "+incorrectPercent+"%<br>");

        }

        message = new StringBuilder("<html>Audience response:<br>"+message.toString()+"</html>");
        JOptionPane.showMessageDialog(null, message.toString());
        question.setDifficulty(question.getDifficulty()+5);
        DatabaseInterfaceImpl.getInstance().updateQuestion(question);
        btnAskTheAudience.setEnabled(false);
    }

    private long findMinDifficulty(List<Question> questions)
    {
        long minDifficulty = questions.get(0).getDifficulty();

        for(Question q: questions)
        {
            if(q.getDifficulty()<minDifficulty)
                minDifficulty = q.getDifficulty();
        }

        return minDifficulty;
    }

    private long findMaxDifficulty(List<Question> questions)
    {

        long minDifficulty = questions.get(0).getDifficulty();

        for(Question q: questions)
        {
            if(q.getDifficulty()>minDifficulty)
                minDifficulty = q.getDifficulty();
        }

        return minDifficulty;
    }

    private String getCorrect(Question question)
    {
        String correct ="";
        if(btnAnswerA.getText().equals(question.getCorrectAnswer()))
            correct = "A";
        else if(btnAnswerB.getText().equals(question.getCorrectAnswer()))
            correct = "B";
        else if(btnAnswerC.getText().equals(question.getCorrectAnswer()))
            correct = "C";
        else if(btnAnswerD.getText().equals(question.getCorrectAnswer()))
            correct = "D";
        return correct;
    }

    private String getIncorrect(Question question)
    {
        String incorrect = "";

        if(btnAnswerA.isEnabled()&&!btnAnswerA.getText().equals(question.getCorrectAnswer()))
        {
            incorrect ="A";
        }
        else if(btnAnswerB.isEnabled()&&!btnAnswerB.getText().equals(question.getCorrectAnswer()))
        {
            incorrect ="B";
        }
        else if(btnAnswerC.isEnabled()&&!btnAnswerC.getText().equals(question.getCorrectAnswer()))
        {
            incorrect ="C";
        }
        else if(btnAnswerD.isEnabled()&&!btnAnswerD.getText().equals(question.getCorrectAnswer()))
        {
            incorrect ="D";
        }

        return incorrect;
    }
}
