package com.nkl.page.dao;

import java.util.List;
import com.nkl.page.domain.Upay;

public interface IUpayDao {

	public abstract int addUpay(Upay upay);

	public abstract int delUpay(String upay_id);

	public abstract int delUpays(String[] upay_ids);

	public abstract int updateUpay(Upay upay);

	public abstract Upay getUpay(Upay upay);

	public abstract List<Upay>  listUpays(Upay upay);

	public abstract int listUpaysCount(Upay upay);

}
