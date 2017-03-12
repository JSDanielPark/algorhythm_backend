package kr.devdogs.algorhythm.member.service;

import java.util.List;
import java.util.Map;

import kr.devdogs.algorhythm.member.dto.Member;

public interface MemberService {
	public boolean memberJoin(Member member);
	
	public Member memberLogin(Member member);
	
	public boolean isEmailDuplicate(Member member);
	
	public boolean memberPasswordUpdate(Member member, String newPw);
	
}
