package kr.devdogs.algorhythm.member.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.devdogs.algorhythm.member.dto.Member;
import kr.devdogs.algorhythm.member.mapper.MemberMapper;
import kr.devdogs.algorhythm.utils.encrypt.EncryptFailException;
import kr.devdogs.algorhythm.utils.encrypt.EncryptUtil;

/**
 * 
 * @author Daniel
 * 회원관련 로직구현
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired private MemberMapper memberMapper;
	@Autowired private EncryptUtil sha256Util;
	private Logger logger = LoggerFactory.getLogger(getClass());
	

	@Override
	public boolean memberJoin(Member member) {
		String pw = member.getPw();
		
		try {
			pw = sha256Util.encoding(member.getPw());
		} catch(EncryptFailException e) {
			logger.error("Password Enctypt Fail - Password : " + pw);
		}
		member.setPw(pw);
		
		int insertedLine = memberMapper.memberJoin(member);
		if(insertedLine == 1) {
			return true;
		} else {
			logger.error("Member Join Fail : " + member.toString());
			return false;
		}
	}
	
	@Override
	public Member memberLogin(Member member) {
		String pw = member.getPw();
		
		try {
			pw = sha256Util.encoding(member.getPw());
		} catch(EncryptFailException e) {
			logger.error("Password Enctypt Fail - Password : " + pw);
		}
		member.setPw(pw);
		
		Member currentMember = memberMapper.memberLogin(member);
		return currentMember;
	}
	
	@Override
	public boolean memberDuplicate(Member member) {
		String email = member.getEmail();
		
		member.setEmail(email);
		
		String SelectString = memberMapper.memberDuplicate(member);
		
		if(SelectString == null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean memberPasswordUpdate(Member member, String newPw) {
		String password = null;
		try {
			 password = sha256Util.encoding(member.getPw());
			 newPw = sha256Util.encoding(newPw);
		} catch(EncryptFailException e) {
			logger.error("Password Enctypt Fail - Password : " + password);
		}
		
		member.setPw(password);
		Member newMemberInfo = new Member();
		newMemberInfo.setPw(newPw);

		System.out.println(member.getEmail());
		String originPw = memberMapper.getMemberPassword(member);
		
		System.out.println(originPw);
		System.out.println(password);
		if(password.equals(originPw)){
			memberMapper.memberPasswordUpdate(newMemberInfo);
			return true;
		}else
			return false;
	}

}
