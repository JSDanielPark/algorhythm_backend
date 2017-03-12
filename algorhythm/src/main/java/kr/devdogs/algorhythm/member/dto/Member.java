package kr.devdogs.algorhythm.member.dto;

/**
 * 
 * @author Daniel
 * Member Data Tranfer Object
 * 
 */
public class Member {
	public static final String SESSION_KEY_EMAIL = "EMAIL";
	public static final String SESSION_KEY_NICKNAME = "NICKNAME";
	public static final String SESSION_KEY_NO = "MEMBER_NO";
	
	private int member_no;
	private String email;
	private String pw;
	private String nickname;
	private String dt;
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	@Override
	public String toString() {
		return "Member [member_no=" + member_no + ", email=" + email + ", pw=" + pw + ", nickname=" + nickname + ", dt="
				+ dt + "]";
	}
	
}
