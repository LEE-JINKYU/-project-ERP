package org.erp.model;

public class MemberVO {
	
	private String empno;
	
	private String password;
	
	private String name;
	
	private String hiredate;
	
	private String phone;
	
	private String gender;
	
	private String ranking;
	
	private String addr;
	
	private String birth;

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "MemberVO [empno=" + empno + ", password=" + password + ", name=" + name + ", hiredate=" + hiredate
				+ ", phone=" + phone + ", gender=" + gender + ", ranking=" + ranking + ", addr=" + addr + ", birth="
				+ birth + "]";
	}
	
}
