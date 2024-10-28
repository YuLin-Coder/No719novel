package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Info extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -674161960515333295L;
	private int info_id; // 
	private int tbook_id; // 
	private String info_title; // 
	private String info_content; //
	private String info_date; // 
	private int info_words; //  

	private String tbook_name; //
	private int pre_id; // 
	private int next_id; // 
	private String random; // 
	private String ids; // 

	public void setInfo_id(int info_id){
		this.info_id=info_id;
	}

	public int getInfo_id(){
		return info_id;
	}

	public void setInfo_title(String info_title){
		this.info_title=info_title;
	}

	public String getInfo_title(){
		return info_title;
	}

	public void setInfo_content(String info_content){
		this.info_content=info_content;
	}

	public String getInfo_contentShow(){
		if (!StringUtil.isEmptyString(info_content)) {
			return Transcode.htmlDiscode(info_content);
		}
		return info_content;
	}
	
	public String getInfo_content(){
		return info_content;
	}

	public void setInfo_date(String info_date){
		this.info_date=info_date;
	}

	public String getInfo_date(){
		return info_date;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getInfo_words() {
		return info_words;
	}

	public void setInfo_words(int info_words) {
		this.info_words = info_words;
	}

	public int getTbook_id() {
		return tbook_id;
	}

	public void setTbook_id(int tbook_id) {
		this.tbook_id = tbook_id;
	}

	public String getTbook_name() {
		return tbook_name;
	}

	public void setTbook_name(String tbook_name) {
		this.tbook_name = tbook_name;
	}

	public int getPre_id() {
		return pre_id;
	}

	public void setPre_id(int pre_id) {
		this.pre_id = pre_id;
	}

	public int getNext_id() {
		return next_id;
	}

	public void setNext_id(int next_id) {
		this.next_id = next_id;
	}

}
