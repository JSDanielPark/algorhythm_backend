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
	public static final String CODE_JOIN_SUCCESS = "success";
	public static final String CODE_JOIN_FAIL = "fail";

	public static final int CODE_USABLE_EMAIL = 0;
	public static final int CODE_DUPLECATE_EMAIL = 1;
	
	public static final int CODE_LOGIN_SUCCESS = 0;
	public static final int CODE_LOGIN_FAIL = 1;
	
	@Autowired private MemberService memberService;
	@Autowired private FileUtils fileUtils;
	
	
	@RequestMapping(value="/api/member/join", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getSample(Member member) {
		Map<String, Object> res = new HashMap<String, Object>();

		if(member.getEmail() == null 
				|| member.getPw() == null 
				|| member.getNickname() == null) {
			res.put("error", "Email, Password, Nickname is Required");
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
		
		if(memberService.isEmailDuplicate(member)) {
			res.put("result", CODE_JOIN_FAIL);
			res.put("errMsg", "이메일이 중복됩니다.");
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
		if(memberService.memberJoin(member)) {
			res.put("result", CODE_JOIN_SUCCESS);
		} else {
			res.put("result", CODE_JOIN_FAIL);
			res.put("errMsg", "Unknown Error");
		}
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	} 
	
	
	@RequestMapping(value="/api/member/check/login", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> isLogin(HttpSession session) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		if(session.getAttribute(Member.SESSION_KEY_EMAIL) != null) {
			res.put("isLogin", "yes");
			res.put("email", session.getAttribute(Member.SESSION_KEY_EMAIL));
			res.put("nickname", session.getAttribute(Member.SESSION_KEY_NICKNAME));
		} else {
			res.put("isLogin", "no");
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
			session.setAttribute(Member.SESSION_KEY_EMAIL, currentMember.getEmail());
			session.setAttribute(Member.SESSION_KEY_NO, currentMember.getMember_no());
			session.setAttribute(Member.SESSION_KEY_NICKNAME, currentMember.getNickname());
			res.put("result", CODE_LOGIN_SUCCESS);
			res.put("nickname", currentMember.getNickname());
			res.put("email", currentMember.getEmail());
		} else {
			res.put("result", CODE_LOGIN_FAIL);
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/member/myinfo", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getMyInfo(HttpSession session) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		String memberNo = String.valueOf(session.getAttribute(Member.SESSION_KEY_NO));
		if(memberNo == null || "null".equals(memberNo)) {
			res.put("error", "로그인을 하셔야합니다.");
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
		
		Member currentMember = memberService
				.getMemberFromNo(Integer.parseInt(memberNo));
		if(currentMember == null) {
			res.put("result", "error");
		} else {
			res.put("result", "success");
			res.put("info", currentMember);
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/member/logout", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
		session.removeAttribute(Member.SESSION_KEY_EMAIL);
		session.removeAttribute(Member.SESSION_KEY_NO);
		session.removeAttribute(Member.SESSION_KEY_NICKNAME);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/member/check/id", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> duplicate(Member member) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		if(member.getEmail() == null) {
			res.put("error", "Email is Required");
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
		
		if(memberService.isEmailDuplicate(member)) {
			res.put("result", CODE_DUPLECATE_EMAIL);
		} else {
			res.put("result", CODE_USABLE_EMAIL);
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/api/member/modifyMyinfo", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> PasswordUpdate(Member member, 
												@RequestParam(name="newPw", required=true)String newPw, 
												HttpSession session) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		if(member.getPw() == null){
			res.put("error", "값을 입력하세요");
		} else {
			String email = String.valueOf(session.getAttribute(Member.SESSION_KEY_EMAIL));
			member.setEmail(email);
			memberService.memberPasswordUpdate(member, newPw);
			res.put("result", "success");
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}