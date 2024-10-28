package com.nkl.page.dao;

import java.util.List;
import com.nkl.page.domain.Uhis;

public interface IUhisDao {

	public abstract int addUhis(Uhis uhis);

	public abstract int delUhis(String uhis_id);

	public abstract int delUhiss(String[] uhis_ids);

	public abstract int updateUhis(Uhis uhis);

	public abstract Uhis getUhis(Uhis uhis);

	public abstract List<Uhis>  listUhiss(Uhis uhis);

	public abstract int listUhissCount(Uhis uhis);

}
