package com.godlewski.jakub.dao;

import com.godlewski.jakub.classes.Category;
import com.godlewski.jakub.classes.Question;
import com.godlewski.jakub.classes.User;

import java.util.List;

/**
 * Created by jakub on 29.07.2017.
 */
public interface DatabaseInterface {

    void insertUser(User user);
    User getUserByLogin(String login);
    void insertCategory(Category category);
    void insertQuestion(Question question);
    List<Category> selectCategory();
    List<Question> selectQuestion();
    List<Question> selectAllQuestions();
    void updateQuestion(Question question);
    List<User> selectUser();
    void updateUser(User user);
}
