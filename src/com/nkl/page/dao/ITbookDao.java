package com.nkl.page.dao;

import java.util.List;
import com.nkl.page.domain.Tbook;

public interface ITbookDao {

	public abstract int addTbook(Tbook tbook);

	public abstract int delTbook(String tbook_id);

	public abstract int delTbooks(String[] tbook_ids);

	public abstract int updateTbook(Tbook tbook);

	public abstract Tbook getTbook(Tbook tbook);

	public abstract List<Tbook>  listTbooks(Tbook tbook);

	public abstract int listTbooksCount(Tbook tbook);

	public abstract int updateTbookClick(Tbook tbook);

}
