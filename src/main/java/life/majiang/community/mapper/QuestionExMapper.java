package life.majiang.community.mapper;

import life.majiang.community.model.Question;

public interface QuestionExMapper {

    void incViewCount(Question question);
}