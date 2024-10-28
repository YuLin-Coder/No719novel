package com.nkl.page.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nkl.common.util.JSONData;
import com.nkl.common.util.Md5;
import com.nkl.common.util.PaperUtil;
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
import com.nkl.page.manager.IndexManager;

@Controller
public class IndexAction {

	@Autowired
	IndexManager indexManager;
	String tip;
	
	public IndexManager getIndexManager() {
		return indexManager;
	}

	public void setIndexManager(IndexManager indexManager) {
		this.indexManager = indexManager;
	}
	
	/**
	 * @Title: index
	 * @Description: 展示图书列表
	 * @return String
	 */
	@RequestMapping(value="page_index.action",method=RequestMethod.GET)
	public String index(ModelMap model){
		try {
			//查询最新图书
			Tbook tbook = new Tbook();
			tbook.setStart(0);
			tbook.setLimit(8);
			List<Tbook> tbooks = indexManager.listTbooks(tbook,null);
			model.addAttribute("tbooks", tbooks);
			
			//查询热门图书
			tbook.setTop_flag(1);
			List<Tbook> tbooks2 = indexManager.listTbooks(tbook,null);
			model.addAttribute("tbooks2", tbooks2);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "default";
	}
	
	/**
	 * @Title: listInfos
	 * @Description: 展示图书章节列表
	 * @return String
	 */
	@RequestMapping(value="page_listInfos.action")
	public String listInfos(Info paramsInfo,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询图书章节信息集合
			if (paramsInfo==null) {
				paramsInfo = new Info();
			}
			//分页信息设置
			paperUtil.setPagination(paramsInfo);
			int[] sum={0};
			List<Info> infos = indexManager.listInfos(paramsInfo,sum); 
			model.addAttribute("infos", infos);
			paperUtil.setTotalCount(sum[0]);
			
			//查询图书
			Tbook tbook = new Tbook();
			tbook.setTbook_id(paramsInfo.getTbook_id());
			tbook = indexManager.queryTbook(tbook);
			model.addAttribute("tbook", tbook);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "info";
	}
	
	/**
	 * @Title: queryInfo
	 * @Description: 查询图书章节详情
	 * @return String
	 */
	@RequestMapping(value="page_queryInfo.action")
	public String queryInfo(Info paramsInfo,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		String returnPage = "infoDetail";
		try {
			 //得到图书章节
			Info info = indexManager.queryInfo(paramsInfo);
			model.addAttribute("info", info);
			
			//查询图书
			Tbook tbook = new Tbook();
			tbook.setTbook_id(info.getTbook_id());
			tbook = indexManager.queryTbook(tbook);
			model.addAttribute("tbook", tbook);
			indexManager.updateTbookClick(tbook);
			
			//判断用户是否收藏图书
			User user = (User)httpSession.getAttribute("userFront");
			if (user!=null) {
				Collect collect = new Collect();
				collect.setUser_id(user.getUser_id());
				collect.setTbook_id(tbook.getTbook_id());
				collect = indexManager.queryCollect(collect);
				if (collect!=null) {
					returnPage = "infoDetail2";
					//查询笔记
					Unote unote = new Unote();
					unote.setUser_id(user.getUser_id());
					unote.setInfo_id(info.getInfo_id());
					unote = indexManager.queryUnote(unote);
					if (unote==null) {
						unote = new Unote();
						unote.setUser_id(user.getUser_id());
						unote.setInfo_id(info.getInfo_id());
					}
					model.addAttribute("unote", unote);
				}
				
				//更新阅读历史
				Uhis uhis = new Uhis();
				uhis.setUser_id(user.getUser_id());
				uhis.setInfo_id(info.getInfo_id());
				indexManager.addUhis(uhis);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return returnPage;
	}
	
	/**
	 * @Title: addUnote
	 * @Description: 添加阅读笔记
	 * @return String
	 */
	@RequestMapping(value="page_addUnote.action")
	@ResponseBody
	public JSONData addUnote(Unote paramsUnote,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //添加收藏
			indexManager.addUnote(paramsUnote);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: listTbooks
	 * @Description: 展示图书列表
	 * @return String
	 */
	@RequestMapping(value="page_listTbooks.action")
	public String listTbooks(Tbook paramsTbook,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询图书信息集合
			if (paramsTbook==null) {
				paramsTbook = new Tbook();
			}
			//分页信息设置
			paperUtil.setPagination(paramsTbook);
			int[] sum={0};
			List<Tbook> tbooks = indexManager.listTbooks(paramsTbook,sum); 
			model.addAttribute("tbooks", tbooks);
			paperUtil.setTotalCount(sum[0]);
			
			//查询图书类型
			BookType bookType = new BookType();
			bookType.setStart(-1);
			List<BookType> bookTypes = indexManager.listBookTypes(bookType, null);
			model.addAttribute("bookTypes", bookTypes);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "tbook";
	}
	
	/**
	 * @Title: listTbooksTop
	 * @Description: 展示推荐图书列表
	 * @return String
	 */
	@RequestMapping(value="page_listTbooksTop.action")
	public String listTbooksTop(Tbook paramsTbook,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询图书信息集合
			if (paramsTbook==null) {
				paramsTbook = new Tbook();
			}
			//分页信息设置
			paperUtil.setPagination(paramsTbook);
			int[] sum={0};
			paramsTbook.setTop_flag(1);
			List<Tbook> tbooks = indexManager.listTbooks(paramsTbook,sum); 
			model.addAttribute("tbooks", tbooks);
			paperUtil.setTotalCount(sum[0]);
			
			//查询图书类型
			BookType bookType = new BookType();
			bookType.setStart(-1);
			List<BookType> bookTypes = indexManager.listBookTypes(bookType, null);
			model.addAttribute("bookTypes", bookTypes);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "tbookTop";
	}
	
	/**
	 * @Title: queryTbook
	 * @Description: 查询图书详情
	 * @return String
	 */
	@RequestMapping(value="page_queryTbook.action")
	public String queryTbook(Tbook paramsTbook,Sblog2 paramsSblog2,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到图书
			Tbook tbook = indexManager.queryTbook(paramsTbook);
			model.addAttribute("tbook", tbook);
			indexManager.updateTbookClick(tbook);
			
			//查询评论信息集合
			if (paramsSblog2==null) {
				paramsSblog2 = new Sblog2();
				paramsSblog2.setTbook_id(paramsTbook.getTbook_id());
			}
			//分页信息设置
			paramsSblog2.setStart(-1);
			List<Sblog2> sblogs = indexManager.listSblog2s(paramsSblog2,null); 
			model.addAttribute("sblog2s", sblogs);
			
			//查询用户书架
			User user = (User)httpSession.getAttribute("userFront");
			if (user!=null) {
				Bookshelf bookshelf = new Bookshelf();
				bookshelf.setUser_id(user.getUser_id());
				bookshelf.setStart(-1);
				List<Bookshelf> bookshelfs = indexManager.listBookshelfs(bookshelf, null);
				if (bookshelfs==null) {
					Bookshelf _bookshelf = new Bookshelf();
					_bookshelf.setUser_id(user.getUser_id());
					_bookshelf.setBookshelf_name("默认书架");
					indexManager.addBookshelf(_bookshelf);
					bookshelfs = indexManager.listBookshelfs(bookshelf, null);
				}
				model.addAttribute("bookshelfs", bookshelfs);
			}
			
			//更新提示
			if (paramsTbook.getCollect_id()!=0) {
				Collect collect = new Collect();
				collect.setCollect_id(paramsTbook.getCollect_id());
				collect.setCollect_tip(0);
				indexManager.updateCollect(collect);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "tbookDetail";
	}
	
	/**
	 * @Title: addSblog2
	 * @Description: 新增评论
	 * @return String
	 */
	@RequestMapping(value="page_addSblog2.action")
	@ResponseBody
	public JSONData addSblog2(Sblog2 paramsSblog2,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//验证码验证
			String random = (String)httpSession.getAttribute("random");
			if (!random.equals(paramsSblog2.getRandom())) {
				jsonData.setErrorReason("验证码错误！");
				return jsonData;
			}
			
			//新增评论
			indexManager.addSblog2(paramsSblog2);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: addEvaluate
	 * @Description: 新增评分
	 * @return String
	 */
	@RequestMapping(value="page_addEvaluate.action")
	@ResponseBody
	public JSONData addEvaluate(Evaluate paramsEvaluate,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//验证是否评分
			Evaluate evaluate = indexManager.queryEvaluate(paramsEvaluate);
			if (evaluate!=null) {
				jsonData.setErrorReason("您已经评过分了，无需再次评分！");
				return jsonData;
			}
			
			//新增评分
			indexManager.addEvaluate(paramsEvaluate);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	@RequestMapping(value="page_saveUserFront.action",method=RequestMethod.POST)
	@ResponseBody
	public JSONData saveUserFront(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = indexManager.getUser(admin);
			httpSession.setAttribute("userFront", admin);
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: saveUserFrontPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	@RequestMapping(value="page_saveUserFrontPass.action",method=RequestMethod.POST)
	@ResponseBody
	public JSONData saveUserFrontPass(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存修改个人信息
			User userFront = (User)httpSession.getAttribute("userFront");
			if (!userFront.getUser_pass().equals(paramsUser.getUser_passOld())) {
				jsonData.setErrorReason("原密码不正确");
				return jsonData;
			}
			indexManager.updateUser(paramsUser);
			//更新session
			if (userFront!=null) {
				userFront.setUser_pass(paramsUser.getUser_pass());
				httpSession.setAttribute("userFront", userFront);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: addUpay
	 * @Description: 线购买会员卡
	 * @return String
	 */
	@RequestMapping(value="page_addUpay.action")
	@ResponseBody
	public JSONData addUpay(Upay paramsUpay,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //添加线购买会员卡
			indexManager.addUpay(paramsUpay, httpSession);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: listMyUpays
	 * @Description: 我的购买记录
	 * @return String
	 */
	@RequestMapping(value="page_listMyUpays.action")
	public String listMyUpays(Upay paramsUpay,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询购买记录
			if (paramsUpay==null) {
				paramsUpay = new Upay();
			}
			//用户身份
			User user = (User)httpSession.getAttribute("userFront");
			paramsUpay.setUser_id(user.getUser_id());
			//分页信息
			paperUtil.setPagination(paramsUpay);
			int[] sum={0};
			List<Upay> sblogs = indexManager.listUpays(paramsUpay,sum); 
			
			model.addAttribute("upays", sblogs);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "upayShow";
	}
	
	/**
	 * @Title: listMySblog2s
	 * @Description: 我的书评信息
	 * @return String
	 */
	@RequestMapping(value="page_listMySblog2s.action")
	public String listMySblog2s(Sblog2 paramsSblog2,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询留言板
			if (paramsSblog2==null) {
				paramsSblog2 = new Sblog2();
			}
			//用户身份
			User user = (User)httpSession.getAttribute("userFront");
			paramsSblog2.setUser_id(user.getUser_id());
			//分页信息
			paperUtil.setPagination(paramsSblog2);
			int[] sum={0};
			List<Sblog2> sblogs = indexManager.listSblog2s(paramsSblog2,sum); 
			
			model.addAttribute("sblog2s", sblogs);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblog2Show";
	}
	
	/**
	 * @Title: delSblog2s
	 * @Description: 删除书评
	 * @return String
	 */
	@RequestMapping(value="page_delSblog2s.action")
	@ResponseBody
	public JSONData delSblog2s(Sblog2 paramsSblog2,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//新增订单
			indexManager.delSblog2s(paramsSblog2);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: listMyBookshelfs
	 * @Description: 查询我的书架
	 * @return String
	 */
	@RequestMapping(value="page_listMyBookshelfs.action")
	public String listMyBookshelfs(Bookshelf paramsBookshelf,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsBookshelf==null) {
				paramsBookshelf = new Bookshelf();
			}
			//获取用户,用户只能查询自己的书架
			User userFront = (User)httpSession.getAttribute("userFront");
			if (userFront.getUser_type()==1) {
				paramsBookshelf.setUser_id(userFront.getUser_id());
			}
			//设置分页信息
			paperUtil.setPagination(paramsBookshelf);
			//总的条数
			int[] sum={0};
			//查询商品预约列表
			List<Bookshelf> bookshelfs = indexManager.listBookshelfs(paramsBookshelf,sum); 
			
			model.addAttribute("bookshelfs", bookshelfs);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myBookshelfShow";
	}
	
	/**
	 * @Title: addBookshelfShow
	 * @Description: 显示添加书架页面
	 * @return String
	 */
	@RequestMapping(value="page_addBookshelfShow.action")
	public String addBookshelfShow(ModelMap model){
		return "myBookshelfEdit";
	}
	
	/**
	 * @Title: addBookshelf
	 * @Description: 添加书架
	 * @return String
	 */
	@RequestMapping(value="page_addBookshelf.action")
	@ResponseBody
	public JSONData addBookshelf(Bookshelf paramsBookshelf,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			Bookshelf bookshelf = indexManager.queryBookshelf(paramsBookshelf);
			if (bookshelf!=null) {
				jsonData.setErrorReason("书架已存在");
				return jsonData;
			}
			 //添加书架
			indexManager.addBookshelf(paramsBookshelf);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: editBookshelf
	 * @Description: 编辑书架
	 * @return String
	 */
	@RequestMapping(value="page_editBookshelf.action")
	public String editBookshelf(Bookshelf paramsBookshelf,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到书架
			Bookshelf bookshelf = indexManager.queryBookshelf(paramsBookshelf);
			model.addAttribute("bookshelf", bookshelf);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myBookshelfEdit";
	}
	
	/**
	 * @Title: saveBookshelf
	 * @Description: 保存编辑书架
	 * @return String
	 */
	@RequestMapping(value="page_saveBookshelf.action")
	@ResponseBody
	public JSONData saveBookshelf(Bookshelf paramsBookshelf,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存编辑书架
			indexManager.updateBookshelf(paramsBookshelf);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	 
	
	/**
	 * @Title: delBookshelfs
	 * @Description: 删除书架
	 * @return String
	 */
	@RequestMapping(value="page_delBookshelfs.action")
	@ResponseBody
	public JSONData delBookshelfs(Bookshelf paramsBookshelf,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //删除书架
			indexManager.delBookshelfs(paramsBookshelf);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: listMyCollects
	 * @Description: 查询我的收藏
	 * @return String
	 */
	@RequestMapping(value="page_listMyCollects.action")
	public String listMyCollects(Collect paramsCollect,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsCollect==null) {
				paramsCollect = new Collect();
			}
			//获取用户,用户只能查询自己的收藏
			User userFront = (User)httpSession.getAttribute("userFront");
			if (userFront.getUser_type()==1) {
				paramsCollect.setUser_id(userFront.getUser_id());
			}
			//设置分页信息
			paperUtil.setPagination(paramsCollect);
			//总的条数
			int[] sum={0};
			//查询商品预约列表
			List<Collect> collects = indexManager.listCollects(paramsCollect,sum); 
			
			model.addAttribute("collects", collects);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myCollectShow";
	}
	
	/**
	 * @Title: addCollect
	 * @Description: 添加书架收藏
	 * @return String
	 */
	@RequestMapping(value="page_addCollect.action")
	@ResponseBody
	public JSONData addCollect(Collect paramsCollect,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //添加收藏
			indexManager.addCollect(paramsCollect);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}

	/**
	 * @Title: delCollects
	 * @Description: 删除收藏
	 * @return String
	 */
	@RequestMapping(value="page_delCollects.action")
	@ResponseBody
	public JSONData delCollects(Collect paramsCollect,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //删除收藏
			indexManager.delCollects(paramsCollect);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: listMyUhiss
	 * @Description: 查询我的阅读历史
	 * @return String
	 */
	@RequestMapping(value="page_listMyUhiss.action")
	public String listMyUhiss(Uhis paramsUhis,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsUhis==null) {
				paramsUhis = new Uhis();
			}
			//获取用户,用户只能查询自己的阅读历史
			User userFront = (User)httpSession.getAttribute("userFront");
			if (userFront.getUser_type()==1) {
				paramsUhis.setUser_id(userFront.getUser_id());
			}
			//设置分页信息
			paperUtil.setPagination(paramsUhis);
			//总的条数
			int[] sum={0};
			//查询商品预约列表
			List<Uhis> uhiss = indexManager.listUhiss(paramsUhis,sum); 
			
			model.addAttribute("uhiss", uhiss);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myUhisShow";
	}
	
	/**
	 * @Title: addUhis
	 * @Description: 添加书架阅读历史
	 * @return String
	 */
	@RequestMapping(value="page_addUhis.action")
	@ResponseBody
	public JSONData addUhis(Uhis paramsUhis,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //添加阅读历史
			indexManager.addUhis(paramsUhis);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}

	/**
	 * @Title: delUhiss
	 * @Description: 删除阅读历史
	 * @return String
	 */
	@RequestMapping(value="page_delUhiss.action")
	@ResponseBody
	public JSONData delUhiss(Uhis paramsUhis,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //删除阅读历史
			indexManager.delUhiss(paramsUhis);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: reg
	 * @Description: 跳转注册页面
	 * @return String
	 */
	@RequestMapping(value="page_reg.action")
	public String reg(){
		return "reg";
	}
	
	/**
	 * @Title: myInfo
	 * @Description: 跳转个人信息页面
	 * @return String
	 */
	@RequestMapping(value="page_myInfo.action")
	public String myInfo(){
		return "myInfo";
	}
	
	/**
	 * @Title: myPwd
	 * @Description: 跳转个人密码页面
	 * @return String
	 */
	@RequestMapping(value="page_myPwd.action")
	public String myPwd(){
		return "myPwd";
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

}
