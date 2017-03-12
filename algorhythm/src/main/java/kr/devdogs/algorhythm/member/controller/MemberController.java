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
	public static final int CODE_DUPLECATE_EMAIL = 0;
	public static final int CODE_USABLE_EMAIL = 1;
	public static final int CODE_LOGIN_SUCCESS = 0;
	public static final int CODE_LOGIN_FAIL = 1;
	public static final int CODE_LOGOUT_SUCCESS = 1;
	
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
	
	@RequestMapping(value="/api/member/login", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> login(Member member, HttpSession session) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		if(member.getEmail() == null 
				|| member.getPw() == null) {
			res.put("error", "Email, Password is Required");
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
		Member currentMember = memberService.memberLogin(member);
		if(currentMember != null) {
			session.setAttribute("email", currentMember.getEmail());
			session.setAttribute("member_no", currentMember.getMember_no());
			session.setAttribute("nickname", currentMember.getNickname());
			res.put("result", CODE_LOGIN_SUCCESS);
		} else {
			res.put("result", CODE_LOGIN_FAIL);
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/member/logout", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
		session.removeAttribute("email");
		session.removeAttribute("member_no");
		session.removeAttribute("nickname");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/member/Duplicate", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> duplicate(Member member) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		if(member.getEmail() == null) {
			res.put("error", "Email is Required");
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
		
		if(memberService.memberDuplicate(member)) {
			res.put("result", CODE_DUPLECATE_EMAIL);
		} else {
			res.put("result", CODE_USABLE_EMAIL);
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/member/newPassword", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> PasswordUpdate(Member member, 
												@RequestParam(name="newPw", required=true)String newPw, 
												HttpSession session) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		if(member.getPw() == null){
			res.put("error", "값을 입력하세요");
		}else {
			String email = String.valueOf(session.getAttribute("email"));
			member.setEmail(email);
			memberService.memberPasswordUpdate(member, newPw);
			res.put("result", "hi");
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
		
		
	}
}