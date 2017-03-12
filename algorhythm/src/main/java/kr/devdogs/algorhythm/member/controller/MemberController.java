package kr.devdogs.algorhythm.member.controller;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.devdogs.algorhythm.member.dto.Member;
import kr.devdogs.algorhythm.member.service.MemberService;
import kr.devdogs.algorhythm.utils.FileUtils;

/**
 * 
 * @author Daniel
 */
@RestController
public class MemberController {
	@Autowired private MemberService memberService;
	@Autowired private FileUtils fileUtils;
	
	@RequestMapping(value="/api/member/join", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getSample(Member member) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		if(member.getEmail() == null 
				|| member.getPw() == null 
				|| member.getNickname() == null) {
			res.put("error", "Email, Password, Nickname is Required");
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
		
		if(memberService.memberJoin(member)) {
			res.put("result", "success");
		} else {
			res.put("result", "fail");
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	} 
}