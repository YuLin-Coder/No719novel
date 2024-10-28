package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Bookshelf extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -9064882563741796198L;
	private int bookshelf_id; // 
	private int user_id; // 
	private String bookshelf_name; // 

	private String ids;
	private String random;

	public void setBookshelf_id(int bookshelf_id){
		this.bookshelf_id=bookshelf_id;
	}

	public int getBookshelf_id(){
		return bookshelf_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setBookshelf_name(String bookshelf_name){
		this.bookshelf_name=bookshelf_name;
	}

	public String getBookshelf_name(){
		return bookshelf_name;
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

}
