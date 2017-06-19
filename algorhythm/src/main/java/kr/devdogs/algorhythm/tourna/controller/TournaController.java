
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
			@RequestParam(name="title", required=false) String title) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		if(page == 0)
			page = 1;
		
		List<Map<String, Object>> tournaList = tournaService.getTournaList(page, title);
		int maxPageCount = tournaService.getMaxPage_List(title);
		
		res.put("list",tournaList);
		res.put("maxPage", maxPageCount);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/tourna/detail/{page}&{tourna_no}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> detail(
			@PathVariable(value="page", required=false) int page,
			@PathVariable(value="tourna_no", required=false) int tourna_no,
			@RequestParam(name="title", required=false) String title,
			@RequestParam(name="difficulty", required=false) String difficulty) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		if(page == 0)
			page = 1;
		
		List<Map<String, Object>> tournaDetail = tournaService.getTournaDetail(page, title, difficulty, tourna_no);
		int maxPageCount = tournaService.getMaxPage(title, difficulty);
		
		res.put("detail",tournaDetail);
		res.put("maxPage", maxPageCount);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/tourna/write", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> write(
			@RequestParam(name="title", required=true) String title,
			@RequestParam(name="difficulty", required=true) Integer difficulty,
			@RequestParam(name="content", required=true) String content,
			@RequestParam(name="test_input", required=true) String test_input,
			@RequestParam(name="test_output", required=true) String test_output,
			@RequestParam(name="testcase_input[]", required=true) String[] testcase_input,
			@RequestParam(name="testcase_output[]", required=true) String[] testcase_output,
			HttpSession session) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("writer", String.valueOf(session.getAttribute(Member.SESSION_KEY_NO)));
		param.put("title", title);
		param.put("content", content);
		param.put("test_input", test_input);
		param.put("test_output", test_output);
		param.put("difficulty", difficulty);
		
		List<Map<String, String>> testcase = new ArrayList<>();
		if(testcase_input.length == testcase_output.length) {
			for(int i=0; i<testcase_input.length; i++) {
				Map<String, String> testcasePair = new HashMap<>();
				testcasePair.put("input", testcase_input[i]);
				testcasePair.put("output", testcase_output[i]);
				testcase.add(testcasePair);
			}
		}
		
		int result = tournaService.writeTourna(param, testcase);
		res.put("result", result);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	} 
	
	@RequestMapping(value="/api/tourna/addtourna", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addtourna(
			@RequestParam(name="title", required=true) String title,
			HttpSession session) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("writer", String.valueOf(session.getAttribute(Member.SESSION_KEY_NO)));
		param.put("title", title);
		
		int result = tournaService.addTourna(param);
		res.put("result", result);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	} 
}