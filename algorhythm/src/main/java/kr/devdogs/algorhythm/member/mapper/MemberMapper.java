package kr.devdogs.algorhythm.member.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import kr.devdogs.algorhythm.member.dto.Member;

@Mapper
public interface MemberMapper {
	// 회원가입
	@Insert("INSERT INTO member(email, pw, nickname, dt) VALUES(#{email}, #{pw}, #{nickname}, now())")
	public int memberJoin(Member member);
}
