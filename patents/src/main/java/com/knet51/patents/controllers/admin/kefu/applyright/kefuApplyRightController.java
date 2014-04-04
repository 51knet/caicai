package com.knet51.patents.controllers.admin.kefu.applyright;

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

import com.knet51.ccweb.jpa.entities.ApplyRight;
import com.knet51.patents.controllers.common.defs.GlobalDefs;
import com.knet51.patents.jpa.services.ApplyRightService;
import com.knet51.patents.util.fileUpLoad.FTPUtil;
import com.knet51.patents.util.fileUpLoad.FileUtil;

@Controller
public class kefuApplyRightController {
	@Autowired
	private ApplyRightService applyService;	
	
	@RequestMapping(value="/admin/kefu/applyright/{type}/{status}")
	public String showAllapplyright(@PathVariable String status,@PathVariable String type,Model model, HttpSession session,
			@RequestParam(value="pageNumber",defaultValue="0") int pageNumber, @RequestParam(value="pageSize", defaultValue="20") int pageSize){
		
		Map<String, String> rightMap = GlobalDefs.getApplyRightMap();
		model.addAttribute("rightMap", rightMap);
		
		Page<ApplyRight> page = null;
		if(status.equals("pass")){
			page = applyService.findApplyRightByStatusAndApplypermit(GlobalDefs.PASS, type, pageNumber, pageSize);
		}else if(status.equals("waite")){
			page = applyService.findApplyRightByStatusAndApplypermit(GlobalDefs.WAITE, type, pageNumber, pageSize);
		}else{
			page = applyService.findApplyRightPage(pageNumber, pageSize);
		}
		model.addAttribute("page", page);
		return "admin.kefu.applyright.list";
	}
	

	

	@RequestMapping(value="/admin/kefu/applyright/view/{id}")
	public String showapplyrightDetail(Model model,HttpSession session,@PathVariable Long id){
		
		ApplyRight applyright = applyService.find(id);
		model.addAttribute("applyright", applyright);
		
		Map<String, String> rightMap = GlobalDefs.getApplyRightMap();
		model.addAttribute("rightMap", rightMap);
		return "admin.kefu.applyright.view";
	}
	

	
	@RequestMapping(value="/admin/kefu/applyright/status/change",method=RequestMethod.POST)
	public @ResponseBody boolean changeapplyrightStatus(@RequestParam("id") Long apply_id){
		boolean flag = false;
		ApplyRight applyRight = applyService.find(apply_id);
		if(applyRight!= null ){
			if(applyRight.getStatus().equals(GlobalDefs.PASS)){
				applyRight.setStatus(GlobalDefs.WAITE);
			}else{
				applyRight.setStatus(GlobalDefs.PASS);
			}
			applyRight = applyService.update(applyRight);
			flag = applyService.empower4User(apply_id, applyRight.getUser());
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
	@RequestMapping(value="/admin/kefu/applyright/download/{id}")
	public String tempDownLoad(@PathVariable Long id,
			HttpServletResponse response,HttpSession session) {
		ApplyRight applyRight = applyService.find(id);
		try {
			// download from webapp
			//String path = session.getServletContext().getRealPath("/");
			//FileUtil.downLoad(request, response, path+savePath,fileName);
			String savePath = applyRight.getSavePath();
			String fileName = savePath.substring(savePath.lastIndexOf("/")+1, savePath.length());
			
			FTPUtil.getInstance().ftpDownLoad(response, savePath, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}