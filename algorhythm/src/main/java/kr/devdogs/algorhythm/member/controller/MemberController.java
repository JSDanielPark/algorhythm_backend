package kr.devdogs.algorhythm.member.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
	
}