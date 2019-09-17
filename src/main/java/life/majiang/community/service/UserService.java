package life.majiang.community.service;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
   @Autowired
   private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(example);
         if (users.size()!=0) {
             User dbuser=users.get(0);
             user.setId(dbuser.getId());
             BeanUtils.copyProperties(user,dbuser);
             dbuser.setGmtModified(System.currentTimeMillis());
             UserExample userExample = new UserExample();
             userExample.createCriteria().andIdEqualTo(dbuser.getId());
             userMapper.updateByExampleSelective(dbuser, userExample);
            } else {
             user.setGmtModified(System.currentTimeMillis());
             user.setGmtCreate(user.getGmtModified());
             userMapper.insert(user);
            }
    }
}
