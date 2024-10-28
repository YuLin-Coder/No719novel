package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

@SuppressWarnings("serial")
public class Collect extends BaseDomain {
	private int collect_id; // 
	private int user_id; // 
	private int bookshelf_id; // 
	private int tbook_id; // 
	private String collect_date; // 
	private int collect_tip; // 1:有提示

	private String bookshelf_name; // 
	private String tbook_name; // 
	private String ids;
	private String random;

	public void setCollect_id(int collect_id){
		this.collect_id=collect_id;
	}

	public int getCollect_id(){
		return collect_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setBookshelf_id(int bookshelf_id){
		this.bookshelf_id=bookshelf_id;
	}

	public int getBookshelf_id(){
		return bookshelf_id;
	}

	public void setTbook_id(int tbook_id){
		this.tbook_id=tbook_id;
	}

	public int getTbook_id(){
		return tbook_id;
	}

	public void setCollect_date(String collect_date){
		this.collect_date=collect_date;
	}

	public String getCollect_date(){
		return collect_date;
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

	public String getBookshelf_name() {
		return bookshelf_name;
	}

	public void setBookshelf_name(String bookshelf_name) {
		this.bookshelf_name = bookshelf_name;
	}

	public int getCollect_tip() {
		return collect_tip;
	}

	public void setCollect_tip(int collect_tip) {
		this.collect_tip = collect_tip;
	}

}
