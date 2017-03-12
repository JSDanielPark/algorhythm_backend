package kr.devdogs.algorhythm.member.service;

import java.util.List;

import kr.devdogs.algorhythm.member.dto.Member;

public interface MemberService {
	public boolean memberJoin(Member member);
	
	public int memberLogin(Member member);
	
	public boolean memberDuplicate(Member member);
}
