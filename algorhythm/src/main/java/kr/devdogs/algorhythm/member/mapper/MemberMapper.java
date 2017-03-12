package kr.devdogs.algorhythm.member.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.devdogs.algorhythm.member.dto.Member;

@Mapper
public interface MemberMapper {
	// 회원가입
	@Insert("INSERT INTO member(email, pw, nickname, dt) VALUES(#{email}, #{pw}, #{nickname}, now())")
	public int memberJoin(Member member);
	
	//로그인
	@Select("SELECT count(*) FROM member where email = #{email} AND #{pw}")	
	public int memberLogin(Member member);
	
	//중복체크
	@Select("SELECT email FROM member where email = #{email}")	
	public String memberDuplicate(Member member);
}
