package com.xh.controller;

import com.github.pagehelper.PageInfo;
import com.xh.entity.Facility;
import com.xh.entity.User;
import com.xh.entity.admin.AdminVo;
import com.xh.entity.student.Repairs;
import com.xh.entity.teacher.Addfacility;
import com.xh.service.adminService.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Integer size = 10;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    /**
     * 设备进库
     * @param facility
     * @return
     */
    @RequestMapping("/addShebei.action")
    public String addShebei(Facility facility, Model model) {
        try {
            int num = adminService.add(facility);
            if (num > 0) {
                model.addAttribute("message", "设备添加成功");
                logger.info("设备进库成功，设备编号: {}, 影响行数: {}", facility.getFtid(), num);
            } else {
                model.addAttribute("message", "设备添加失败");
                logger.error("设备进库失败，设备编号: {}", facility.getFtid());
            }
        } catch (Exception e) {
            model.addAttribute("message", "设备添加时发生错误: " + e.getMessage());
            logger.error("设备进库时发生异常: ", e);
        }
        return "admin/jinku";
    }


    /**
     * 设备出库前的页面
     * @return
     */
    @RequestMapping("/info.action")
    public String info(Model model) {
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
    public String fenye(Integer page, Model model) {
        page = page == null ? 1 : page;
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
    public String chuku(Integer id, Model model) {
        Facility facility = adminService.findById(id);
        model.addAttribute("facility", facility);
        return "admin/chukuDetail";
    }




    /**
     * 出库设备
     * @param facility
     * @return
     */
    @RequestMapping("/chukuback.action")
    public String chukuback(Facility facility, Model model) {
        int num = adminService.updateById(facility);

        if (num > 0) {
            model.addAttribute("message", "设备出库成功");
        } else if(num == 0) {
            model.addAttribute("message", "设备出库数量必须大于0");
            return "forward:/admin/chuku.action";
        } else  {
            model.addAttribute("message", "设备出库失败");
            return "forward:/admin/chuku.action";
        }
        logger.info("设备出库成功，影响行数: {}", num);
        return "forward:/admin/fenye.action";
    }


    //查找修改设备信息
    @RequestMapping("/findFacility.action")
    public String findFacility(Integer id, Model model) {
        Facility facility = adminService.findById(id);
        model.addAttribute("facility", facility);
        return "admin/updateFacility";
    }

    //修改设备信息
    @RequestMapping("/updateFacility.action")
    public String updateFacility(Facility facility) {
        adminService.updateFacility(facility);
   return "forward:/admin/fenye.action";
    }

    /**
     * 多条件查询
     */
    @RequestMapping("/ajaxSplit.action")
    public String ajaxSplit(AdminVo adminVo, HttpServletRequest request, Integer page) {
        page = page == null ? 1 : page;
        PageInfo<List<Facility>> info = adminService.findByAdminVo(adminVo, size, page);
        request.setAttribute("info", info);
        request.setAttribute("adminVo", adminVo);
        return "admin/chuku";
    }

    /**
     * 查看老师需要申请安装的设备
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/findAll.action")
    public String findAll(Integer page, HttpServletRequest request) {
        page = page == null ? 1 : page;
        PageInfo<List<Addfacility>> info = adminService.findAddFacility(page);
        request.setAttribute("info", info);
        return "admin/shenQingAnZhuangSheBei";
    }

    /**
     * 点击安装设备
     * @param session
     * @param page
     * @param id  老师申请安装设备的这条记录
     * @param fname
     * @param request
     * @return
     */
//    @RequestMapping("/anzhuang.action")
//    public String anzhuang(HttpSession session, Integer page, String fname,Integer id,String location,HttpServletRequest request){
//        if (page == null){page = 1;}
//        PageInfo<List<Facility>> info = adminService.findByName(page,fname,id);
//        if (info.getList().size() <= 0){
//            //将原有的需要添加的记录查出来
//            PageInfo<List<Addfacility>> in1 = adminService.findAddFacility(page);
//            request.setAttribute("info", in1);
//            return "redirect:/admin/findAll.action";
//        }
//        request.setAttribute("info",info);
//        session.setAttribute("product",id);
//        //将安装设备的位置存进去，后面要使用
//        session.setAttribute("location", location);
//        return "redirect:/admin/findAll.action";
//    }
    @RequestMapping("/anzhuang.action")
    public String anzhuang(HttpSession session, Integer page, String fname, Integer id, String location, HttpServletRequest request) {
        if (page == null) {
            page = 1;
        }

        PageInfo<List<Facility>> info = adminService.findByName(page, fname, id);

        if (info.getList().size() <= 0) {
            // 仓库没有设备
            PageInfo<List<Addfacility>> in1 = adminService.findAddFacility(page);
            request.setAttribute("info", in1);
            request.setAttribute("msg", "仓库没有设备，请去进库");
            return "admin/shenQingAnZhuangSheBei";
        } else {
            // 申请通过
            request.setAttribute("info", info);
            session.setAttribute("product", id);
            session.setAttribute("location", location);
            request.setAttribute("msg", "申请通过");
            return "redirect:/admin/findAll.action";
        }


    }
    /**
     * 点击直接安装，减少库存
     * @param page
     * @param id
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/anzhuangPost.action")
    public String anzhuangPost(Integer page, Integer id, HttpSession session, HttpServletRequest request) {
        page = page == null ? 1 : page;
        Integer n = (Integer) session.getAttribute("product");
        String location = (String) session.getAttribute("location");
        int num = adminService.updateFacilityById(id, n, location);
        PageInfo<List<Addfacility>> info = adminService.findAddFacility(page);
        request.setAttribute("info", info);
        return "admin/shenQingAnZhuangSheBeialertPost";
    }

    /**
     * 查看已安装设备的信息
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/findAddfacilityByPlan.action")
    public String findAddfacilityByPlan(Integer page, HttpServletRequest request) {
        page = page == null ? 1 : page;
        PageInfo<List<Addfacility>> info = adminService.findAddfacilityByPlan(page);
        request.setAttribute("info", info);
        return "admin/chaKanAnZhuangSheBei";
    }


    //老师端查看已安装设备
    @RequestMapping("/findAddfacilityByPlan2.action")
    public String findAddfacilityByPlan2(Integer page, HttpServletRequest request) {
        page = page == null ? 1 : page;
        PageInfo<List<Addfacility>> info = adminService.findAddfacilityByPlan2(page);
        request.setAttribute("info", info);
        return "teacher/deleteFacility";
    }

    /**
     * 查看未维修设备的信息
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/chaKanWeiXiuXinXi.action")
    public String chaKanWeiXiuXinXi(Integer page, HttpServletRequest request) {
        page = page == null ? 1 : page;
        PageInfo<List<Repairs>> info = adminService.findServiceByplan(page);
        request.setAttribute("info", info);
        return "admin/WeiXiuSheBei";
    }

     @RequestMapping("/repairPassed.action")
    public String repairPassed(HttpSession session, Integer page, String fname,Integer id,String location,HttpServletRequest request){

        adminService.repairPassed(page,fname,id);

        return "redirect:/admin/chaKanWeiXiuXinXi.action";
    }

    //老师查看已维修设备
    @RequestMapping("/deleteFacility.action")
    public String deleteFacility(Integer page, HttpServletRequest request) {
        page = page == null ? 1 : page;
        PageInfo<List<Addfacility>> info = adminService.findServiceByplan2(page);
        request.setAttribute("info", info);
        return "admin/deleteSheBei";
    }

    //报废处理
    @RequestMapping("/deleteCheck.action")
    public String deleteCheck(Integer id, HttpSession session, HttpServletRequest request) {
        adminService.deleteCheckById(id);
        return "redirect:/admin/deleteFacility.action"; // 重定向到另一个 URL
    }




    /**
     * 查看已维修的设备
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/chaKanYiXiuXinXi.action")
    public String chaKanYiXiuXinXi(Integer page, HttpServletRequest request) {
        page = page == null ? 1 : page;
        PageInfo<List<Repairs>> info = adminService.findServiceByYiXiuplan(page);
        request.setAttribute("info", info);
        return "admin/YiXiuSheBei";
    }


    /**
     * 添加维修人员账号
     * @param user
     * @return
     */
    @RequestMapping("/addWorker.action")
    public String addWorker(User user, Model model) {
        User existingUser = adminService.findByUname(user.getUname());
        if (existingUser != null) {
            model.addAttribute("errorMessage", "用户名已存在!");
            return "admin/addWorker";
        }
        int num = adminService.addWorker(user);

        if (num > 0) {

            model.addAttribute("successMessage", "添加成功!");
        } else {

            model.addAttribute("errorMessage", "添加失败!");
        }

        return "admin/addWorker";
    }

    /**
     * 查看所有的维修工信息
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/findWork.action")
    public String findWork(Integer page, HttpServletRequest request) {
        page = page == null ? 1 : page;
        PageInfo<List<User>> info = adminService.findWorker(page);
        request.setAttribute("info", info);
        return "admin/findWorker";
    }


    @PostMapping("/deleteUser.action")
    @ResponseBody
    public Map<String, Object> deleteUser(@RequestParam("uid") Long uid) {
        Map<String, Object> result = new HashMap<>();
        try {
            adminService.delUser(uid);
            result.put("success", true);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    /**
     * 查看所有的老师信息
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/findTeacher.action")
    public String findTeacher(Integer page, HttpServletRequest request) {
        page = page == null ? 1 : page;
        PageInfo<List<User>> info = adminService.findTeacher(page);
        request.setAttribute("info", info);
        return "admin/findTeacher";
    }

    @RequestMapping("/addTeacher.action")
    public String addTeacher(User user, Model model) {
        User existingUser = adminService.findByUname(user.getUname());
        if (existingUser != null) {
            model.addAttribute("errorMessage", "用户名已存在!");
            return "admin/addTeacher";
        }
        int num = adminService.addTeacher(user);
        if (num > 0) {
            model.addAttribute("successMessage", "添加成功!");
        } else {
            model.addAttribute("errorMessage", "添加失败!");
        }

        return "admin/addTeacher";
    }


//    /**
//     * 查看所有的学生信息
//     * @param page
//     * @param request
//     * @return
//     */
//    @RequestMapping("/findStudent.action")
//    public String findStudent(Integer page, HttpServletRequest request) {
//        page = page == null ? 1 : page;
//        PageInfo<List<User>> info = adminService.findStudent(page);
//        request.setAttribute("info", info);
//        return "admin/findStudent";
//    }

    @RequestMapping("/addStudent.action")
    public String addStudent(User user, Model model) {
        User existingUser = adminService.findByUname(user.getUname());
        if (existingUser != null) {
            model.addAttribute("errorMessage", "用户名已存在!");
            return "admin/addStudent";
        }
        int num = adminService.addStudent(user);
        if (num > 0) {
            model.addAttribute("successMessage", "添加成功!");
        } else {
            model.addAttribute("errorMessage", "添加失败!");
        }
        return "admin/addStudent";
    }

    /**
     * 商品采购
     * @return
     */
    @RequestMapping("/caigou.action")
    public String caigou(HttpServletRequest request, Integer page) {
        page = page == null ? 1 : page;
        PageInfo<List<Addfacility>> info = adminService.caigou(page);
        request.setAttribute("info", info);
        return "admin/caigou";
    }



    @RequestMapping("/findAdmin.action")
    public String findAdmin(Integer page, HttpServletRequest request) {
        page = page == null ? 1 : page;
        PageInfo<List<User>> info = adminService.findAdmin(page);
        request.setAttribute("info", info);
        return "admin/findAdmin";
    }


    @RequestMapping("/updateUser.action")
    public String showUpdateUserForm(int uid, Model model) {
        User user = adminService.getUserById(uid);
        model.addAttribute("user", user);
        return "admin/updateUser";
    }

    @RequestMapping("/updateUser2.action")
    @ResponseBody // 确保返回 JSON 数据
    public Map<String, Object> updateUser(User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            adminService.updateUser(user);
            response.put("success", true);
            response.put("message", "用户信息修改成功");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "用户信息修改失败：" + e.getMessage());
        }
        return response;
    }

}