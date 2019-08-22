package life.majiang.community.controller;

import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PublishController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @GetMapping("/publish")
    public String publish(@ModelAttribute("question") Question question){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(@Valid @ModelAttribute("question")
                                        Question question,
                            BindingResult bindingResult,
                            HttpServletRequest request,
                            Model model){
      if (bindingResult.hasErrors()){
          List<ObjectError>  list=bindingResult.getAllErrors();
          StringBuffer sb=new StringBuffer();
          for (ObjectError error:list){
             sb.append(error);
          }
          model.addAttribute("error",sb.toString());
          return "/publish";
      }
        Cookie[] cookies=request.getCookies();
      User user=null;
      if (!ObjectUtils.isEmpty(cookies)){
          for (Cookie cookie:cookies){
              if (cookie.getName().equals("token")){
                  String token=cookie.getValue();
                  System.out.println(token);
                   user=userMapper.findByToken(token);
                  break;
              }

          }
          if (ObjectUtils.isEmpty(user)){
              model.addAttribute("error","用户未登陆");
              return "/publish";
          }
      }

        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
