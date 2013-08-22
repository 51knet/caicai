package com.knet51.ccweb.controllers.common;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class CommonController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/searchUser")
	public String findUserByName(@RequestParam("searchParam") String searchParam,Model model){
		List<User> userList = userService.findAllUsers();
		List<User> newList = new ArrayList<User>();
		for(int i=0;i<userList.size();i++){
			if(userList.get(i).getName().contains(searchParam) && userList.get(i).getForbidden() == null){
				newList.add(userList.get(i));
			}
		}
		model.addAttribute("userList", newList);
		model.addAttribute("searchParam", searchParam);
		return "user.search.list";
	}
}
