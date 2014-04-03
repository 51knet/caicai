package com.knet51.patents.controllers.admin.kefu.rolevalid;

import java.util.Map;

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

import com.knet51.ccweb.jpa.entities.RoleValid;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.RoleValidService;
import com.knet51.patents.util.fileUpLoad.FTPUtil;
import com.knet51.patents.util.fileUpLoad.FileUtil;

@Controller
public class kefuRolevalidController {
	@Autowired
	private RoleValidService validService;	
	
	@RequestMapping(value="/admin/kefu/applypermit/{type}/{status}")
	public String showAllApplypermit(@PathVariable String status,@PathVariable String type,Model model, HttpSession session,
			@RequestParam(value="pageNumber",defaultValue="0") int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		
		Map<String, String> applypermit = GlobalDefs.getApplyPermitMap();
		model.addAttribute("permitMap", applypermit);
		
		Page<RoleValid> page = null;
		if(status.equals("pass")){
			page = validService.findRoleValidByStatusAndApplypermit(GlobalDefs.PASS, type, pageNumber, pageSize);
		}else if(status.equals("waite")){
			page = validService.findRoleValidByStatusAndApplypermit(GlobalDefs.WAITE, type, pageNumber, pageSize);
		}else{
			page = validService.findRoleValidPage(pageNumber, pageSize);
		}
		model.addAttribute("page", page);
		return "admin.kefu.applypermit.list";
	}
	

	

	@RequestMapping(value="/admin/kefu/applypermit/view/{id}")
	public String showApplypermitDetail(Model model,HttpSession session,@PathVariable Long id){
		
		RoleValid roleValid = validService.find(id);
		model.addAttribute("rolevalid", roleValid);
		
		Map<String, String> applypermit = GlobalDefs.getApplyPermitMap();
		model.addAttribute("permitMap", applypermit);
		return "admin.kefu.applypermit.view";
	}
	

	
	@RequestMapping(value="/admin/kefu/applypermit/status/change",method=RequestMethod.POST)
	public @ResponseBody boolean changeApplypermitStatus(@RequestParam("id") Long id){
		boolean flag = false;
		RoleValid roleValid = validService.find(id);
		if(roleValid!= null ){
			if(roleValid.getStatus().equals(GlobalDefs.PASS)){
				roleValid.setStatus(GlobalDefs.WAITE);
			}else{
				roleValid.setStatus(GlobalDefs.PASS);
			}
		}
		roleValid = validService.update(roleValid);
		
		if(roleValid != null){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * download temp
	 * @param resource_id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/kefu/applypermit/download/{id}")
	public String tempDownLoad(@PathVariable Long id,
			HttpServletResponse response,HttpSession session) {
		RoleValid roleValid = validService.find(id);
		try {
			//String path = session.getServletContext().getRealPath("/");
			String savePath = roleValid.getSavePath();
			String fileName = savePath.substring(savePath.lastIndexOf("/")+1, savePath.length());
			//FileUtil.downLoad(request, response, path+savePath,fileName);
			FTPUtil.getInstance().ftpDownLoad(response, savePath, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
