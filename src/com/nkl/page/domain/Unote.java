package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

@SuppressWarnings("serial")
public class Unote extends BaseDomain {
	private int unote_id; // 
	private int user_id; // 
	private int info_id; // 
	private String unote_content; // 
	private String unote_date; // 

	private String tbook_name; // 
	private String info_title; // 
	private String ids;
	private String random;

	public void setUnote_id(int unote_id){
		this.unote_id=unote_id;
	}

	public int getUnote_id(){
		return unote_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setUnote_date(String unote_date){
		this.unote_date=unote_date;
	}

	public String getUnote_date(){
		return unote_date;
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

	public String getUnote_content() {
		return unote_content;
	}

	public void setUnote_content(String unote_content) {
		this.unote_content = unote_content;
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
