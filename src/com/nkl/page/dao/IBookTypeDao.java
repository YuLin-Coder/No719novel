package com.nkl.page.dao;

import java.util.List;
import com.nkl.page.domain.BookType;

public interface IBookTypeDao {

	public abstract int addBookType(BookType bookType);

	public abstract int delBookType(String book_type_id);

	public abstract int delBookTypes(String[] book_type_ids);

	public abstract int updateBookType(BookType bookType);

	public abstract BookType getBookType(BookType bookType);

	public abstract List<BookType>  listBookTypes(BookType bookType);

	public abstract int listBookTypesCount(BookType bookType);

}
