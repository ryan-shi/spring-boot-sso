package com.ryan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryan.domain.Department;
import com.ryan.repository.DepartmentRepository;

import java.security.Principal;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private static Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping("/index")
    public String index(Model model, Principal user) throws Exception{
        model.addAttribute("user", user);
        return "department/index";
    }

    @RequestMapping(value="/{id}")
    public String show(Model model,@PathVariable Long id) {
        Department department = departmentRepository.findOne(id);
        model.addAttribute("department",department);
        return "department/show";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Page<Department> getList(Integer page, Integer size, String name) {
		log.info("page: {},size: {},name: {}", page, size, name);
        try {
        	Pageable pageable = new PageRequest(page == null ? 0 : page, size == null ? 10 : size,
					new Sort(Sort.Direction.ASC, "id"));
			if(name==null)
				return departmentRepository.findAll(pageable);
			else
				return departmentRepository.findByNameContaining(name, pageable);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/new")
    public String create(){
        return "department/new";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(Department department) throws Exception{
        departmentRepository.save(department);
        log.info("新增->ID="+department.getId());
        return "1";
    }

    @RequestMapping(value="/edit/{id}")
    public String update(ModelMap model,@PathVariable Long id){
        Department department = departmentRepository.findOne(id);
        model.addAttribute("department",department);
        return "department/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public String update(Department department) throws Exception{
        departmentRepository.save(department);
        log.info("修改->ID="+department.getId());
        return "1";
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) throws Exception{
        departmentRepository.delete(id);
        log.info("删除->ID="+id);
        return "1";
    }

}
