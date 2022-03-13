package com.xh.controller;

import com.github.pagehelper.PageInfo;
import com.xh.entity.Facility;
import com.xh.service.adminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description:
 * @Author: xiaohao
 * @Time: 2022/3/11 23:56
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Integer size = 3;

    @Autowired
    private AdminService adminService;

    /**
     * 设备进库
     * @param facility
     * @return
     */
    @RequestMapping("/addShebei.action")
    public String addShebei(Facility facility){
        int num = adminService.add(facility);
        System.out.println(num);

        return "admin/jinkualert";
    }

    /**
     * 设备出库前的页面
     * @return
     */
    @RequestMapping("/info.action")
    public String info(Model model){
        List<Facility> info = adminService.info();
        model.addAttribute("info", info);
        return "admin/chuku";
    }

    /**
     * 分页查询设备的信息，对出库做准备
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/fenye.action")
    public String fenye(Integer page ,Model model){
        if (page == null){
            page = 1;
        }
        PageInfo<List<Facility>> info = adminService.findAll(page, size);
        model.addAttribute("info", info);
        return "admin/chuku";
    }


    /**
     * 查询出所有的设备信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/chuku.action")
    public String chuku(Integer id,Model model){
        System.out.println("1");
        Facility facility = adminService.findById(id);
        model.addAttribute("facility",facility);
        return "admin/chukuDetail";
    }

    /**
     * 出库设备
     * @param
     * @param
     * @return
     */
    @RequestMapping("/chukuback.action")
    public String chukuback(Facility facility){
        System.out.println("11");
        int num = adminService.updateById(facility);
        System.out.println(num);
        return "forward:/admin/fenye.action";
    }
}
