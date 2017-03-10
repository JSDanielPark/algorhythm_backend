package kr.devdogs.algorhythm.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.devdogs.algorhythm.member.dto.Member;
import kr.devdogs.algorhythm.member.mapper.MemberMapper;

/**
 * 
 * @author Daniel
 * 회원관련 로직구현
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired private MemberMapper memberMapper;
	
	public List<Member> memberSample(Member member) {
		return null;
	}
	
}
