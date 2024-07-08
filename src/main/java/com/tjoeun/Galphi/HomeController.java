package com.tjoeun.Galphi;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.Galphi.dao.MybatisDAO;
import com.tjoeun.Galphi.vo.AccountVO;
import com.tjoeun.Galphi.vo.BookCommentList;
import com.tjoeun.Galphi.vo.BookCommentVO;
import com.tjoeun.Galphi.vo.BookList;
import com.tjoeun.Galphi.vo.BookVO;
import com.tjoeun.Galphi.vo.Param;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/")
	public String home(Locale locale, Model model) {
		logger.info("HomeController 클래스의 home() 메소드 실행");
		return "redirect:index";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		logger.info("HomeController 클래스의 index() 메소드 실행");
		return "index";
	}
	
	@RequestMapping("/selectByISBN")
	public String selectByISBN(HttpServletRequest request, Model model, BookCommentList bookCommentList) {
		logger.info("HomeController 클래스의 selectByISBN() 메소드 실행");
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		int ISBN = Integer.parseInt(request.getParameter("ISBN"));
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/applicationCTX.xml");
		BookVO bookVO = ctx.getBean("bookVO", BookVO.class);
		BookVO vo = mapper.selectByISBN(ISBN);
		bookCommentList = new BookCommentList();
		bookCommentList.setList(mapper.selectCommentList(ISBN));
		model.addAttribute("vo", vo);
		model.addAttribute("bookCommentList", bookCommentList);
//		model.addAttribute("enter", "\r\n");
		return "contentView";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		logger.info("HomeController 클래스의 login() 메소드 실행");
		return "login";
	}
	
	@RequestMapping("/loginOk")
	public String loginOK(HttpServletRequest request, Model model, AccountVO accountVO) {
		logger.info("HomeController 클래스의 loginOk() 메소드 실행");
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/applicationCTX.xml");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		// logger.info(id);
		// logger.info(password);
	    HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("id", id);
		hmap.put("password", password);
		int account = mapper.Login(hmap);
		int IdExNo = mapper.IdCheck(id);
		String nickname = mapper.nickname(hmap);
		// logger.info(nickname);
		if (IdExNo == 0) {
			request.setAttribute("msg", "아이디 정보가 존재하지 않습니다.");
			request.setAttribute("url", "login");
			return "alert";
		} else if (account == 0) {
			request.setAttribute("msg", "비밀번호를 확인하세요.");
			request.setAttribute("url", "login");
			return "alert";
		} else {
			request.setAttribute("msg", "로그인 성공!");
			request.setAttribute("url", "index");
			HttpSession session = request.getSession();
			session.setAttribute("nickname", nickname);
			return "alert";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		logger.info("HomeController 클래스의 logout() 메소드 실행");
		HttpSession session = request.getSession();
		request.setAttribute("url", "index");
		session.removeAttribute("nickname");
		request.setAttribute("msg", "로그아웃");
		return "alert";
	}
	
	@RequestMapping("/account_create")
	public String account_create(HttpServletRequest request, Model model) {
		logger.info("HomeController 클래스의 account_create() 메소드 실행");
		return "account_create";
	}
	
	@RequestMapping("/account_insert")
	public String account_insert(HttpServletRequest request, Model model, AccountVO ao) {
		logger.info("HomeController 클래스의 account_insert() 메소드 실행");
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String username = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		System.out.println("id: " + id + ", password: " + password + ", username: " + username + ", nicknmame: " + nickname);
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("id", id);
		hmap.put("password", password);
		hmap.put("username", username);
		hmap.put("nickname", nickname);
		mapper.insert(hmap);
		request.setAttribute("msg", "회원가입이 완료되었습니다!");
		request.setAttribute("url", "index");
		return "alert";
	}

	@RequestMapping("/nick_Check")
	public String nick_Check(HttpServletRequest request, Model model) {
		logger.info("HomeController 클래스의 nick_Check() 메소드 실행");
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		String nickname = request.getParameter("nickname");
		// System.out.println("nickname: " + nickname);
		int nick_Check = mapper.nickCheck(nickname);
		// System.out.println("nick_Check: " + nick_Check);
		if (nickname.trim().length() == 0) {
			request.setAttribute("msg", "닉네임을 입력해주세요.");
			return "alert";
		} else if (nick_Check > 0) {
			request.setAttribute("msg", "이미 사용중인 닉네임입니다.");
			return "alert";
		} else {
			request.setAttribute("msg", "사용 가능한 닉네임입니다.");
			return "alert";
		}
	}
	
	@RequestMapping("/id_Check")
	public String id_Check(HttpServletRequest request, Model model) {
		logger.info("HomeController 클래스의 id_Check() 메소드 실행");
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		String id = request.getParameter("id");
		// System.out.println("id: " + id);
		int	id_Check = mapper.accountIdCheck(id);
		// System.out.println("id_Check: " + id_Check);
		if (id.trim().length() == 0) {
			request.setAttribute("msg", "ID를 입력해주세요.");
			return "alert";
		} else if (id_Check > 0) {
			request.setAttribute("msg", "이미 사용중인 아이디입니다.");
			return "alert";
		} else {
			request.setAttribute("msg", "사용 가능한 아이디입니다.");
			return "alert";
		}
	}
	
	@RequestMapping("/alert")
	public String alert(HttpServletRequest request, Model model) {
		logger.info("HomeController 클래스의 alert() 메소드 실행");
		return "alert";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		logger.info("HomeController 클래스의 list() 메소드 실행");
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		int pageSize = 10;
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) { }
		String list = request.getParameter("list");
		String categoryList = request.getParameter("list");
		try {
			categoryList = categoryList.toLowerCase();
		} catch (Exception e) { }
		String category = request.getParameter("category");
		String item = request.getParameter("item");
		// logger.info(category);
		// logger.info(item);
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/applicationCTX.xml");
		BookList bookBestList = new BookList(pageSize, 1, currentPage);
		BookList bookDailyList = new BookList(pageSize, 1, currentPage);
		BookList bookNewList = new BookList(pageSize, mapper.selectNewCount(), currentPage);
		BookList bookCategoryList = new BookList(pageSize, mapper.selectCategoryCount(categoryList), currentPage);
		// System.out.println(bookCategoryList);
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", bookBestList.getStartNo());
		hmap.put("endNo", bookBestList.getEndNo());
		hmap.put("startNo", bookDailyList.getStartNo());
		hmap.put("endNo", bookDailyList.getEndNo());
		hmap.put("startNo", bookNewList.getStartNo());
		hmap.put("endNo", bookNewList.getEndNo());
		
		bookBestList.setList(mapper.selectBestList(hmap));
		bookDailyList.setList(mapper.selectDailyList(hmap));
		bookNewList.setList(mapper.selectNewList(hmap));
		int startNo = bookCategoryList.getStartNo();
		int endNo = bookCategoryList.getEndNo();
		Param param = new Param(startNo, endNo, categoryList);
		bookCategoryList.setList(mapper.selectCategoryList(param));
		BookList bookList = null;
		if (item == null || item.trim().length() == 0) {
			bookList = new BookList(pageSize, mapper.selectCount(), currentPage);
			hmap.put("startNo", bookList.getStartNo());
			hmap.put("endNo", bookList.getEndNo());
			bookList.setList(mapper.selectList(hmap));
		} else {
			Param param2 = new Param();
			param2.setCategory(category);
			param2.setItem(item);
			bookList = new BookList(pageSize, mapper.selectMultiCount(param2), currentPage);
			param2.setStartNo(bookList.getStartNo());
			param2.setEndNo(bookList.getEndNo());
			bookList.setList(mapper.selectMultiList(param2));
		}
		model.addAttribute("bookBestList", bookBestList);
		model.addAttribute("bookDailyList", bookDailyList);
		model.addAttribute("bookNewList", bookNewList);
		model.addAttribute("bookCategoryList", bookCategoryList);
		model.addAttribute("currentpage", currentPage);
		model.addAttribute("bookList", bookList);
		model.addAttribute("enter", "\r\n");
		return "list" + list + "View";
	}
	
	@RequestMapping("/Search")
	public String Search(HttpServletRequest request, Model model) {
		logger.info("HomeController 클래스의 Search() 메소드 실행");
		String category = request.getParameter("category");
		String item = request.getParameter("item");
		if (item != null) {
			category = (String) model.getAttribute("category");
			item = item.trim().length() == 0 ? "" : item;
			item = (String) model.getAttribute("item");
		} else {
			category = (String) model.getAttribute("category");
			item = (String) model.getAttribute("item");
		}
		return "redirect:list";
	}
	
	@RequestMapping("/insertcommentOK")
	public String insertcommentOK(HttpServletRequest request, Model model, BookCommentVO co) {
		logger.info("HomeController 클래스의 insertcommentOK() 메소드 실행");
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/applicationCTX.xml");
		String check_memo = request.getParameter("memo");
		String nick = request.getParameter("nick");
		int ISBN = Integer.parseInt(request.getParameter("ISBN"));
		// logger.info(check_memo);
		try {
			int checkScore = Integer.parseInt(request.getParameter("score"));
			// System.out.println("checkscore: " + checkScore);
			if (check_memo == "") {
				request.setAttribute("msg", "후기를 입력해주세요.");
				return "alert";
			} else if (checkScore > 10 || checkScore < 0) {
				request.setAttribute("msg", "별점을 0 ~ 10 사이로 입력해 주세요.");
				return "alert";
			} else {
				co.setISBN(ISBN);
				co.setMemo(check_memo);
				co.setNick(nick);
				co.setScore(checkScore);
				mapper.insertComment(co);
				float avg = Float.parseFloat(request.getParameter("avg"));
				int size = Integer.parseInt(request.getParameter("size"));
				float score = co.getScore();
				avg = (avg * size + score) / (size + 1);
				Param param = new Param(co.getISBN(), avg);
				mapper.update(param);
			}
		} catch (Exception e) { 
			request.setAttribute("msg", "별점을 0 ~ 10 사이로 입력해 주세요.");
			return "alert";
		}
		return selectByISBN(request, model, null);
	}

	@RequestMapping("/deletecommentOK")
	public String deletecommentOK(HttpServletRequest request, Model model) {
		logger.info("HomeController 클래스의 insertcommentOK() 메소드 실행");
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		int idx = Integer.parseInt(request.getParameter("idx"));
		mapper.deleteComment(idx);
		int ISBN = Integer.parseInt(request.getParameter("ISBN"));
		float coscore = Float.parseFloat(request.getParameter("coscore"));
		int size = Integer.parseInt(request.getParameter("size"));
		float avg = Float.parseFloat(request.getParameter("avg"));
		avg = (avg * size - coscore) / (size - 1);
		Param param = new Param(ISBN, avg);
		mapper.update(param);
		return selectByISBN(request, model, null);
	}
	
	@RequestMapping("/updatecommentOK")
	public String updatecommentOK(HttpServletRequest request, Model model) {
		logger.info("HomeController 클래스의 insertcommentOK() 메소드 실행");
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		int idx = Integer.parseInt(request.getParameter("idx"));
		BookCommentVO co = mapper.selectcommentByIdx(idx);
		float coscore = Float.parseFloat(request.getParameter("coscore"));
		int size = Integer.parseInt(request.getParameter("size"));
		float avg = Float.parseFloat(request.getParameter("avg"));
		model.addAttribute("coscore", coscore);
		model.addAttribute("size", size);
		model.addAttribute("avg", avg);
		model.addAttribute("co", co);
		return "updatecomment";
	}
	
	@RequestMapping("/updatecommentOK2")
	public String updatecommentOK2(HttpServletRequest request, Model model) {
		logger.info("HomeController 클래스의 insertcommentOK() 메소드 실행");
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		String check_memo = request.getParameter("memo");
		try {
			int checkScore = Integer.parseInt(request.getParameter("score"));
			if (check_memo == "") {
				request.setAttribute("msg", "후기를 입력해주세요.");
				return "alert";
			} else if (checkScore > 10 || checkScore < 0) {
				request.setAttribute("msg", "별점을 0 ~ 10 사이 정수로 입력해 주세요.");
				return "alert";
			} else {
				int idx = Integer.parseInt(request.getParameter("idx"));
				String memo = request.getParameter("memo");
				float score = Float.parseFloat(request.getParameter("score"));
				Param param = new Param(idx, memo, score);
				mapper.updateComment(param);
				int ISBN = Integer.parseInt(request.getParameter("ISBN"));
				float coscore = Float.parseFloat(request.getParameter("coscore"));
				int size = Integer.parseInt(request.getParameter("size"));
				float avg = Float.parseFloat(request.getParameter("avg"));
				avg = (avg * size - coscore + score) / size;
				Param param2 = new Param(ISBN, avg);
				mapper.update(param2);
			}
		} catch (Exception e) { 
			request.setAttribute("msg", "별점을 0 ~ 10 사이 정수로 입력해 주세요.");
			return "alert";
		}
		return selectByISBN(request, model, null);
	}
	
}























