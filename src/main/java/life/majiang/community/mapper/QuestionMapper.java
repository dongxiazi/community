package life.majiang.community.mapper;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified," +
            "creator,tag) values(#{title},#{description},#{gmtCreate}," +
            "#{gmtModified},#{creator},#{tag})")
    void create(Question question);
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset,@Param("size") Integer size);
    @Select("select count(1) from question")
    Integer getTotals();
    @Select("select count(1) from question where creator=#{id}")
    Integer getTotalsByUser(@Param("id") Integer id);
    @Select("select * from question where creator=#{id} limit #{offset},#{size}")
    List<Question> listByUser(@Param("id") Integer id, @Param("offset")Integer offset,@Param("size") Integer size);
    @Select("select * from question where id=#{id}")
    Question getById(@Param("id") Integer id);
}
