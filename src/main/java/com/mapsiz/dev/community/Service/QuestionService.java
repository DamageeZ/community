package com.mapsiz.dev.community.Service;

import com.mapsiz.dev.community.DTO.QuestionDTO;
import com.mapsiz.dev.community.Mapper.QuestionMapper;
import com.mapsiz.dev.community.Mapper.UserMapper;
import com.mapsiz.dev.community.Model.Question;
import com.mapsiz.dev.community.Model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: DamageeZ
 * @Create: 06-02-2021 15:16
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

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


    public List<QuestionDTO> listQuestions() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findByAccountId(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            if (questionDTO.getDescription().length()>=70) {
                questionDTO.setDescription(question.getDescription().substring(0,70)+"...");
            }
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
