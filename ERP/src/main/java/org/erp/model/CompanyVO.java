package org.erp.model;

public class CompanyVO {

	private String companyid;
	
	private String companyname;
	
	private String companymanager;
	
	private String companytype;
	
	private String companyphone;
	
	private String startdate;
	
	private String companyaddr;
	
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanymanager() {
		return companymanager;
	}
	public void setCompanymanager(String companymanager) {
		this.companymanager = companymanager;
	}
	public String getCompanytype() {
		return companytype;
	}
	public void setCompanytype(String companytype) {
		this.companytype = companytype;
	}
	public String getCompanyphone() {
		return companyphone;
	}
	public void setCompanyphone(String companyphone) {
		this.companyphone = companyphone;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getCompanyaddr() {
		return companyaddr;
	}
	public void setCompanyaddr(String companyaddr) {
		this.companyaddr = companyaddr;
	}
	
	@Override
	public String toString() {
		return "CompanyVO [companyid=" + companyid + ", companyname=" + companyname + ", companymanager="
				+ companymanager + ", companytype=" + companytype + ", companyphone=" + companyphone + ", startdate="
				+ startdate + ", companyaddr=" + companyaddr + "]";
	}
	
}
