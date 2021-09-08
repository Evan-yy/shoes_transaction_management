package com.hyy.Controller;

import com.hyy.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
//
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
//    @ResponseBody   会跳不了视图
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Model model, HttpSession Session){

        //具体业务:
//        if(!StringUtils.isEmpty(username) && "123456".equals(password))
//        {
//            Session.setAttribute("loginUser",username);
//            return "redirect:/index.html";
//        }
//        else{
//            //告诉用户登录失败
//            model.addAttribute("msg","用户名或密码错误!");
//            return "login";
//        }

        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken authenticationToken = new UsernamePasswordToken(username, password);

        try {
            subject.login(authenticationToken);//执行登录方法：如果没有异常就说明OK了
            User currentUser =  (User)subject.getPrincipal(); //拿到User对象
            if((currentUser.getPerms())==null)
            {
                Session.setAttribute("loginUser", username);
                return "u_index";
            } else if((currentUser.getPerms()).equals("user:index"))
            {
                Session.setAttribute("loginUser", username);
                return "redirect:/index";
            }
            return "login";
        } catch (UnknownAccountException e) {//用户名不存在
            model.addAttribute("msg","用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e){//密码不存在
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }

    @RequestMapping("/index")
    public String index(){
        return "Admin/index";
    }

//    @RequestMapping("/welcome.html")
//    public String welcome(){
//        return "Admin/welcome";
//    }


    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized(){
        return "未授权无法访问到此页面";
    }

}
