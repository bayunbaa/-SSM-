package com.xh.controller;

import com.github.pagehelper.PageInfo;
import com.xh.entity.Facility;
import com.xh.entity.User;
import com.xh.entity.admin.AdminVo;
import com.xh.entity.student.Repairs;
import com.xh.entity.teacher.Addfacility;
import com.xh.service.adminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    /**
     * 多条件查询
     */
    @RequestMapping("/ajaxSplit.action")
    public String ajaxSplit(AdminVo adminVo, HttpServletRequest request, Integer page){
        if (page == null){
            page=1;
        }
        PageInfo<List<Facility>> info =  adminService.findByAdminVo(adminVo, size, page);
        System.out.println(info);
        request.setAttribute("info", info);
        request.setAttribute("adminVo", adminVo);
        return "admin/chuku";
    }



//    findAll.action

    /**
     * 查看老师需要申请安装的设备
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/findAll.action")
    public String findAll(Integer page, HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        PageInfo<List<Addfacility>> info = adminService.findAddFacility(page);
        request.setAttribute("info", info);
        return "admin/shenQingAnZhuangSheBei";
    }


    /**
     * 点击安装设备
     * @param session
     * @param page
     * @param id  老师申请安装设备的这条记录
     * @param request
     * @return
     */
    @RequestMapping("/anzhuang.action")
    public String anzhuang(HttpSession session, Integer page, Integer id, String location,HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        PageInfo<List<Facility>> info = adminService.findByName(page, id);
        if (info.getList().size() <= 0){
            //将原有的需要添加的记录查出来
            PageInfo<List<Addfacility>> in1 = adminService.findAddFacility(page);
            request.setAttribute("info", in1);
            return "admin/shenQingAnZhuangSheBeialert";
        }
        request.setAttribute("info",info);
        session.setAttribute("product",id);
        //将安装设备的位置存进去，后面要使用
        session.setAttribute("location", location);
        return "admin/anZhuangSheBei";
    }


    /**
     * 点击直接安装，减少库存
     * @param id
     * @return
     */
    @RequestMapping("/anzhuangPost.action")
    public String anzhuangPost(Integer page, Integer id,  HttpSession session,HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        //获取老师申请安装设备的这条记录
        Integer n =(Integer) session.getAttribute("product");
        //获取安装设备的位置
        String location = (String) session.getAttribute("location");

        int num =  adminService.updateFacilityById(id, n,location);
        //查询出所有没有安装好的设备信息
        PageInfo<List<Addfacility>> info = adminService.findAddFacility(page);
        request.setAttribute("info", info);


        //设备安装成功
        return "admin/shenQingAnZhuangSheBeialertPost";
    }

    /**
     * 查看已安装设备的信息
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/findAddfacilityByPlan.action")
    public String findAddfacilityByPlan(Integer page, HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        PageInfo<List<Addfacility>> info =  adminService.findAddfacilityByPlan(page);
        request.setAttribute("info",info);
        return "admin/chaKanAnZhuangSheBei";
    }


    /**
     * 查看未维修设备的信息
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/chaKanWeiXiuXinXi.action")
    public String chaKanWeiXiuXinXi(Integer page, HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        PageInfo<List<Repairs>> info =  adminService.findServiceByplan(page);
        request.setAttribute("info", info);
        return "admin/WeiXiuSheBei";
    }

    /* chaKanYiXiuXinXi.action */

    /**
     * 查看已维修的设备
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/chaKanYiXiuXinXi.action")
    public String chaKanYiXiuXinXi(Integer page, HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        PageInfo<List<Repairs>> info =  adminService.findServiceByYiXiuplan(page);
        request.setAttribute("info", info);
        return "admin/WeiXiuSheBei";
    }

    /**
     * 注册维修工账号，ajax判断用户名是否重读
     * @return
     */
    @RequestMapping("/ajaxUname.action")
    @ResponseBody
    public String ajaxUname(String uname){
        System.out.println("呀呀呀");
        User user = adminService.findAjaxUname(uname);
        if (user == null){
            return null;
        }
        return "1";
    }


    /**
     * 添加维修人员账号
     * @param user
     * @return
     */
    @RequestMapping("/addWorker.action")
    public String addWorker(User user){
        int num = adminService.addWorker(user);
        return "admin/addWorkerAlert";
    }


    /**
     * 查看所有的维修工信息
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/findWork.action")
    public String findWork(Integer page, HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        PageInfo<List<User>> info =  adminService.findWorker(page);
        request.setAttribute("info", info);
        return "admin/findWorker";
    }

    /**
     * 查看所有的老师信息
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/findTeacher.action")
    public String findTeacher(Integer page, HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        PageInfo<List<User>> info =  adminService.findTeacher(page);
        request.setAttribute("info", info);
        return "admin/findTeacher";
    }

    /**
     * 查看所有的学生信息
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/findStudent.action")
    public String findStudent(Integer page, HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        PageInfo<List<User>> info =  adminService.findStudent(page);
        request.setAttribute("info", info);
        return "admin/findStudent";
    }


    /**
     * 商品采购
     * @return
     */
    @RequestMapping("/caigou.action")
    public String caigou(HttpServletRequest request, Integer page){
        if (page == null){
            page = 1;
        }
        PageInfo<List<Addfacility>> info = adminService.caigou(page);
        request.setAttribute("info", info);
        return "admin/caigou";
    }





}
