package com.ryan.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryan.domain.Department;
import com.ryan.domain.Role;
import com.ryan.domain.User;
import com.ryan.repository.DepartmentRepository;
import com.ryan.repository.RoleRepository;
import com.ryan.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	DepartmentRepository departmentRepository;

	@RequestMapping("/index")
	public String index(Model model, Principal user) throws Exception {

		boolean newrole = true, editrole = true, deleterole = true;

		model.addAttribute("newrole", newrole);
		model.addAttribute("editrole", editrole);
		model.addAttribute("deleterole", deleterole);

		model.addAttribute("user", user);
		return "user/index";
	}

	@RequestMapping(value = "/{id}")
	public String show(Model model, @PathVariable Long id) {
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "user/show";
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public Page<User> getList(Integer page, Integer size, String name) {
		log.info("page: {},size: {},name: {}", page, size, name);
		try {
			Pageable pageable = new PageRequest(page == null ? 0 : page, size == null ? 10 : size,
					new Sort(Sort.Direction.ASC, "id"));
			if(name==null)
				return userRepository.findAll(pageable);
			else
				return userRepository.findByUsernameContaining(name, pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/new")
	public String create(Model model) {
		List<Department> departments = departmentRepository.findAll();
		List<Role> roles = roleRepository.findAll();

		model.addAttribute("departments", departments);
		model.addAttribute("roles", roles);
		return "user/new";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(User user) throws Exception {
		user.setCreateTime(new Date());
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
		user.setPassword(bpe.encode(user.getPassword()));
		userRepository.save(user);
		log.info("新增的用户ID=" + user.getId());
		return "1";
	}

	@RequestMapping(value = "/edit/{id}")
	public String update(Model model, @PathVariable Long id) {
		User user = userRepository.findOne(id);

		List<Department> departments = departmentRepository.findAll();
		List<Role> roles = roleRepository.findAll();

		List<Long> rids = new ArrayList<>();
		for (Role role : user.getRoles()) {
			rids.add(role.getId());
		}

		model.addAttribute("user", user);
		model.addAttribute("departments", departments);
		model.addAttribute("roles", roles);
		model.addAttribute("rids", rids);
		return "user/edit";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	@ResponseBody
	public String update(User user) throws Exception {
		userRepository.save(user);
		log.info("修改的ID=" + user.getId());
		return "1";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable Long id) throws Exception {
		userRepository.delete(id);
		log.info("删除的ID=" + id);
		return "1";
	}

	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("/user")
	@ResponseBody
	public Page<User> user() {
		return userRepository.findAll(new PageRequest(0, 1));
	}
}