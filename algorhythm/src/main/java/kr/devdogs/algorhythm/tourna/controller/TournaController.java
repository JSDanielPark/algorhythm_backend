
package kr.devdogs.algorhythm.tourna.controller;

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

import kr.devdogs.algorhythm.tourna.service.TournaService;
import kr.devdogs.algorhythm.member.dto.Member;
import kr.devdogs.algorhythm.member.service.MemberService;
import kr.devdogs.algorhythm.utils.FileUtils;


@RestController
public class TournaController {
	
@Autowired TournaService tournaService;
	
	@RequestMapping(value="/api/tourna/compile", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> compile(
			@RequestParam(name="source", required=true)String source, HttpSession session) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		String username = String.valueOf(session.getAttribute(Member.SESSION_KEY_NICKNAME));
		String result = tournaService.getCompileLog(source, username);
		
		if("success".equals(result)) {
			res.put("result", result);
		} else {
			res.put("result", "fail");
			res.put("error", result);
		}
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	} 
	
	
	@RequestMapping(value="/api/tourna/list/{page}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> list(
			@PathVariable(value="page", required=false) int page,
			@RequestParam(name="subject", required=false) String subject,
			@RequestParam(name="difficulty", required=false) String difficulty) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		if(page == 0)
			page = 1;
		
		List<Map<String, Object>> examList = tournaService.getTournaList(page, subject, difficulty);
		int maxPageCount = tournaService.getMaxPage(subject, difficulty);
		
		res.put("list",examList);
		res.put("maxPage", maxPageCount);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}