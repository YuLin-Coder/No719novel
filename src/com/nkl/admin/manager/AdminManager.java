package com.nkl.admin.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkl.common.util.DateUtil;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;
import com.nkl.page.dao.IBookTypeDao;
import com.nkl.page.dao.ICollectDao;
import com.nkl.page.dao.IInfoDao;
import com.nkl.page.dao.ISblog2Dao;
import com.nkl.page.dao.ITbookDao;
import com.nkl.page.dao.IUpayDao;
import com.nkl.page.dao.IUserDao;
import com.nkl.page.domain.BookType;
import com.nkl.page.domain.Info;
import com.nkl.page.domain.Sblog2;
import com.nkl.page.domain.Tbook;
import com.nkl.page.domain.Upay;
import com.nkl.page.domain.User;

@Service
public class AdminManager {

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
	IUpayDao upayDao;
	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User>  listUsers(User user,int[] sum){
		initUser();
		if (sum!=null) {
			sum[0] = userDao.listUsersCount(user);
		}
		List<User> users = userDao.listUsers(user);
		
		
		return users;
	}
	
	/**
	 * @Title: initUser
	 * @Description: 会员到期了，不续费的，自动变回普通用户
	 * @return void
	 */
	public void initUser(){
		User user = new User();
		user.setUser_type(1);
		user.setUser_kind(2);
		user.setStart(-1);
		List<User> users = userDao.listUsers(user);
		if (users!=null) {
			for (User user2 : users) {
				if (user2.getLeftDays()==0) {
					user2.setUser_kind(1);
					userDao.updateUser(user2);
				}
			}
		}
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
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void  addUser(User user){
		user.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			//user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		//默认头像
		if (StringUtil.isEmptyString(user.getUser_photo())) {
			user.setUser_photo("default.jpg");
		}
		userDao.addUser(user);
		
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
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void  delUsers(User user){
		
		userDao.delUsers(user.getIds().split(","));
		
	}
	
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
	 * @Title: queryBookType
	 * @Description: 图书类型查询
	 * @param bookType
	 * @return BookType
	 */
	public BookType queryBookType(BookType bookType) {
		
		BookType _bookType = bookTypeDao.getBookType(bookType);
		
		return _bookType;
	}

	/**
	 * @Title: addBookType
	 * @Description: 添加图书类型
	 * @param bookType
	 * @return void
	 */
	public void addBookType(BookType bookType) {
		
		bookTypeDao.addBookType(bookType);
		
	}

	/**
	 * @Title: updateBookType
	 * @Description: 更新图书类型信息
	 * @param bookType
	 * @return void
	 */
	public void updateBookType(BookType bookType) {
		
		bookTypeDao.updateBookType(bookType);
		
	}

	/**
	 * @Title: delBookType
	 * @Description: 删除图书类型信息
	 * @param bookType
	 * @return void
	 */
	public void delBookTypes(BookType bookType) {
		
		bookTypeDao.delBookTypes(bookType.getIds().split(","));
		
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

	/**
	 * @Title: addTbook
	 * @Description: 添加图书
	 * @param tbook
	 * @return void
	 */
	public void addTbook(Tbook tbook) {
		
		if (!StringUtil.isEmptyString(tbook.getTbook_desc())) {
			tbook.setTbook_desc(Transcode.htmlEncode(tbook.getTbook_desc()));
		}
		tbook.setTbook_date(DateUtil.dateToDateString(new Date()));
		tbookDao.addTbook(tbook);
		
	}

	/**
	 * @Title: updateTbook
	 * @Description: 更新图书信息
	 * @param tbook
	 * @return void
	 */
	public void updateTbook(Tbook tbook) {
		
		if (!StringUtil.isEmptyString(tbook.getTbook_desc())) {
			tbook.setTbook_desc(Transcode.htmlEncode(tbook.getTbook_desc()));
		}
		tbookDao.updateTbook(tbook);
		
	}

	public void updateTbookClick(Tbook tbook) {
		
		tbookDao.updateTbookClick(tbook);
		
	}
	
	/**
	 * @Title: delTbook
	 * @Description: 删除图书信息
	 * @param tbook
	 * @return void
	 */
	public void delTbooks(Tbook tbook) {
		
		tbookDao.delTbooks(tbook.getIds().split(","));
		
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
		//更新收藏提示
		collectDao.updateCollectTip(info.getTbook_id());
		
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
	 * @Title: querySblog2
	 * @Description: 评论信息查询
	 * @param sblog2
	 * @return Sblog2
	 */
	public Sblog2 querySblog2(Sblog2 sblog2) {
		
		Sblog2 _sblog = sblog2Dao.getSblog2(sblog2);
		
		return _sblog;
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
 
}
