package kr.devdogs.algorhythm.file.controller;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.devdogs.algorhythm.file.service.FileService;



@Controller
public class FileController {
	@Autowired private FileService fileService;
	
	/** 등록 */
	@RequestMapping(value="/api/file/upload/photo", method=RequestMethod.POST)
	public @ResponseBody String photoUpload(@RequestParam("upload") MultipartFile photo, 
			@RequestParam(name="CKEditorFuncNum", required=true) String key,
			HttpSession session, HttpServletResponse response) {
		String uploadedPath = fileService.uploadPhoto(photo);
		response.setContentType("text/html");
	    response.setCharacterEncoding("UTF-8");
		if(uploadedPath == null) {
			return "업로드에 실패했습니다.";
		}
		return "<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction('"+key+"', '"+uploadedPath+"')</script>";
	}
}