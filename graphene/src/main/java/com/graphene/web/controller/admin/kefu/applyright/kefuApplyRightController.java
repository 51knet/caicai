package com.graphene.web.controller.admin.kefu.applyright;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.graphene.web.common.defs.GlobalDefs;
import com.graphene.web.jpa.entity.applyright.AlliesApplyRight;
import com.graphene.web.jpa.entity.applyright.ParkApplyRight;
import com.graphene.web.service.UserService;
import com.graphene.web.service.applyright.AlliesRightService;
import com.graphene.web.service.applyright.ParkRightService;
import com.graphene.web.util.fileUpload.FileUtil;


@Controller
public class kefuApplyRightController {
	@Autowired
	private UserService userService;
	@Autowired
	private ParkRightService parkRightService;
	@Autowired
	private AlliesRightService allieRightService;
	
	@RequestMapping(value="/admin/kefu/applyright/allies/{status}")
	public String showAllapplyright(@PathVariable String status,Model model, HttpSession session,
			@RequestParam(value="pageNumber",defaultValue="0") int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		
		Page<AlliesApplyRight> page = null;
		if(status.equals("pass")){
			page = allieRightService.findAllByStatus(pageNumber, pageSize, GlobalDefs.PASS);
		}else if(status.equals("waite")){
			page = allieRightService.findAllByStatus(pageNumber, pageSize, GlobalDefs.WAITE);
		}else{
			page = allieRightService.findAll(pageNumber, pageSize);
		}
		model.addAttribute("page", page);
		return "admin.kefu.applyright.allies.list";
	}
	

	@RequestMapping(value="/admin/kefu/applyright/allies/view/{id}")
	public String showapplyrightDetail(Model model,HttpSession session,@PathVariable Long id){
		
		AlliesApplyRight applyright = allieRightService.find(id);
		model.addAttribute("applyright", applyright);
		return "admin.kefu.applyright.allies.view";
	}
	
	@RequestMapping(value="/admin/kefu/applyright/park/{status}")
	public String showAllCoapplyright(@PathVariable String status,@PathVariable String type,Model model, HttpSession session,
			@RequestParam(value="pageNumber",defaultValue="0") int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){		
		
		Page<ParkApplyRight > page = null;
		if(status.equals("pass")){
			page = parkRightService.findAllByStatus(GlobalDefs.PASS, pageNumber, pageSize);
		}else if(status.equals("waite")){
			page = parkRightService.findAllByStatus(GlobalDefs.WAITE, pageNumber, pageSize);
		}else{
			page = parkRightService.findAll(pageNumber, pageSize);
		}
		model.addAttribute("page", page);
		return "admin.kefu.applyright.park.list";
	}
	

	

	@RequestMapping(value="/admin/kefu/applyright/park/view/{id}")
	public String showCoapplyrightDetail(Model model,HttpSession session,@PathVariable Long id){
		
		ParkApplyRight parkApplyRight = parkRightService.find(id);
		model.addAttribute("parkApply", parkApplyRight);
		return "admin.kefu.applyright.park.view";
	}
	

	
	@RequestMapping(value="/admin/kefu/applyright/park/status/change",method=RequestMethod.POST)
	public @ResponseBody boolean changeCoApplyrightStatus(@RequestParam("id") Long apply_id){
		boolean flag = false;
		ParkApplyRight applyRight = parkRightService.find(apply_id);
		if(applyRight!= null ){
			if(applyRight.getStatus().equals(GlobalDefs.PASS)){
				applyRight.setStatus(GlobalDefs.WAITE);
			}else{
				applyRight.setStatus(GlobalDefs.PASS);
			}
			applyRight = parkRightService.update(applyRight);
			flag = allieRightService.empower4User(apply_id, applyRight.getUser(), "park");
		}

		return flag;
	}
	
	@RequestMapping(value="/admin/kefu/applyright/allies/status/change",method=RequestMethod.POST)
	public @ResponseBody boolean changeApplyrightStatus(@RequestParam("id") Long apply_id,@RequestParam("types") String types){
		boolean flag = false;
		AlliesApplyRight applyRight = allieRightService.find(apply_id);
		if(applyRight!= null ){
			if(applyRight.getStatus().equals(GlobalDefs.PASS)){
				applyRight.setStatus(GlobalDefs.WAITE);
			}else{
				applyRight.setStatus(GlobalDefs.PASS);
			}
			applyRight = allieRightService.update(applyRight);
			flag = allieRightService.empower4User(apply_id, applyRight.getUser(), types);
		}

		return flag;

	}
	/**
	 * download apply_right resources
	 * @param resource_id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/kefu/applyright/allies/download/{id}")
	public String tempDownLoad(@PathVariable Long id,HttpServletResponse response,
			HttpServletRequest request,HttpSession session) {
		AlliesApplyRight applyRight = allieRightService.find(id);
		try {
			// download from webapp
			String path = session.getServletContext().getRealPath("/");
			
			String savePath = applyRight.getAlliesFile();
			String fileName = savePath.substring(savePath.lastIndexOf("/")+1, savePath.length());
			FileUtil.getInstance().downLoad(request, response, path+savePath,fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
