package com.nkl.page.dao;

import java.util.List;
import com.nkl.page.domain.Sblog2;

public interface ISblog2Dao {

	public abstract int addSblog2(Sblog2 sblog2);

	public abstract int delSblog2(String sblog_id);

	public abstract int delSblog2s(String[] sblog_ids);

	public abstract int updateSblog2(Sblog2 sblog2);

	public abstract Sblog2 getSblog2(Sblog2 sblog2);

	public abstract List<Sblog2>  listSblog2s(Sblog2 sblog2);

	public abstract int listSblog2sCount(Sblog2 sblog2);

}
