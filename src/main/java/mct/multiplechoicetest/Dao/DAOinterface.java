package mct.multiplechoicetest.Dao;

import mct.multiplechoicetest.Model.Question;

import java.util.ArrayList;
import java.util.List;

public interface DAOinterface <Question> {
    public int save(Question question);



    public int update(Question question);

    public int delete(Question question);

    public ArrayList<Question> selectAll();

    public Question selectById(Question question);
    public Question selectByQuizId(Question question);
}




