package org.erp.model;

public class CartDTO {

	private int cartid;

	private String empno;

	private String productid;

	private String companyname;

	private int productcount;

	private String productcnt;
	
	private int confirm;

	// product

	private String productname;

	private int price;

	private int totalprice;
	
	private int sumcount;

	// member

	private String name;

	//
	
	public int getSumcount() {
		return sumcount;
	}

	public void setSumcount(int sumcount) {
		this.sumcount = sumcount;
	}
	
	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductcnt() {
		return productcnt;
	}

	public void setProductcnt(String productcnt) {
		this.productcnt = productcnt;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public int getProductcount() {
		return productcount;
	}

	public void setProductcount(int productcount) {
		this.productcount = productcount;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void initSaleTotal() {
		this.totalprice = this.price * this.productcount;
	}

	@Override
	public String toString() {
		return "CartDTO [cartid=" + cartid + ", empno=" + empno + ", productid=" + productid + ", companyname="
				+ companyname + ", productcount=" + productcount + ", productcnt=" + productcnt + ", confirm=" + confirm
				+ ", productname=" + productname + ", price=" + price + ", totalprice=" + totalprice + ", sumcount="
				+ sumcount + ", name=" + name + "]";
	}

}
