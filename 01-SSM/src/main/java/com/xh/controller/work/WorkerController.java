package com.xh.controller.work;

import com.github.pagehelper.PageInfo;
import com.xh.entity.student.Repairs;
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
        System.out.println("1");
        if (page == null){
            page = 1;
        }
        PageInfo<List<Repairs>> info = workerService.selectByPlan(page);
        request.setAttribute("info", info);

        return "worker/yiJiJue";
    }


}
