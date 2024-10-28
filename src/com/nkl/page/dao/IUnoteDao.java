package com.nkl.page.dao;

import java.util.List;
import com.nkl.page.domain.Unote;

public interface IUnoteDao {

	public abstract int addUnote(Unote unote);

	public abstract int delUnote(String unote_id);

	public abstract int delUnotes(String[] unote_ids);

	public abstract int updateUnote(Unote unote);

	public abstract Unote getUnote(Unote unote);

	public abstract List<Unote>  listUnotes(Unote unote);

	public abstract int listUnotesCount(Unote unote);

}
