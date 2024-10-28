package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Upay extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1910280440840316401L;
	private int upay_id; // 
	private String upay_no; // 
	private int user_id; // 
	private String upay_date; // 
	private double upay_price; // 

	private String real_name; // 
	private String ids;
	private String random;

	public void setUpay_id(int upay_id){
		this.upay_id=upay_id;
	}

	public int getUpay_id(){
		return upay_id;
	}

	public void setUpay_no(String upay_no){
		this.upay_no=upay_no;
	}

	public String getUpay_no(){
		return upay_no;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setUpay_date(String upay_date){
		this.upay_date=upay_date;
	}

	public String getUpay_date(){
		return upay_date;
	}

	public void setUpay_price(double upay_price){
		this.upay_price=upay_price;
	}

	public double getUpay_price(){
		return upay_price;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

}
