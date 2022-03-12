package com.xh.controller;

import com.xh.entity.Inform;
import com.xh.entity.User;
import com.xh.service.loginservice.InformService;
import com.xh.service.loginservice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    //注入
    //登录用户
    @Autowired
    private LoginService loginService;
    //查询公告
    @Autowired
    private InformService informService;

    /**
     * 用户登录
     * @param user
     * @param request
     * @return
     */
    @RequestMapping("/mainJsp.action")
    public String mainJsp(User user, HttpServletRequest request, HttpSession session){
        //查看当前用户是否存在
        if (user.getRols()==-1){
            request.setAttribute("msg","请选择用户类型");
            return "login";
        }

        User u = loginService.findUser(user);

        if (u == null){
            request.setAttribute("msg","用户名或密码错误");
            return "login";
        }
        //存储用户信息
        session.setAttribute("user",u);
        Integer rols = u.getRols();
        if (rols == 1){
            return "/admin/main";
        }
        if (rols == 2){
            return "/worker/main";
        }
        if (rols == 3){
            return "/teacher/main";
        }
        if (rols == 4){
            return "/student/main";
        }
        return "";
    }

    /**
     * 管理员公告，跳的页面可以编辑公告
     * @return
     */
    @RequestMapping("/admininform.action")
    public String admininform(HttpSession session){
        Inform inform = informService.findInform();
        session.setAttribute("body",inform.getIbody());
        return "/public/admininform";
    }

    /**
     * 编辑公告对公告进行编译
     * @param inform
     * @return
     */
    @RequestMapping("/editinform.action")
    public String editinform(Inform inform){
        informService.editInform(inform);
        return "redirect:/admininform.action";

    }

    /**
     * 其他成员跳的页面不能编辑公告
     * @param session
     * @return
     */
    @RequestMapping("/inform.action")
    public String inform(HttpSession session){
        Inform inform = informService.findInform();
        System.out.println(inform);
        session.setAttribute("body",inform.getIbody());
        return "/public/inform";
    }

    /* tuichu.action */
    @RequestMapping("/tuichu.action")
    public String tuichu(HttpSession session){
        session.invalidate();
        return "redirect:/login.jsp";
    }

}
