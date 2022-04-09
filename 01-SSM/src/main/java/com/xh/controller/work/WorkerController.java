package com.xh.controller.work;

import com.github.pagehelper.PageInfo;
import com.xh.entity.student.Repairs;
import com.xh.entity.teacher.Addfacility;
import com.xh.service.workerService.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @ResponseBody
    public String installshebei(Integer id){
       int num = workerService.updateAddFacilityByPlan(id);
        return "安装成功";
    }

    /**
     * 维修工点击安装完成，跳转到新的页面显示，弹出安装成功
     * @return
     */
    @RequestMapping("/installshebeiSuff.action")
    public String installshebeiSuff(Integer page, HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        PageInfo<List<Addfacility>> info =workerService.findAddFacilityByPlan(page);
        request.setAttribute("info", info);
        return "worker/xvYaoAnZhuangSheBeiAlert";
    }


}
