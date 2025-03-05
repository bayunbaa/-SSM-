package com.xh.controller.teacher;

import com.github.pagehelper.PageInfo;
import com.xh.entity.User;
import com.xh.entity.student.Repairs;
import com.xh.entity.teacher.Addfacility;
import com.xh.service.studentService.StudentService;
import com.xh.service.teacherService.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @PostMapping("/baoxiu.action")
    public String baoxiu(@ModelAttribute Repairs repairs, HttpSession session, Model model) {
        // 取出当前登录用户
        User user = (User) session.getAttribute("user");
        if (user != null) {
            // 将当前登录用户账号存进去
            repairs.setUid(user.getUid());
            // 将报修设备信息存入到数据库中
            int num = studentService.insert(repairs);
            if (num > 0) {
                model.addAttribute("message", "报修成功");
            } else {
                model.addAttribute("message", "报修失败");
            }
        } else {
            model.addAttribute("message", "用户未登录");
        }
        return "teacher/sheBeiBaoXiu"; // 注意：确保视图解析器能够正确解析这个路径
    }

    /**
     * 查看当前登录用户的设备进度
     * @param page  当前页
     * @param session
     * @return
     */
    @RequestMapping("/baoXiuJinDu.action")
    public String baoXiuJinDu(Integer page, HttpSession session, HttpServletRequest request){
        page = page == null ? 1 : page;
        User user = (User) session.getAttribute("user");
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
    public String addSheBei(Addfacility addfacility, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("message", "用户未登录");
            return "login"; // 假设登录页面的路径是 /login
        }
        addfacility.setUid(user.getUid() + "");
        int num = teacherService.insert(addfacility);

        if (num > 0) {
            model.addAttribute("message", "设备申请提交成功");
        } else {
            model.addAttribute("message", "设备申请提交失败");
        }

        return "teacher/addSheBei";
    }



    /* addSheBeiJindu.action */

    /**
     * 查看添加设备的进度
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
        PageInfo<Addfacility> info = teacherService.findByUid(page, user.getUid());
        request.setAttribute("info",info);
        return "teacher/addSheBeiJinDu";
    }


    /**
     * 申请报废设备
     * @param addfacility
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/delSheBei.action")
    public String delSheBei(Addfacility addfacility, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("message", "用户未登录");
            return "login"; // 假设登录页面的路径是 /login
        }
        addfacility.setUid(user.getUid() + "");
        int num = teacherService.insertDeleteFacility(addfacility);

        if (num > 0) {
            model.addAttribute("message", "设备申请提交成功");
        } else {
            model.addAttribute("message", "设备申请提交失败");
        }

        return "teacher/addSheBei";
    }







    @RequestMapping("/delete.action")
    public String delete(Integer id, HttpSession session, HttpServletRequest request) {
        teacherService.deleteById(id);
        return "redirect:/admin/findAddfacilityByPlan2.action"; // 重定向到另一个 URL
    }

    @RequestMapping("/findDelete.action")
    public String findDelete(Integer page, HttpSession session, HttpServletRequest request){
        page = page == null ? 1 : page;
        User user = (User) session.getAttribute("user");
        PageInfo<List<Addfacility>> info = studentService.findByUid2(page, user.getUid());
        request.setAttribute("info",info);
        return "teacher/findDelete";
    }




    /**
     * 修改个人资料
     * @param user
     * @param session
     * @return
     */
  @RequestMapping("/teacherEdit.action")
public String teacherEdit(User user, HttpSession session, RedirectAttributes redirectAttributes) {
    // 检查会话中的用户属性
    User u = (User) session.getAttribute("user");
    if (u == null) {
        redirectAttributes.addFlashAttribute("success", "false");
        redirectAttributes.addFlashAttribute("message", "用户未登录，请先登录。");
        return "redirect:/login"; // 假设登录页面为 /login
    }
    // 设置用户的 UID 和角色
    user.setUid(u.getUid());
    user.setRols(u.getRols());
    // 调用服务更新密码
    int num = studentService.updateByUid(user);

    // 判断更新结果并设置提示信息
    if (num > 0) {
        redirectAttributes.addFlashAttribute("success", "true");
        redirectAttributes.addFlashAttribute("message", "密码修改成功！");
    } else {
        redirectAttributes.addFlashAttribute("success", "false");
        redirectAttributes.addFlashAttribute("message", "密码修改失败，请重试。");
    }

    // 重定向到当前页面
    return "teacher/teacherEdit"; // 重定向到修改页面
}


}
