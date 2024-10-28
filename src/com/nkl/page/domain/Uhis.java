package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

@SuppressWarnings("serial")
public class Uhis extends BaseDomain {
	private int uhis_id; // 
	private int user_id; // 
	private int info_id; // 
	private String uhis_date; // 

	private String tbook_name; // 
	private String info_title; // 
	private String ids;
	private String random;

	public void setUhis_id(int uhis_id){
		this.uhis_id=uhis_id;
	}

	public int getUhis_id(){
		return uhis_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setUhis_date(String uhis_date){
		this.uhis_date=uhis_date;
	}

	public String getUhis_date(){
		return uhis_date;
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

	public String getTbook_name() {
		return tbook_name;
	}

	public void setTbook_name(String tbook_name) {
		this.tbook_name = tbook_name;
	}

	public int getInfo_id() {
		return info_id;
	}

	public String getInfo_title() {
		return info_title;
	}

	public void setInfo_id(int info_id) {
		this.info_id = info_id;
	}

	public void setInfo_title(String info_title) {
		this.info_title = info_title;
	}

}
