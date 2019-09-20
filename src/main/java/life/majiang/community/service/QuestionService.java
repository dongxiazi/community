package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.QuestionExMapper;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.QuestionExample;
import life.majiang.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExMapper questionExMapper;
    @Autowired
    private UserMapper userMapper;
    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        Integer totals=(int)questionMapper.countByExample(new QuestionExample());
        Integer   totalPage=totals%size==0?totals/size:totals/size+1;
        if(page<1){
            page=1;
        }
        if (page>totalPage&totalPage!=0){
            page=totalPage;
        }
        Integer offset=(page-1)*size;
        List<Question> list=questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        List<QuestionDTO>  questionDTOList=new ArrayList<>();
        for (Question question : list) {
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalPage,page);
        return paginationDTO;
    }

    public PaginationDTO list(Integer id, Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorNotEqualTo(id);
        Integer totals=(int)questionMapper.countByExample(example);
        Integer   totalPage=totals%size==0?totals/size:totals/size+1;
        if(page<1){
            page=1;
        }
        if (page>totalPage&&totalPage!=0)
        {            page=totalPage;
        }
        Integer offset=(page-1)*size;
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorNotEqualTo(id);
        List<Question> list= questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offset,size));
        List<QuestionDTO>  questionDTOList=new ArrayList<>();
        for (Question question : list) {
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalPage,page);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question= questionMapper.selectByPrimaryKey(id);
        if (null==question){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (null!=question.getId()){
            question.setGmtModified(System.currentTimeMillis());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
          int updated=  questionMapper.updateByExampleSelective(question, example);
          if (updated!=1){
              throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
          }
        }else{
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }
    }

    public void incViewCount(Integer id) {
        Question question=new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExMapper.incViewCount(question);
    }

    /**
     * 此种更改会出现多人并发访问时，更新操作有脏数据，摒弃
     * @param id
     */

//    public void incViewCount(Integer id) {
//      Question question=  questionMapper.selectByPrimaryKey(id);
//        Question updateRecord = new Question();
//        updateRecord.setId(id);
//        updateRecord.setViewCount(question.getViewCount()+1);
//        QuestionExample example = new QuestionExample();
//        example.createCriteria().andIdEqualTo(id);
//        questionMapper.updateByExampleSelective(updateRecord, example);
//    }
}
