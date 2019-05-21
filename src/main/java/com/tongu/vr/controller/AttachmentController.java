package com.tongu.vr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tongu.vr.config.OssConfig;
import com.tongu.vr.model.vo.FileVO;
import com.tongu.vr.service.AttachmentService;

@RestController
public class AttachmentController {
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private OssConfig config;
	
	@RequestMapping(value = {"/", "info"})
	public ModelAndView index(ModelMap modelMap) {
		modelMap.put("size", config.getMaxSize());
		return new ModelAndView("info");
	}

	@PostMapping("/upload")
	@ResponseBody
	public ModelAndView upload(@RequestParam MultipartFile file, ModelMap modelMap)  {
		try {
			FileVO fileVO = attachmentService.save(file);
			modelMap.put("attachment", fileVO);
			return new ModelAndView("qr");
		} catch (Exception e) {
			modelMap.put("size", config.getMaxSize());
			modelMap.put("error", e.getMessage());
			return new ModelAndView("info");
		}	
	}
	
	@GetMapping("/list")
	@ResponseBody
	public ModelAndView list(ModelMap modelMap)  {
		List<FileVO> list = attachmentService.queryList();
		modelMap.put("attachmentList", list);
		return new ModelAndView("list");
	}
}
