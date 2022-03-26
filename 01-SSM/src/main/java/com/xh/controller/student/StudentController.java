package com.xh.controller.student;

import com.github.pagehelper.PageInfo;
import com.xh.entity.User;
import com.xh.entity.student.Repairs;
import com.xh.service.loginservice.LoginService;
import com.xh.service.studentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @Author: xiaohao
 * @Time: 2022/3/26 13:48
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    //注入，用于操作设备报修表
    @Autowired
    private StudentService studentService;


    /**
     * 提交设备报修信息
     * @param repairs
     * @param session
     * @return
     */
    @RequestMapping("/baoxiu.action")
    public String baoxiu(Repairs repairs, HttpSession session){
        //取出当前登录用户
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        //将当前登录用户账号存进去
        repairs.setUid(user.getUid());
        //将报修设备信息存入到数据库中
        int num = studentService.insert(repairs);

        return "student/sheBeiBaoXiualert";
    }

    /**
     * 查看当前登录用户的设备进度
     * @param page  当前页
     * @param session
     * @return
     */
    @RequestMapping("/baoXiuJinDu.action")
    public String baoXiuJinDu(Integer page, HttpSession session, HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        User user = (User) session.getAttribute("user");
        /**
         * 查询当前用户报修的设备信息
         */
        PageInfo<List<Repairs>> info = studentService.findByUid(page, user.getUid());
        request.setAttribute("info",info);
        return "student/sheBeiJinDu";
    }


    /* studentEdit.action */
    @RequestMapping("/studentEdit.action")
    public String studentEdit(User user, HttpSession session){
        User u = (User) session.getAttribute("user");
        user.setUid(u.getUid());
        user.setRols(u.getRols());
        System.out.println(u);
       //更新密码
        int num =  studentService.updateByUid(user);
        return "student/studentEditalert";
    }
}
