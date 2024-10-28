package com.nkl.admin.action;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nkl.admin.manager.AdminManager;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.PaperUtil;
import com.nkl.page.domain.BookType;
import com.nkl.page.domain.Info;
import com.nkl.page.domain.Sblog2;
import com.nkl.page.domain.Tbook;
import com.nkl.page.domain.Upay;
import com.nkl.page.domain.User;

@Controller
public class AdminAction {

	@Autowired
	AdminManager adminManager;
	public AdminManager getAdminManager() {
		return adminManager;
	}
	public void setAdminManager(AdminManager adminManager) {
		this.adminManager = adminManager;
	}
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveAdmin.action")
	public String saveAdmin(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//验证用户会话是否失效
			if (!validateAdmin(httpSession)) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.getUser(admin);
			httpSession.setAttribute("admin", admin);

			setSuccessTip("编辑成功", "modifyInfo.jsp", model);
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("编辑异常", "modifyInfo.jsp", model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveAdminPass.action")
	public String saveAdminPass(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//验证用户会话是否失效
			if (!validateAdmin(httpSession)) {
				return "loginTip";
			}
			User admin = (User)httpSession.getAttribute("admin");
			if (!admin.getUser_pass().equals(paramsUser.getUser_passOld())) {
				setErrorTip("修改异常，原密码不正确", "modifyPwd.jsp", model);
				return "infoTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				httpSession.setAttribute("admin", admin);
			}

			setSuccessTip("修改成功", "modifyPwd.jsp", model);
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp", model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询普通用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listUsers.action")
	public String listUsers(User paramsUser,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询用户列表
			paramsUser.setUser_type(1);
			paramsUser.setUser_kind(1);
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			model.addAttribute("users", users);
			model.addAttribute("paramsUser", paramsUser);
			paperUtil.setTotalCount(sum[0]);

		} catch (Exception e) {
			setErrorTip("查询普通用户异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加普通用户页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addUserShow.action")
	public String addUserShow(ModelMap model){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加普通用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addUser.action")
	public String addUser(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//检查用户是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.getUser(user);
			if (user!=null) {
				model.addAttribute("tip","失败，该用户名已经存在！");
				model.addAttribute("user", paramsUser);
				
				return "userEdit";
			}
			 //添加用户
			paramsUser.setUser_type(1);
			paramsUser.setUser_kind(1);
			paramsUser.setReg_date(DateUtil.getCurDateTime());
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listUsers.action", model);
		} catch (Exception e) {
			setErrorTip("添加普通用户异常", "Admin_listUsers.action", model);
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editUser.action")
	public String editUser(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到用户
			User user = adminManager.getUser(paramsUser);
			model.addAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询普通用户异常", "Admin_listUsers.action", model);
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveUser.action")
	public String saveUser(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑用户
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listUsers.action", model);
		} catch (Exception e) {
			setErrorTip("编辑普通用户异常", "Admin_listUsers.action", model);
			return "infoTip";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delUsers.action")
	public String delUsers(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除用户
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除普通用户成功", "Admin_listUsers.action", model);
		} catch (Exception e) {
			setErrorTip("删除普通用户异常", "Admin_listUsers.action", model);
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers2
	 * @Description: 查询会员用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listUsers2.action")
	public String listUsers2(User paramsUser,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询用户列表
			paramsUser.setUser_type(1);
			paramsUser.setUser_kind(2);
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			model.addAttribute("users", users);
			model.addAttribute("paramsUser", paramsUser);
			paperUtil.setTotalCount(sum[0]);

		} catch (Exception e) {
			setErrorTip("查询会员用户异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "userShow2";
	}
	
	/**
	 * @Title: addUserShow2
	 * @Description: 显示添加会员用户页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addUserShow2.action")
	public String addUserShow2(ModelMap model){
		return "userEdit2";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加会员用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addUser2.action")
	public String addUser2(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//检查用户是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.getUser(user);
			if (user!=null) {
				model.addAttribute("tip","失败，该用户名已经存在！");
				model.addAttribute("user", paramsUser);
				
				return "userEdit2";
			}
			 //添加用户
			paramsUser.setUser_type(2);
			paramsUser.setUser_kind(2);
			paramsUser.setReg_date(DateUtil.getCurDateTime());
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listUsers2.action", model);
		} catch (Exception e) {
			setErrorTip("添加会员用户异常", "Admin_listUsers2.action", model);
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser2
	 * @Description: 编辑用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editUser2.action")
	public String editUser2(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到用户
			User user = adminManager.getUser(paramsUser);
			model.addAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询会员用户异常", "Admin_listUsers2.action", model);
			return "infoTip";
		}
		
		return "userEdit2";
	}
	
	/**
	 * @Title: saveUser2
	 * @Description: 保存编辑用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveUser2.action")
	public String saveUser2(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑用户
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listUsers2.action", model);
		} catch (Exception e) {
			setErrorTip("编辑会员用户异常", "Admin_listUsers2.action", model);
			return "infoTip";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delUsers2.action")
	public String delUsers2(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除用户
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除会员用户成功", "Admin_listUsers2.action", model);
		} catch (Exception e) {
			setErrorTip("删除会员用户异常", "Admin_listUsers2.action", model);
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listUpays
	 * @Description: 查询支付记录
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listUpays.action")
	public String listUpays(Upay paramsUpay,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsUpay==null) {
				paramsUpay = new Upay();
			}
			//设置分页信息
			paperUtil.setPagination(paramsUpay);
			//总的条数
			int[] sum={0};
			//查询支付记录列表
			List<Upay> upays = adminManager.listUpays(paramsUpay,sum); 
			
			model.addAttribute("upays", upays);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询支付记录异常","main.jsp",model);
			return "infoTip";
		}
		
		return "upayShow";
	}
	
	/**
	 * @Title: listUsers3
	 * @Description: 会员余额查询
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listUsers3.action")
	public String listUsers3(User paramsUser,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询用户列表
			paramsUser.setUser_type(1);
			paramsUser.setUser_kind(2);
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			model.addAttribute("users", users);
			model.addAttribute("paramsUser", paramsUser);
			paperUtil.setTotalCount(sum[0]);

		} catch (Exception e) {
			setErrorTip("会员余额查询异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "userShow3";
	}
	
	/**
	 * @Title: listBookTypes
	 * @Description: 查询图书类型
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listBookTypes.action")
	public String listBookTypes(BookType paramsBookType,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsBookType==null) {
				paramsBookType = new BookType();
			}
			
			//设置分页信息
			paperUtil.setPagination(paramsBookType);
			//总的条数
			int[] sum={0};
			//查询图书类型列表
			List<BookType> bookTypes = adminManager.listBookTypes(paramsBookType,sum); 
			
			model.addAttribute("bookTypes", bookTypes);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询图书类型异常","main.jsp",model);
			return "infoTip";
		}
		
		return "bookTypeShow";
	}
	
	/**
	 * @Title: addBookTypeShow
	 * @Description: 显示添加图书类型页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addBookTypeShow.action")
	public String addBookTypeShow(ModelMap model){
		return "bookTypeEdit";
	}
	
	/**
	 * @Title: addBookType
	 * @Description: 添加图书类型
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addBookType.action")
	public String addBookType(BookType paramsBookType,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//检查图书类型是否存在
			BookType bookType = new BookType();
			bookType.setBook_type_name(paramsBookType.getBook_type_name());
			bookType = adminManager.queryBookType(bookType);
			if (bookType!=null) {
				model.addAttribute("tip","失败，该类型已经存在！");
				model.addAttribute("bookType", paramsBookType);
				return "bookTypeEdit";
			}
			
			 //添加图书类型
			adminManager.addBookType(paramsBookType);
			
			setSuccessTip("添加成功","Admin_listBookTypes.action",model);
		} catch (Exception e) {
			setErrorTip("添加图书类型异常","Admin_listBookTypes.action",model);
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editBookType
	 * @Description: 编辑图书类型
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editBookType.action")
	public String editBookType(BookType paramsBookType,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到图书类型
			BookType bookType = adminManager.queryBookType(paramsBookType);
			model.addAttribute("bookType", bookType);
			
		} catch (Exception e) {
			setErrorTip("查询图书类型异常","Admin_listBookTypes.action",model);
			return "infoTip";
		}
		
		return "bookTypeEdit";
	}
	
	/**
	 * @Title: saveBookType
	 * @Description: 保存编辑图书类型
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveBookType.action")
	public String saveBookType(BookType paramsBookType,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//检查图书类型是否存在
			BookType bookType = new BookType();
			bookType.setBook_type_name(paramsBookType.getBook_type_name());
			bookType = adminManager.queryBookType(bookType);
			if (bookType!=null&&bookType.getBook_type_id()!=paramsBookType.getBook_type_id()) {
				model.addAttribute("tip","失败，该类型已经存在！");
				model.addAttribute("bookType", paramsBookType);
				return "bookTypeEdit";
			}
			
			 //保存编辑图书类型
			adminManager.updateBookType(paramsBookType);
			
			setSuccessTip("编辑成功","Admin_listBookTypes.action",model);
		} catch (Exception e) {
			model.addAttribute("tip","编辑失败");
			model.addAttribute("bookType", paramsBookType);
			return "bookTypeEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delBookTypes
	 * @Description: 删除图书类型
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delGoodss.action")
	public String delGoodss(BookType paramsBookType,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除图书类型
			adminManager.delBookTypes(paramsBookType);
			
			setSuccessTip("删除图书类型成功","Admin_listBookTypes.action",model);
		} catch (Exception e) {
			setErrorTip("删除图书类型异常","Admin_listBookTypes.action",model);
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listTbooks
	 * @Description: 查询图书
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listTbooks.action")
	public String listTbooks(Tbook paramsTbook,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsTbook==null) {
				paramsTbook = new Tbook();
			}
			//设置分页信息
			paperUtil.setPagination(paramsTbook);
			Object object = httpSession.getAttribute("admin");
			//反射获取object的data属性值(subEvent中的eventId)
			java.lang.reflect.Field fi = null;
			String s =null;
			try {
				fi = object.getClass().getDeclaredField("user_id");
				fi.setAccessible(true);
				s= fi.get(object).toString();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			paramsTbook.setUser_id(Integer.parseInt(s));
			int[] sum={0};
			List<Tbook> tbooks = adminManager.listTbooks(paramsTbook,sum); 
			model.addAttribute("tbooks", tbooks);
			paperUtil.setTotalCount(sum[0]);
			//查询图书类型
			BookType bookType = new BookType();
			bookType.setStart(-1);
			List<BookType> bookTypes = adminManager.listBookTypes(bookType, null);
			model.addAttribute("bookTypes", bookTypes);
			
		} catch (Exception e) {
			setErrorTip("查询图书异常","main.jsp",model);
			return "infoTip";
		}
		
		return "tbookShow";
	}
	
	/**
	 * @Title: queryTbook
	 * @Description: 查看图书
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_queryTbook.action")
	public String queryTbook(Tbook paramsTbook,ModelMap model){
		try {
			 //得到图书
			Tbook tbook = adminManager.queryTbook(paramsTbook);
			model.addAttribute("tbook", tbook);
			
		} catch (Exception e) {
			setErrorTip("查询图书异常","Admin_listTbooks.action",model);
			return "infoTip";
		}
		
		return "tbookDetail";
	}
	
	/**
	 * @Title: addTbookShow
	 * @Description: 显示添加图书页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addTbookShow.action")
	public String addTbookShow(ModelMap model){
		//查询图书类型
		BookType bookType = new BookType();
		bookType.setStart(-1);
		List<BookType> bookTypes = adminManager.listBookTypes(bookType, null);
		model.addAttribute("bookTypes", bookTypes);
		
		return "tbookEdit";
	}
	
	/**
	 * @Title: addTbook
	 * @Description: 添加图书
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addTbook.action")
	public String addTbook(Tbook paramsTbook,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //添加图书
			adminManager.addTbook(paramsTbook);

			setSuccessTip("添加图书成功","Admin_listTbooks.action",model);
		} catch (Exception e) {
			setErrorTip("添加图书异常","Admin_listTbooks.action",model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: editTbook
	 * @Description: 编辑图书
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editTbook.action")
	public String editTbook(Tbook paramsTbook,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到图书
			Tbook tbook = adminManager.queryTbook(paramsTbook);
			model.addAttribute("tbook", tbook);
			
			//查询图书类型
			BookType bookType = new BookType();
			bookType.setStart(-1);
			List<BookType> bookTypes = adminManager.listBookTypes(bookType, null);
			model.addAttribute("bookTypes", bookTypes);
			
		} catch (Exception e) {
			setErrorTip("查询图书异常","Admin_listTbooks.action",model);
			return "infoTip";
		}
		
		return "tbookEdit";
	}
	
	/**
	 * @Title: saveTbook
	 * @Description: 保存编辑图书
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveTbook.action")
	public String saveTbook(Tbook paramsTbook,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑图书
			adminManager.updateTbook(paramsTbook);
			adminManager.updateTbookClick(paramsTbook);
			
		} catch (Exception e) {
			model.addAttribute("tip","编辑失败");
			model.addAttribute("tbook", paramsTbook);
			
			//查询图书类型
			BookType bookType = new BookType();
			bookType.setStart(-1);
			List<BookType> bookTypes = adminManager.listBookTypes(bookType, null);
			model.addAttribute("bookTypes", bookTypes);
			
			return "tbookEdit";
		}
		setSuccessTip("编辑图书成功","Admin_listTbooks.action",model);
		return "infoTip";
	}
	
	
	/**
	 * @Title: delTbooks
	 * @Description: 删除图书
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delTbooks.action")
	public String delTbooks(Tbook paramsTbook,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除图书
			adminManager.delTbooks(paramsTbook);

			setSuccessTip("删除图书成功","Admin_listTbooks.action",model);
		} catch (Exception e) {
			setErrorTip("删除图书异常","Admin_listTbooks.action",model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listSblog2s
	 * @Description: 查询留言板
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listSblog2s.action")
	public String listSblog2s(Sblog2 paramsSblog2,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询留言板
			if (paramsSblog2==null) {
				paramsSblog2 = new Sblog2();
			}
			//分页设置
			paperUtil.setPagination(paramsSblog2);
			int[] sum={0};
			List<Sblog2> sblogs = adminManager.listSblog2s(paramsSblog2,sum); 
			
			model.addAttribute("sblogs", sblogs);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询异常","main.jsp",model);
			return "infoTip";
		}
		
		return "sblog2Show";
	}
	
	/**
	 * @Title: delSblog2s
	 * @Description: 删除留言板
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delSblog2s.action")
	public String delSblog2s(Sblog2 paramsSblog2,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除留言板
			adminManager.delSblog2s(paramsSblog2);

			setSuccessTip("删除留言成功","Admin_listSblog2s.action",model);
		} catch (Exception e) {
			setErrorTip("删除留言异常","Admin_listSblog2s.action",model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listInfos
	 * @Description: 查询图书章节
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listInfos.action")
	public String listInfos(Info paramsInfo,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsInfo==null) {
				paramsInfo = new Info();
			}
			//设置分页信息
			paperUtil.setPagination(paramsInfo);
			int[] sum={0};
			List<Info> infos = adminManager.listInfos(paramsInfo,sum); 
			model.addAttribute("infos", infos);
			paperUtil.setTotalCount(sum[0]);
			
			//查询图书
			Tbook tbook = new Tbook();
			tbook.setTbook_id(paramsInfo.getTbook_id());
			tbook = adminManager.queryTbook(tbook);
			model.addAttribute("tbook", tbook);
			model.addAttribute("tbook_id", paramsInfo.getTbook_id());
			
		} catch (Exception e) {
			setErrorTip("查询常见帮助异常","main.jsp",model);
			return "infoTip";
		}
		
		return "infoShow";
	}
	
	/**
	 * @Title: addInfoShow
	 * @Description: 显示添加图书章节页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addInfoShow.action")
	public String addInfoShow(Info paramsInfo,ModelMap model){
		model.addAttribute("tbook_id", paramsInfo.getTbook_id());
		return "infoEdit";
	}
	
	/**
	 * @Title: addInfo
	 * @Description: 添加图书章节
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addInfo.action")
	public String addInfo(Info paramsInfo,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //添加常见帮助
			adminManager.addInfo(paramsInfo);

			setSuccessTip("添加图书章节成功", "Admin_listInfos.action?paramsInfo.tbook_id="+paramsInfo.getTbook_id(), model);
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("添加图书章节异常", "Admin_listInfos.action?paramsInfo.tbook_id="+paramsInfo.getTbook_id(), model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: queryInfo
	 * @Description: 查询图书章节信息
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_queryInfo.action")
	public String queryInfo(Info paramsInfo,ModelMap model){
		try {
			 //得到图书章节信息
			Info info = adminManager.queryInfo(paramsInfo);
			model.addAttribute("info", info);

			//查询图书
			Tbook tbook = new Tbook();
			tbook.setTbook_id(info.getTbook_id());
			tbook = adminManager.queryTbook(tbook);
			model.addAttribute("tbook", tbook);
			
		} catch (Exception e) {
			setErrorTip("查询图书章节信息异常", "Admin_listInfos.action?paramsInfo.tbook_id="+paramsInfo.getTbook_id(), model);
			return "infoTip";
		}
		
		return "infoDetail";
	}
	 
	/**
	 * @Title: editInfo
	 * @Description: 编辑图书章节
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editInfo.action")
	public String editInfo(Info paramsInfo,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到图书章节
			if (paramsInfo==null) {
				paramsInfo = new Info();
			}
			Info info = adminManager.queryInfo(paramsInfo);
			model.addAttribute("info", info);
			model.addAttribute("tbook_id", paramsInfo.getTbook_id());
			
		} catch (Exception e) {
			setErrorTip("查询图书章节异常", "Admin_listInfos.action?paramsInfo.tbook_id="+paramsInfo.getTbook_id(), model);
			return "infoTip";
		}
		
		return "infoEdit";
	}
	
	/**
	 * @Title: saveInfo
	 * @Description: 保存编辑图书章节
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveInfo.action")
	public String saveInfo(Info paramsInfo,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑图书章节
			adminManager.updateInfo(paramsInfo);
			
		} catch (Exception e) {
			model.addAttribute("tip","编辑失败");
			model.addAttribute("info", paramsInfo);
			model.addAttribute("tbook_id", paramsInfo.getTbook_id());
			return "infoEdit";
		}
		setSuccessTip("编辑图书章节成功", "Admin_listInfos.action?paramsInfo.tbook_id="+paramsInfo.getTbook_id(), model);
		return "infoTip";
	}
	
	/**
	 * @Title: delInfos
	 * @Description: 删除图书章节
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delInfos.action")
	public String delInfos(Info paramsInfo,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除图书章节
			adminManager.delInfos(paramsInfo);

			setSuccessTip("删除图书章节成功", "Admin_listInfos.action?paramsInfo.tbook_id="+paramsInfo.getTbook_id(), model);
		} catch (Exception e) {
			setErrorTip("删除图书章节异常", "Admin_listInfos.action?paramsInfo.tbook_id="+paramsInfo.getTbook_id(), model);
		}
		return "infoTip";
	}
	
	private boolean validateAdmin(HttpSession httpSession){
		User admin = (User)httpSession.getAttribute("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url,ModelMap model){
		model.addAttribute("tipType", "error");
		model.addAttribute("tip", tip);
		model.addAttribute("url1", url);
		model.addAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url,ModelMap model){
		model.addAttribute("tipType", "success");
		model.addAttribute("tip", tip);
		model.addAttribute("url1", url);
		model.addAttribute("value1", "确 定");
	}

}
