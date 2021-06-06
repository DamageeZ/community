package com.mapsiz.dev.community.Mapper;

import com.mapsiz.dev.community.Model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: DamageeZ
 * @Create: 06-02-2021 13:54
 */
@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO questions (title,description,gmtCreate,gmtModified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("SELECT * FROM questions")
    List<Question> list();
}
