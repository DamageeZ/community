package com.mapsiz.dev.community.Service;

import com.mapsiz.dev.community.Mapper.QuestionMapper;
import com.mapsiz.dev.community.Model.Question;
import com.mapsiz.dev.community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: DamageeZ
 * @Create: 06-02-2021 15:16
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    public void publishQuestion(String tag, String title, String description, User user) {
        Question question = new Question();
        question.setTitle(title);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setDescription(description);
        question.setCreator(user.getAccountId());
        question.setTag(tag);
        questionMapper.create(question);
    }
}
