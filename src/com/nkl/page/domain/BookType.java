package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class BookType extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1770185824735782580L;
	private int book_type_id; // 
	private String book_type_name; // 

	private String ids;

	public int getBook_type_id() {
		return book_type_id;
	}

	public void setBook_type_id(int book_type_id) {
		this.book_type_id = book_type_id;
	}

	public String getBook_type_name() {
		return book_type_name;
	}

	public void setBook_type_name(String book_type_name) {
		this.book_type_name = book_type_name;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
