package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
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
    private UserMapper userMapper;
    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        Integer totals=questionMapper.getTotals();
      Integer   totalPage=totals%size==0?totals/size:totals/size+1;
        if(page<1){
            page=1;
        }
        if (page>totalPage&totalPage!=0){
            page=totalPage;
        }
        Integer offset=(page-1)*size;
        List<Question> list=questionMapper.list(offset,size);
        List<QuestionDTO>  questionDTOList=new ArrayList<>();
        for (Question question : list) {
            User user=userMapper.findById(question.getCreator());
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
        Integer totals=questionMapper.getTotalsByUser(id);
        Integer   totalPage=totals%size==0?totals/size:totals/size+1;
        if(page<1){
            page=1;
        }
        if (page>totalPage&&totalPage!=0)
        {            page=totalPage;
        }
        Integer offset=(page-1)*size;
        List<Question> list=questionMapper.listByUser(id,offset,size);
        List<QuestionDTO>  questionDTOList=new ArrayList<>();
        for (Question question : list) {
            User user=userMapper.findById(question.getCreator());
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
        Question question=questionMapper.getById(id);
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }
}
