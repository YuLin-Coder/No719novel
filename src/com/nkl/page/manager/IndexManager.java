package com.nkl.page.manager;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkl.common.util.DateUtil;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;
import com.nkl.page.dao.IBookTypeDao;
import com.nkl.page.dao.IBookshelfDao;
import com.nkl.page.dao.ICollectDao;
import com.nkl.page.dao.IEvaluateDao;
import com.nkl.page.dao.IInfoDao;
import com.nkl.page.dao.ISblog2Dao;
import com.nkl.page.dao.ITbookDao;
import com.nkl.page.dao.IUhisDao;
import com.nkl.page.dao.IUnoteDao;
import com.nkl.page.dao.IUpayDao;
import com.nkl.page.dao.IUserDao;
import com.nkl.page.domain.BookType;
import com.nkl.page.domain.Bookshelf;
import com.nkl.page.domain.Collect;
import com.nkl.page.domain.Evaluate;
import com.nkl.page.domain.Info;
import com.nkl.page.domain.Sblog2;
import com.nkl.page.domain.Tbook;
import com.nkl.page.domain.Uhis;
import com.nkl.page.domain.Unote;
import com.nkl.page.domain.Upay;
import com.nkl.page.domain.User;

@Service
public class IndexManager {

	@Autowired
	IUserDao userDao;
	@Autowired
	IBookTypeDao bookTypeDao;
	@Autowired
	ITbookDao tbookDao;
	@Autowired
	ISblog2Dao sblog2Dao;
	@Autowired
	IInfoDao infoDao;
	@Autowired
	ICollectDao collectDao;
	@Autowired
	IEvaluateDao evaluateDao;
	@Autowired
	IUpayDao upayDao;
	@Autowired
	IUhisDao uhisDao;
	@Autowired
	IUnoteDao unoteDao;
	@Autowired
	IBookshelfDao bookshelfDao;
	
	/**
	 * @Title: listUpays
	 * @Description: 支付记录查询
	 * @param upay
	 * @return List<Upay>
	 */
	public List<Upay> listUpays(Upay upay, int[] sum) {
		
		if (sum != null) {
			sum[0] = upayDao.listUpaysCount(upay);
		}
		List<Upay> upays = upayDao.listUpays(upay);

		
		return upays;
	}
	
	/**
	 * @Title: addUpay
	 * @Description: 新增购买记录
	 * @param sblog
	 * @return void
	 */
	public void addUpay(Upay upay,HttpSession httpSession) {
		//订单号
		upay.setUpay_no(DateUtil.dateToDateString(new Date(), "yyyyMMddHHmmssSSS"));
		//购买时间
		upay.setUpay_date(DateUtil.getCurDateTime());
		upayDao.addUpay(upay);
		
		//更新用户身份和有效期
		User user = new User();
		user.setUser_id(upay.getUser_id());
		user = userDao.getUser(user);
		user.setUser_kind(2);
		String invalid_date = user.getInvalid_date();
		String now_date = DateUtil.getCurDate();
		
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		if (invalid_date==null || invalid_date.compareTo(now_date) < 0) {
			cal.setTime(DateUtil.getDate(now_date));
		}else{
			cal.setTime(DateUtil.getDate(invalid_date));
		}
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		String invalid_date_new = DateUtil.dateToDateString(cal.getTime(), "yyyy-MM-dd");
		user.setInvalid_date(invalid_date_new);
		userDao.updateUser(user);
		
		httpSession.setAttribute("userFront", user);
	}
	
	/**
	 * @Title: getUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User  getUser(User user){
		
		User _user = userDao.getUser(user);
		
		return _user;
	}
	 
	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void  updateUser(User user){
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			//user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user);
		userDao.updateUserMoney(user);
		
	}
	
	/**
	 * @Title: listBookTypes
	 * @Description: 图书类型查询
	 * @param bookType
	 * @return List<BookType>
	 */
	public List<BookType> listBookTypes(BookType bookType, int[] sum) {
		
		if (sum != null) {
			sum[0] = bookTypeDao.listBookTypesCount(bookType);
		}
		List<BookType> bookTypes = bookTypeDao.listBookTypes(bookType);

		
		return bookTypes;
	}

	/**
	 * @Title: listTbooks
	 * @Description: 图书查询
	 * @param tbook
	 * @return List<Tbook>
	 */
	public List<Tbook> listTbooks(Tbook tbook, int[] sum) {
		
		if (sum != null) {
			sum[0] = tbookDao.listTbooksCount(tbook);
		}
		List<Tbook> tbooks = tbookDao.listTbooks(tbook);

		
		return tbooks;
	}

	/**
	 * @Title: queryTbook
	 * @Description: 图书查询
	 * @param tbook
	 * @return Tbook
	 */
	public Tbook queryTbook(Tbook tbook) {
		
		Tbook _tbook = tbookDao.getTbook(tbook);
		
		return _tbook;
	}
	
	public void updateTbookClick(Tbook tbook) {
		
		tbook.setTbook_click(tbook.getTbook_click()+1);
		tbookDao.updateTbookClick(tbook);
		
	}
	
	/**
	 * @Title: listSblog2s
	 * @Description: 评论信息查询
	 * @param sblog
	 * @return List<Sblog>
	 */
	public List<Sblog2> listSblog2s(Sblog2 sblog, int[] sum) {
		
		if (sum != null) {
			sum[0] = sblog2Dao.listSblog2sCount(sblog);
		}
		List<Sblog2> sblogs = sblog2Dao.listSblog2s(sblog);

		
		return sblogs;
	}

	/**
	 * @Title: addSblog2
	 * @Description: 新增评论信息
	 * @param sblog
	 * @return void
	 */
	public void addSblog2(Sblog2 sblog) {
		
		if (!StringUtil.isEmptyString(sblog.getSblog_content())) {
			//内容编码
			sblog.setSblog_content(Transcode.htmlEncode(sblog.getSblog_content()));
		}
		//评论时间
		sblog.setSblog_date(DateUtil.dateToDateString(new Date()));
		sblog2Dao.addSblog2(sblog);
		
	}
	
	/**
	 * @Title: delSblog2s
	 * @Description: 删除评论信息
	 * @param sblog
	 * @return void
	 */
	public void  delSblog2s(Sblog2 sblog){
		
		sblog2Dao.delSblog2s(sblog.getIds().split(","));
		
	}
	
	/**
	 * @Title: listInfos
	 * @Description: 图书目录查询
	 * @param info
	 * @return List<Info>
	 */
	public List<Info> listInfos(Info info, int[] sum) {
		if (sum != null) {
			sum[0] = infoDao.listInfosCount(info);
		}
		List<Info> infos = infoDao.listInfos(info);
		return infos;
	}

	/**
	 * @Title: queryInfo
	 * @Description: 图书目录查询
	 * @param info
	 * @return Info
	 */
	public Info queryInfo(Info info) {
		Info _info = infoDao.getInfo(info);
		if (_info!=null) {
			Info itemInfo = new Info();
			itemInfo.setTbook_id(_info.getTbook_id());
			itemInfo.setStart(-1);
			List<Info> infos = infoDao.listInfos(itemInfo);
			for (int i = 0; i < infos.size(); i++) {
				Info item = infos.get(i);
				if (item.getInfo_id()==info.getInfo_id()) {
					if (i>0) {
						_info.setPre_id(infos.get(i-1).getInfo_id());
					}
					if (i<infos.size()-1) {
						_info.setNext_id(infos.get(i+1).getInfo_id());
					}
					break;
				}
			}
		}
		
		return _info;
	}

	/**
	 * @Title: addInfo
	 * @Description: 新增图书目录
	 * @param info
	 * @return void
	 */
	public void addInfo(Info info) {
		
		if (!StringUtil.isEmptyString(info.getInfo_content())) {
			info.setInfo_content(Transcode.htmlEncode(info.getInfo_content()));
		}
		info.setInfo_date(DateUtil.dateToDateString(new Date()));
		infoDao.addInfo(info);
		
	}

	/**
	 * @Title: updateInfo
	 * @Description: 更新图书目录
	 * @param info
	 * @return void
	 */
	public void updateInfo(Info info) {
		
		if (!StringUtil.isEmptyString(info.getInfo_content())) {
			info.setInfo_content(Transcode.htmlEncode(info.getInfo_content()));
		}
		infoDao.updateInfo(info);
		
	}
	
	/**
	 * @Title: delInfos
	 * @Description: 删除图书目录
	 * @param info
	 * @return void
	 */
	public void  delInfos(Info info){
		
		infoDao.delInfos(info.getIds().split(","));
		
	}
	
	/**
	 * @Title: listEvaluates
	 * @Description: 评分信息查询
	 * @param evaluate
	 * @return List<Sblog>
	 */
	public List<Evaluate> listEvaluates(Evaluate evaluate, int[] sum) {
		
		if (sum != null) {
			sum[0] = evaluateDao.listEvaluatesCount(evaluate);
		}
		List<Evaluate> evaluates = evaluateDao.listEvaluates(evaluate);

		
		return evaluates;
	}

	/**
	 * @Title: addEvaluate
	 * @Description: 新增评分信息
	 * @param evaluate
	 * @return void
	 */
	public void addEvaluate(Evaluate evaluate) {
		
		//评分时间
		evaluate.setEvaluate_date(DateUtil.dateToDateString(new Date()));
		evaluateDao.addEvaluate(evaluate);
		
	}
	
	/**
	 * @Title: queryEvaluate
	 * @Description: 评分查询
	 * @param evaluate
	 * @return Evaluate
	 */
	public Evaluate queryEvaluate(Evaluate evaluate) {
		
		Evaluate _evaluate = evaluateDao.getEvaluate(evaluate);
		
		return _evaluate;
	}
	
	/**
	 * @Title: delEvaluates
	 * @Description: 删除评分信息
	 * @param evaluate
	 * @return void
	 */
	public void  delEvaluates(Evaluate evaluate){
		
		evaluateDao.delEvaluates(evaluate.getIds().split(","));
		
	}
	
	/**
	 * @Title: listBookshelfs
	 * @Description: 查询书架信息
	 * @param bookshelf
	 * @return List<Bookshelf>
	 */
	public List<Bookshelf>  listBookshelfs(Bookshelf bookshelf,int[] sum){
		
		if (sum!=null) {
			sum[0] = bookshelfDao.listBookshelfsCount(bookshelf);
		}
		List<Bookshelf> bookshelfs = bookshelfDao.listBookshelfs(bookshelf);
		
		return bookshelfs;
	}
	
	/**
	 * @Title: queryBookshelf
	 * @Description: 查询书架
	 * @param bookshelf
	 * @return Bookshelf
	 */
	public Bookshelf queryBookshelf(Bookshelf bookshelf) {
		
		Bookshelf _bookshelf = bookshelfDao.getBookshelf(bookshelf);
		
		return _bookshelf;
	}
	
	/**
	 * @Title: addBookshelf
	 * @Description: 添加书架
	 * @param bookshelf
	 * @return void
	 */
	public void addBookshelf(Bookshelf bookshelf) {
		
		bookshelfDao.addBookshelf(bookshelf);
		
	}

	/**
	 * @Title: updateBookshelf
	 * @Description: 更新书架信息
	 * @param bookshelf
	 * @return void
	 */
	public void updateBookshelf(Bookshelf bookshelf) {
		
		bookshelfDao.updateBookshelf(bookshelf);
		
	}
	
	/**
	 * @Title: delBookshelfs
	 * @Description: 删除书架信息
	 * @param bookshelf
	 * @return void
	 */
	public void  delBookshelfs(Bookshelf bookshelf){
		
		bookshelfDao.delBookshelfs(bookshelf.getIds().split(","));
		
	}
	
	/**
	 * @Title: listCollects
	 * @Description: 收藏查询
	 * @param collect
	 * @return List<Collect>
	 */
	public List<Collect> listCollects(Collect collect, int[] sum) {
		
		if (sum != null) {
			sum[0] = collectDao.listCollectsCount(collect);
		}
		List<Collect> collects = collectDao.listCollects(collect);
		
		return collects;
	}

	/**
	 * @Title: queryCollect
	 * @Description: 收藏查询
	 * @param collect
	 * @return Collect
	 */
	public Collect queryCollect(Collect collect) {
		
		Collect _collect = collectDao.getCollect(collect);
		
		return _collect;
	}
	
	public void updateCollect(Collect collect) {
		
		collectDao.updateCollect(collect);
		
	}

	/**
	 * @Title: addCollect
	 * @Description: 添加收藏
	 * @param collect
	 * @return void
	 */
	public void addCollect(Collect collect) {
		
		Collect _collect = collectDao.getCollect(collect);
		if (_collect==null) {
			collect.setCollect_date(DateUtil.getCurDate());
			collectDao.addCollect(collect);
		}
		
	}

	/**
	 * @Title: delCollect
	 * @Description: 删除收藏信息
	 * @param collect
	 * @return void
	 */
	public void delCollects(Collect collect) {
		
		if (!StringUtil.isEmptyString(collect.getIds())) {
			collectDao.delCollects(collect.getIds().split(","));
		}else {
			collect = collectDao.getCollect(collect);
			collectDao.delCollect(collect.getCollect_id()+"");
		}
		
	}
	
	/**
	 * @Title: listUhiss
	 * @Description: 阅读历史查询
	 * @param uhis
	 * @return List<Uhis>
	 */
	public List<Uhis> listUhiss(Uhis uhis, int[] sum) {
		
		if (sum != null) {
			sum[0] = uhisDao.listUhissCount(uhis);
		}
		List<Uhis> uhiss = uhisDao.listUhiss(uhis);
		
		return uhiss;
	}

	/**
	 * @Title: queryUhis
	 * @Description: 阅读历史查询
	 * @param uhis
	 * @return Uhis
	 */
	public Uhis queryUhis(Uhis uhis) {
		
		Uhis _uhis = uhisDao.getUhis(uhis);
		
		return _uhis;
	}
	
	public void updateUhis(Uhis uhis) {
		
		uhisDao.updateUhis(uhis);
		
	}

	/**
	 * @Title: addUhis
	 * @Description: 添加阅读历史
	 * @param uhis
	 * @return void
	 */
	public void addUhis(Uhis uhis) {
		
		Uhis _uhis = uhisDao.getUhis(uhis);
		if (_uhis==null) {
			uhis.setUhis_date(DateUtil.getCurDateTime());
			uhisDao.addUhis(uhis);
		}else {
			_uhis.setUhis_date(DateUtil.getCurDateTime());
			uhisDao.updateUhis(_uhis);
		}
		
	}

	/**
	 * @Title: delUhis
	 * @Description: 删除阅读历史信息
	 * @param uhis
	 * @return void
	 */
	public void delUhiss(Uhis uhis) {
		
		if (!StringUtil.isEmptyString(uhis.getIds())) {
			uhisDao.delUhiss(uhis.getIds().split(","));
		}else {
			uhis = uhisDao.getUhis(uhis);
			uhisDao.delUhis(uhis.getUhis_id()+"");
		}
		
	}
	
	/**
	 * @Title: listUnotes
	 * @Description: 阅读笔记查询
	 * @param unote
	 * @return List<Unote>
	 */
	public List<Unote> listUnotes(Unote unote, int[] sum) {
		
		if (sum != null) {
			sum[0] = unoteDao.listUnotesCount(unote);
		}
		List<Unote> unotes = unoteDao.listUnotes(unote);
		
		return unotes;
	}

	/**
	 * @Title: queryUnote
	 * @Description: 阅读笔记查询
	 * @param unote
	 * @return Unote
	 */
	public Unote queryUnote(Unote unote) {
		
		Unote _unote = unoteDao.getUnote(unote);
		
		return _unote;
	}
	
	public void updateUnote(Unote unote) {
		
		unote.setUnote_date(DateUtil.getCurDate());
		unoteDao.updateUnote(unote);
		
	}

	/**
	 * @Title: addUnote
	 * @Description: 添加阅读笔记
	 * @param unote
	 * @return void
	 */
	public void addUnote(Unote unote) {
		
		Unote _unote = unoteDao.getUnote(unote);
		if (_unote==null) {
			unote.setUnote_date(DateUtil.getCurDate());
			unoteDao.addUnote(unote);
		}else {
			_unote.setUnote_date(DateUtil.getCurDate());
			_unote.setUnote_content(unote.getUnote_content());
			unoteDao.updateUnote(_unote);
		}
		
	}

	/**
	 * @Title: delUnote
	 * @Description: 删除阅读笔记信息
	 * @param unote
	 * @return void
	 */
	public void delUnotes(Unote unote) {
		
		if (!StringUtil.isEmptyString(unote.getIds())) {
			unoteDao.delUnotes(unote.getIds().split(","));
		}else {
			unote = unoteDao.getUnote(unote);
			unoteDao.delUnote(unote.getUnote_id()+"");
		}
		
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public ITbookDao getTbookDao() {
		return tbookDao;
	}

	public ISblog2Dao getSblog2Dao() {
		return sblog2Dao;
	}

	public IInfoDao getInfoDao() {
		return infoDao;
	}

	public ICollectDao getCollectDao() {
		return collectDao;
	}

	public IEvaluateDao getEvaluateDao() {
		return evaluateDao;
	}

	public IUpayDao getUpayDao() {
		return upayDao;
	}

	public IUhisDao getUhisDao() {
		return uhisDao;
	}

	public IUnoteDao getUnoteDao() {
		return unoteDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void setTbookDao(ITbookDao tbookDao) {
		this.tbookDao = tbookDao;
	}

	public void setSblog2Dao(ISblog2Dao sblog2Dao) {
		this.sblog2Dao = sblog2Dao;
	}

	public void setInfoDao(IInfoDao infoDao) {
		this.infoDao = infoDao;
	}

	public void setCollectDao(ICollectDao collectDao) {
		this.collectDao = collectDao;
	}

	public void setEvaluateDao(IEvaluateDao evaluateDao) {
		this.evaluateDao = evaluateDao;
	}

	public void setUpayDao(IUpayDao upayDao) {
		this.upayDao = upayDao;
	}

	public void setUhisDao(IUhisDao uhisDao) {
		this.uhisDao = uhisDao;
	}

	public void setUnoteDao(IUnoteDao unoteDao) {
		this.unoteDao = unoteDao;
	}

	public IBookTypeDao getBookTypeDao() {
		return bookTypeDao;
	}

	public IBookshelfDao getBookshelfDao() {
		return bookshelfDao;
	}

	public void setBookTypeDao(IBookTypeDao bookTypeDao) {
		this.bookTypeDao = bookTypeDao;
	}

	public void setBookshelfDao(IBookshelfDao bookshelfDao) {
		this.bookshelfDao = bookshelfDao;
	}
}
