package com.godlewski.jakub.dao;

import com.godlewski.jakub.classes.Category;
import com.godlewski.jakub.classes.Question;
import com.godlewski.jakub.classes.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 29.07.2017.
 */
public class DatabaseInterfaceImpl implements DatabaseInterface {

    private final String DRIVER = "org.sqlite.JDBC";
    private final String DB_NAME = "jdbc:sqlite:QuestionGame.db";
    private Connection connection;
    private Statement statement;

    private DatabaseInterfaceImpl()
    {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_NAME);
            statement = connection.createStatement();
            createTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    private static DatabaseInterfaceImpl databaseInterface = null;

    public static DatabaseInterfaceImpl getInstance()
    {
        if (databaseInterface == null)
        {
            databaseInterface = new DatabaseInterfaceImpl();
        }
        return databaseInterface;
    }

    private void createTable()
    {
        String queryUser = "create table if not exists User" +
                "(" +
                "id integer primary key autoincrement," +
                "login varchar(50) not null," +
                "password varchar(50) not null," +
                "name varchar(50) not null," +
                "points int not null"+
                ");";

        String queryCategory = "create table if not exists Category" +
                "(" +
                "id integer primary key autoincrement," +
                "name varchar(50) not null" +
                ");";

        String queryQuestion = "create table if not exists Question" +
                "(" +
                "id integer primary key autoincrement," +
                "question varchar(100) not null," +
                "answer1 varchar(50) not null," +
                "answer2 varchar(50) not null," +
                "answer3 varchar(50) not null," +
                "answer4 varchar(50) not null," +
                "correct varchar(50) not null," +
                "difficulty int not null," +
                "idCategory int not null," +
                "foreign key (idCategory) references Category(id) on delete cascade on update cascade" +
                ");";

        try {
            statement.execute(queryUser);
            statement.execute(queryCategory);
            statement.execute(queryQuestion);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void insertUser(User user) {

        try {
            String query = "insert into User (login, password, name, points) " +
                    "values (?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,user.getLogin());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getName());
            ps.setInt(4,user.getPoints());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByLogin(String login) {

        try {
            String query = "select * from User where login = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,login);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertCategory(Category category) {

        try {
            String query = "insert into Category (name) " +
                    "values (?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, category.getName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertQuestion(Question question) {
        try
        {
            String query = "insert into Question (question, answer1, answer2, answer3, answer4, correct, difficulty, idCategory)" +
                    "values (?,?,?,?,?,?,?,?); ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, question.getQuestion());
            ps.setString(2, question.getAnswer1());
            ps.setString(3, question.getAnswer2());
            ps.setString(4, question.getAnswer3());
            ps.setString(5, question.getAnswer4());
            ps.setString(6, question.getCorrectAnswer());
            ps.setInt(7, question.getDifficulty());
            ps.setInt(8, question.getCategoryId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Category> selectCategory() {

        List<Category> categories = new ArrayList<>();
        try {
            String query = "select * from Category;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                categories.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public List<Question> selectQuestion() {
        List<Question> questions = new ArrayList<>();
        try {
            String query = "select * from Question order by random() limit 12;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                questions.add(new Question(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    @Override
    public List<Question> selectAllQuestions() {

        List<Question> questions = new ArrayList<>();
        String query = "select * from Question";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                questions.add(new Question(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public void updateQuestion(Question question)
    {
        String query = "update Question set question =?, answer1=?, answer2=?, answer3=?, answer4=?, correct=?, difficulty=?, idCategory=? where id=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, question.getQuestion());
            ps.setString(2, question.getAnswer1());
            ps.setString(3, question.getAnswer2());
            ps.setString(4, question.getAnswer3());
            ps.setString(5, question.getAnswer4());
            ps.setString(6, question.getCorrectAnswer());
            ps.setInt(7, question.getDifficulty());
            ps.setInt(8, question.getCategoryId());
            ps.setInt(9, question.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> selectUser() {

        List<User> users = new ArrayList<>();
        String query = "select * from User order by points desc";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                users.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void updateUser(User user) {
        String query = "update User set login =?, password=?, name=?, points=? where id=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setInt(4, user.getPoints());
            ps.setLong(5, user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
