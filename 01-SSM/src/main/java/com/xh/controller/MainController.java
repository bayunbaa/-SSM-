package com.xh.controller;

import com.xh.entity.Inform;
import com.xh.entity.User;
import com.xh.service.loginservice.InformService;
import com.xh.service.loginservice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    public String mainJsp(@Valid User user, BindingResult bindingResult, HttpServletRequest request, HttpSession session) {
        // 1. 输入验证
        if (bindingResult.hasErrors()) {
            request.setAttribute("msg", "输入格式不正确");
            return "login";
        }

        // 2. 清理输入数据（防止XSS攻击）
        user.setUname(htmlEscape(user.getUname()));
        user.setUpassword(htmlEscape(user.getUpassword()));

        // 3. 登录逻辑
        User u = loginService.findUser(user);
        if (u == null) {
            request.setAttribute("msg", "用户名或密码错误");
            return "login";
        }

        // 4. 会话管理
        session.invalidate(); // 销毁旧会话
        session = request.getSession(true); // 创建新会话
        session.setAttribute("user", u);

        // 5. 权限控制
        return redirectToMainPage(u.getRols());
    }

    // HTML转义方法（防止XSS攻击）
    private String htmlEscape(String input) {
        if (input == null) {
            return null;
        }
        return input
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    // 根据角色重定向到对应页面
    private String redirectToMainPage(int rols) {
        switch (rols) {
            case 1:
                return "/admin/main";
            case 2:
                return "/worker/main";
            case 3:
                return "/teacher/main";
            default:
                return "";
        }
    }









    /**
     * 管理员公告，跳的页面可以编辑公告
     * @return
     */
    @RequestMapping("/admininform.action")
    public String admininform(HttpSession session){
        Inform inform = informService.findInform();
        session.setAttribute("inform",inform);

        return "/public/admininform";
    }

    /**
     * 编辑公告对公告进行编译
     * @param inform
     * @return
     */
    @RequestMapping("/editinform.action")
    public String editinform(Inform inform, HttpSession session){
        //获取当前登录的用户
        User user = (User) session.getAttribute("user");
        //获取公告，用于后面编译
        Inform inform1 =(Inform) session.getAttribute("inform");
        informService.editInform(inform, user.getUid(), inform1.getIid());
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
        session.setAttribute("inform",inform);
        return "/public/inform";
    }

    /* tuichu.action */
    @RequestMapping("/tuichu.action")
    public String tuichu(HttpSession session){
        session.invalidate();
        return "redirect:/login.jsp";
    }

}
