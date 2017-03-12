package kr.devdogs.algorhythm.member.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.devdogs.algorhythm.member.dto.Member;

@Mapper
public interface MemberMapper {
	// 회원가입
	@Insert("INSERT INTO member(email, pw, nickname, dt) VALUES(#{email}, #{pw}, #{nickname}, now())")
	public int memberJoin(Member member);
	
	//로그인
	@Select("SELECT member_no, email, nickname FROM member where email = #{email} AND pw=#{pw}")	
	public Member memberLogin(Member member);
	
	//중복체크
	@Select("SELECT email FROM member where email = #{email}")	
	public String emailDuplicate(Member member);
	
	//pw가져오기 
	@Select("SELECT pw From member where email = #{email}")
	public String getMemberPassword(Member member);
	
	//비밀번호 변경
	@Update("UPDATE member SET pw = #{pw} where email = #{email}")
	public String memberPasswordUpdate(Member pw);
	
}
