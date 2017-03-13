package com.ryan.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryan.domain.Role;
import com.ryan.repository.RoleRepository;

@Controller
@RequestMapping("/role")
public class RoleController {
    
	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping("/index")
    public String index(Model model, Principal user) throws Exception{
        model.addAttribute("user", user);
        return "role/index";
    }

    @RequestMapping(value="/{id}")
    public String show(Model model,@PathVariable Long id) {
        Role role = roleRepository.findOne(id);
        model.addAttribute("role",role);
        return "role/show";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Page<Role> getList(Integer page, Integer size, String name) {
		log.info("page: {},size: {},name: {}", page, size, name);
        try {
        	Pageable pageable = new PageRequest(page == null ? 0 : page, size == null ? 10 : size,
					new Sort(Sort.Direction.ASC, "id"));
			if(name==null)
				return roleRepository.findAll(pageable);
			else
				return roleRepository.findByNameContaining(name, pageable);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/new")
    public String create(){
        return "role/new";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(Role role) throws Exception{
    	log.info("time: {}",role.getCreateTime());
        roleRepository.save(role);
        log.info("新增->ID="+role.getId());
        return "1";
    }

    @RequestMapping(value="/edit/{id}")
    public String update(Model model,@PathVariable Long id){
        Role role = roleRepository.findOne(id);
        model.addAttribute("role",role);
        return "role/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public String update(Role role) throws Exception{
        roleRepository.save(role);
        log.info("修改->ID="+role.getId());
        return "1";
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) throws Exception{
        roleRepository.delete(id);
        log.info("删除->ID="+id);
        return "1";
    }

}
