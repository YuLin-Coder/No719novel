package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.DateUtil;

public class User extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 935450826788987376L;
	private int user_id; // 
	private String user_name; // 
	private String user_pass; // 
	private String real_name; // 
	private String nick_name; // 
	private String user_address; // 
	private String user_mail; // 
	private String user_phone; // 
	private double user_money; // 
	private String user_photo; // 
	private String reg_date; // 
	private int user_kind; // 1：普通 2：会员
	private String invalid_date; 
	private int user_type; // 1：注册用户 2：系统管理员 
	
	private String ids; //  
	private String random;
	private String user_types;
	private String user_passOld; // 
	
	public int getLeftDays(){
		int days = 0;
		if (user_kind==2) {
			String dateNow = DateUtil.getCurDate();
			days = DateUtil.getDaysBetween(DateUtil.getDate(dateNow), DateUtil.getDate(invalid_date));
			if (days < 0) {
				days = 0;
			}
			return days;
		}
		return 0;
	}
	
	public String getUser_kindDesc() {
		switch (user_kind) {
		case 1:
			return "普通用户"; 
		case 2:
			return "会员用户";
		default:
			return "";
		}
	}
	
	public String getUser_typeDesc() {
		switch (user_type) {
		case 1:
			return "注册用户"; 
		case 2:
			return "系统管理员";
		default:
			return "";
		}
	}
	
	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setUser_name(String user_name){
		this.user_name=user_name;
	}

	public String getUser_name(){
		return user_name;
	}

	public void setUser_pass(String user_pass){
		this.user_pass=user_pass;
	}

	public String getUser_pass(){
		return user_pass;
	}

	public void setUser_mail(String user_mail){
		this.user_mail=user_mail;
	}

	public String getUser_mail(){
		return user_mail;
	}

	public void setReal_name(String real_name){
		this.real_name=real_name;
	}

	public String getReal_name(){
		return real_name;
	}

	public void setReg_date(String reg_date){
		this.reg_date=reg_date;
	}

	public String getReg_date(){
		return reg_date;
	}

	public void setUser_type(int user_type){
		this.user_type=user_type;
	}

	public int getUser_type(){
		return user_type;
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

	public String getUser_types() {
		return user_types;
	}

	public void setUser_types(String user_types) {
		this.user_types = user_types;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public double getUser_money() {
		return user_money;
	}

	public String getUser_photo() {
		return user_photo;
	}

	public void setUser_money(double user_money) {
		this.user_money = user_money;
	}

	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}

	public int getUser_kind() {
		return user_kind;
	}

	public String getInvalid_date() {
		return invalid_date;
	}

	public void setUser_kind(int user_kind) {
		this.user_kind = user_kind;
	}

	public void setInvalid_date(String invalid_date) {
		this.invalid_date = invalid_date;
	}

	public String getUser_passOld() {
		return user_passOld;
	}

	public void setUser_passOld(String user_passOld) {
		this.user_passOld = user_passOld;
	}


}
