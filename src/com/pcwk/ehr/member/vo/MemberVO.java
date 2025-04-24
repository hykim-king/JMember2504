package com.pcwk.ehr.member.vo;

import com.pcwk.ehr.cmn.DTO;

public class MemberVO  extends DTO {
	
	private String	memberId   ;//회원ID		
	private String	name       ;//이름			
	private String	password   ;//비밀번호		
	private String	email      ;//이메일		
	private int		loginCount ;//로그인 횟수	
	private String	regDt      ;//가입일		
	private String	role       ;//권한	
	
	/**
	 * 
	 */
	public MemberVO() {
		super();
	}

	/**
	 * @param memberId
	 * @param name
	 * @param password
	 * @param email
	 * @param loginCount
	 * @param regDt
	 * @param role
	 */
	public MemberVO(String memberId, String name, String password, String email, int loginCount, String regDt,
			String role) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.password = password;
		this.email = email;
		this.loginCount = loginCount;
		this.regDt = regDt;
		this.role = role;
	}
	
	public String voToString() {
		return String.format("%s,%s,%s,%s,%d,%s,%s%n", memberId
				                       ,name
				                       ,password
				                       ,email
				                       ,loginCount
				                       ,regDt
				                       ,role
				                       );
	}
	
	public String stringToVO() {
		return String.format("%-10s %-10s %-10s %-20s %-9d %-15s %-10s%n", memberId
                ,name
                ,password
                ,email
                ,loginCount
                ,regDt
                ,role
                );
	}
	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the loginCount
	 */
	public int getLoginCount() {
		return loginCount;
	}

	/**
	 * @param loginCount the loginCount to set
	 */
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	/**
	 * @return the regDt
	 */
	public String getRegDt() {
		return regDt;
	}

	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", loginCount=" + loginCount + ", regDt=" + regDt + ", role=" + role + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
	
	
}
