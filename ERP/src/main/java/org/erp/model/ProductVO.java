package org.erp.model;

public class ProductVO {

	private String companyid;
	
	private String companyname;
	
	private String productid;
	
	private String productname;
	
	private String productcnt;
	
	private String price;
	
	
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

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProductcnt() {
		return productcnt;
	}

	public void setProductcnt(String productcnt) {
		this.productcnt = productcnt;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductVO [companyid=" + companyid + ", companyname=" + companyname + ", productid=" + productid
				+ ", productname=" + productname + ", productcnt=" + productcnt + ", price=" + price + "]";
	}
	
	
}
