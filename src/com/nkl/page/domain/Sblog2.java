package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Sblog2 extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -5153140362380984117L;
	private int sblog_id; // 
	private int tbook_id; // 
	private int user_id; // 
	private String nick_name; // 
	private String nick_name2; // 
	private String sblog_pic; // 
	private String sblog_content; // 
	private String sblog_date; // 

	private String tbook_name; // 
	private String ids;
	private String random;
	
	public String getSblog_contentShow(){
		if (!StringUtil.isEmptyString(sblog_content)) {
			return Transcode.htmlDiscode(sblog_content);
		}
		return sblog_content;
	}

	public void setSblog_id(int sblog_id){
		this.sblog_id=sblog_id;
	}

	public int getSblog_id(){
		return sblog_id;
	}

	public void setTbook_id(int tbook_id){
		this.tbook_id=tbook_id;
	}

	public int getTbook_id(){
		return tbook_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setNick_name(String nick_name){
		this.nick_name=nick_name;
	}

	public String getNick_name(){
		return nick_name;
	}

	public void setSblog_pic(String sblog_pic){
		this.sblog_pic=sblog_pic;
	}

	public String getSblog_pic(){
		return sblog_pic;
	}

	public void setSblog_content(String sblog_content){
		this.sblog_content=sblog_content;
	}

	public String getSblog_content(){
		return sblog_content;
	}

	public void setSblog_date(String sblog_date){
		this.sblog_date=sblog_date;
	}

	public String getSblog_date(){
		return sblog_date;
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

	public String getNick_name2() {
		return nick_name2;
	}

	public void setNick_name2(String nick_name2) {
		this.nick_name2 = nick_name2;
	}

	public String getTbook_name() {
		return tbook_name;
	}

	public void setTbook_name(String tbook_name) {
		this.tbook_name = tbook_name;
	}

}
