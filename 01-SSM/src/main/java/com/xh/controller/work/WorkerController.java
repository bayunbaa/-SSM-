package com.xh.controller.work;

import com.github.pagehelper.PageInfo;
import com.xh.entity.User;
import com.xh.entity.student.Repairs;
import com.xh.entity.teacher.Addfacility;
import com.xh.service.workerService.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @Author: xiaohao
 * @Time: 2022/3/27 13:44
 */
@Controller
@RequestMapping("/worker")
public class WorkerController {
    //注入，操作报修记录
    @Autowired
    private WorkerService workerService;

    /**
     * 查询出所有未处理的报修记录
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/editBaoXiuState.action")
    public String editBaoXiuState(Integer page, HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        PageInfo<List<Repairs>> info = workerService.findAll(page);
        request.setAttribute("info", info);

        return "worker/baoXiuXinXi";
    }


    /**
     * 修改状态，一勾选表示此条问题已被解决
     * @param id
     * @return
     */
    @RequestMapping("/editXinxi.action")
    @ResponseBody
    public String editXinxi(Integer id){
        int num = workerService.updateById(id);
        return "1";
    }


    /**
     * 查看已解决的问题
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/selectByPlan.action")
    public String selectByPlan(Integer page, HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        PageInfo<List<Repairs>> info = workerService.selectByPlan(page);
        request.setAttribute("info", info);

        return "worker/yiJiJue";
    }


    //查看报废
    @RequestMapping("/deleteFacility.action")
    public String findDelete(Integer page, HttpSession session, HttpServletRequest request){
        page = page == null ? 1 : page;
        User user = (User) session.getAttribute("user");
        PageInfo<List<Addfacility>> info = workerService.findByUid(page, user.getUid());
        request.setAttribute("info",info);
        return "worker/findDelete";
    }

    //报废处理
    @RequestMapping("/delete.action")
    public String delete(Integer id, HttpSession session, HttpServletRequest request) {
        workerService.deleteById(id);
        return "redirect:/worker/deleteFacility.action"; // 重定向到另一个 URL
    }

//    //点击报废
//    @RequestMapping("/delete.action")
//    public String delete(Integer id, HttpSession session, HttpServletRequest request) {
//        workerService.deleteById(id);
//        return "redirect:/worker/deleteFacility.action"; // 重定向到另一个 URL
//    }
//


    /* 老师申请报废设备，管理员同意后，维修工需要去报废,这里查出来所有报废的设备 */
    @RequestMapping("/findAlreadyDelete.action")
    public String findAlreadyDelete(Integer page, HttpSession session, HttpServletRequest request){
        page = page == null ? 1 : page;
        User user = (User) session.getAttribute("user");
        PageInfo<List<Addfacility>> info =  workerService.findDelete(page, user.getUid());
        request.setAttribute("info",info);
        return "teacher/findDelete";
    }



    /* 老师申请安装设备，管理员同意后，维修工需要去安装,这里查出来所有没有安装的设备 */
    @RequestMapping("/installshebeiPre.action")
    public String installshebeiPre(Integer page, HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        PageInfo<List<Addfacility>> info =workerService.findAddFacilityByPlan(page);
        request.setAttribute("info", info);
        return "worker/xvYaoAnZhuangSheBei";
    }

    /**
     * 点击复选框，表示已安装完成这个设备,根据审核通过的这条数据的id
     * @param id
     * @return
     */
    @RequestMapping("/installshebei.action")
    public String installshebei(@RequestParam(required = false) Integer id, @RequestParam(required = false) Integer page,
                                RedirectAttributes redirectAttributes)
    {
        if (id != null) {
            int num = workerService.updateAddFacilityByPlan(id);
            if (num > 0) {
                redirectAttributes.addFlashAttribute("message", "安装成功");
            } else {
                redirectAttributes.addFlashAttribute("message", "安装失败");
            }
        }
        if (page == null) {
            page = 1;
        }
        redirectAttributes.addAttribute("page", page);
        return "redirect:/worker/installshebeiPre.action";
    }


}
