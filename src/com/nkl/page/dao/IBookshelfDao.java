package com.nkl.page.dao;

import java.util.List;
import com.nkl.page.domain.Bookshelf;

public interface IBookshelfDao {

	public abstract int addBookshelf(Bookshelf bookshelf);

	public abstract int delBookshelf(String bookshelf_id);

	public abstract int delBookshelfs(String[] bookshelf_ids);

	public abstract int updateBookshelf(Bookshelf bookshelf);

	public abstract Bookshelf getBookshelf(Bookshelf bookshelf);

	public abstract List<Bookshelf>  listBookshelfs(Bookshelf bookshelf);

	public abstract int listBookshelfsCount(Bookshelf bookshelf);

}
