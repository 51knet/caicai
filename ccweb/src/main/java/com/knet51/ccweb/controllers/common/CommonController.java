package com.knet51.ccweb.controllers.common;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.services.UserService;

@Controller
public class CommonController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/searchUser",method=RequestMethod.POST)
	public String findUserByName(@RequestParam("searchParam") String searchParam,Model model){
		try {
			List<User> userList = userService.findAllUsers();
			List<User> newUserList = new ArrayList<User>();
			if(searchParam.trim() != null ){
				for(int i=0;i<userList.size();i++){
					if(userList.get(i).getForbidden() == null && userList.get(i).getName()!=null && userList.get(i).getName().contains(searchParam) ){
						newUserList.add(userList.get(i));
					}
				}
				System.out.println("---new size="+newUserList.size());
				model.addAttribute("userList", newUserList);
			}else{
				model.addAttribute("userList", userList);
			}
			
			model.addAttribute("searchParam", searchParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user.search.list";
	}
}
