package com.xh.controller.teacher;

import com.github.pagehelper.PageInfo;
import com.xh.entity.User;
import com.xh.entity.student.Repairs;
import com.xh.entity.teacher.Addfacility;
import com.xh.service.studentService.StudentService;
import com.xh.service.teacherService.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: 教室提交的
 * @Author: xiaohao
 * @Time: 2022/3/26 19:15
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    //因为学生有了老师的部分功能，我们这里直接调用学生的功能
    @Autowired
    private StudentService studentService;
    //操作老师表
    @Autowired
    private TeacherService teacherService;


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

        return "teacher/sheBeiBaoXiualert";
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
        return "teacher/sheBeiJinDu";
    }


    /**
     * 添加设备
     * @param addfacility
     * @param session
     * @return
     */
    @RequestMapping("/addSheBei.action")
    public String addSheBei(Addfacility addfacility, HttpSession session){
        if (addfacility.getFname()==null||addfacility.getFname()==""||addfacility.getLocation()==null||addfacility.getLocation()==""||addfacility.getTest()==null||addfacility.getTest()==""){
            return "teacher/addSheBeialertNotNull";
        }
        //将当前用户登录的Id，存进去
        User user = (User) session.getAttribute("user");
        addfacility.setUid(user.getUid()+"");
        int num = teacherService.insert(addfacility);

        return "teacher/addSheBeialert";
    }



    /* addSheBeiJindu.action */

    /**
     * 添加设备的进度
     * @param page
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/addSheBeiJindu.action")
    public String addSheBeiJindu(Integer page,HttpSession session, HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        User user =(User) session.getAttribute("user");
        PageInfo<List<Addfacility>> info = teacherService.findByUid(page, user.getUid());
        request.setAttribute("info",info);
        return "teacher/addSheBeiJinDu";
    }




    /**
     * 修改个人资料
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/teacherEdit.action")
    public String teacherEdit(User user, HttpSession session){
        User u = (User) session.getAttribute("user");
        user.setUid(u.getUid());
        user.setRols(u.getRols());
        System.out.println(u);
        //更新密码
        int num =  studentService.updateByUid(user);
        return "teacher/teacherEditalert";
    }
}
