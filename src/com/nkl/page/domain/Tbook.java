package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Tbook extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 8782325810716469437L;
	private int tbook_id; // 
	private int user_id; // 
	private int book_type_id; // 
	private String tbook_name; // 
	private String tbook_pic; // 
	private String tbook_isbn; // 
	private String tbook_author; // 
	private String tbook_desc; // 
	private String tbook_date; // 
	private int tbook_click; // 
	private double tbook_words; // 单位：万
	private int tbook_flag; // 1:连载中 2:已完本

	private String real_name; // 
	private String book_type_name; // 
	private int top_flag; 
	private double evaluate_score; // 
	private int collect_id; // 
	private String ids;
	private String random;
	
	public String getTbook_flagDesc() {
		switch (tbook_flag) {
		case 1:
			return "连载中";
		case 2:
			return "已完本";
		default:
			return "";
		}
	}

	public void setTbook_id(int tbook_id){
		this.tbook_id=tbook_id;
	}

	public int getTbook_id(){
		return tbook_id;
	}

	public void setBook_type_id(int book_type_id){
		this.book_type_id=book_type_id;
	}

	public int getBook_type_id(){
		return book_type_id;
	}

	public void setTbook_name(String tbook_name){
		this.tbook_name=tbook_name;
	}

	public String getTbook_name(){
		return tbook_name;
	}

	public void setTbook_pic(String tbook_pic){
		this.tbook_pic=tbook_pic;
	}

	public String getTbook_pic(){
		return tbook_pic;
	}

	public void setTbook_isbn(String tbook_isbn){
		this.tbook_isbn=tbook_isbn;
	}

	public String getTbook_isbn(){
		return tbook_isbn;
	}

	public void setTbook_author(String tbook_author){
		this.tbook_author=tbook_author;
	}

	public String getTbook_author(){
		return tbook_author;
	}

	public void setTbook_desc(String tbook_desc){
		this.tbook_desc=tbook_desc;
	}

	public String getTbook_desc(){
		return tbook_desc;
	}
	
	public String getTbook_descShow(){
		if (!StringUtil.isEmptyString(tbook_desc)) {
			return Transcode.htmlDiscode(tbook_desc);
		}
		return tbook_desc;
	}

	public void setTbook_date(String tbook_date){
		this.tbook_date=tbook_date;
	}

	public String getTbook_date(){
		return tbook_date;
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

	public String getBook_type_name() {
		return book_type_name;
	}

	public void setBook_type_name(String book_type_name) {
		this.book_type_name = book_type_name;
	}

	public int getTbook_flag() {
		return tbook_flag;
	}

	public void setTbook_flag(int tbook_flag) {
		this.tbook_flag = tbook_flag;
	}

	public int getUser_id() {
		return user_id;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public int getTbook_click() {
		return tbook_click;
	}

	public double getTbook_words() {
		return tbook_words;
	}

	public void setTbook_click(int tbook_click) {
		this.tbook_click = tbook_click;
	}

	public void setTbook_words(double tbook_words) {
		this.tbook_words = tbook_words;
	}

	public int getTop_flag() {
		return top_flag;
	}

	public void setTop_flag(int top_flag) {
		this.top_flag = top_flag;
	}

	public double getEvaluate_score() {
		return evaluate_score;
	}

	public void setEvaluate_score(double evaluate_score) {
		this.evaluate_score = evaluate_score;
	}

	public int getCollect_id() {
		return collect_id;
	}

	public void setCollect_id(int collect_id) {
		this.collect_id = collect_id;
	}

}
