package kr.devdogs.algorhythm.template.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.devdogs.algorhythm.exam.mapper.ExamMapper;
import kr.devdogs.algorhythm.exam.service.ExamService;
import kr.devdogs.algorhythm.member.dto.Member;
import kr.devdogs.algorhythm.member.service.MemberService;
import kr.devdogs.algorhythm.template.service.TemplateService;
import kr.devdogs.algorhythm.utils.FileUtils;

/**
 * 
 * @author Daniel
 */
@RestController
public class TemplateController {
	@Autowired TemplateService templateService;
	
	@RequestMapping(value="/api/template/{lang}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> compile(
			@PathVariable(value="lang", required=true) String language) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("template",templateService.getTemplate(language));
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	} 
}