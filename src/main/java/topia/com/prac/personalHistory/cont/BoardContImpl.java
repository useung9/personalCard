package topia.com.prac.personalHistory.cont;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import topia.com.prac.entity.Page;
import topia.com.prac.personalHistory.serv.BoardServImpl;


@Controller
public class BoardContImpl implements BoardCont{
	private static final Logger log = LoggerFactory.getLogger(BoardContImpl.class);
	
	
	@Autowired
	private  BoardServImpl boardserv;
	

	
// 기본로딩	
	
	//@Override
	@RequestMapping(value="/board" )
	public String home(HttpServletRequest request, Model model,@ModelAttribute("page") Page page) {
		log.info("home 메소드 ");
		//Page page = new Page();
		// modelattribute : get/set 매핑
		
		log.info("Page 기본값 : "+page + "\n curpage : " + page.getCurPage());
		

		System.out.println("get value / "+request.toString());
		
		List<Object>  userList = boardserv.getPersonalHistoryList(page);
		System.out.println("항목수 "+ userList.size());
		log.info("서비스 페이지값 넘어왔을때 " +page);
		log.info("데이터 : "+userList);
		int totcnt = boardserv.cnt(page);
		model.addAttribute("page",page);
		model.addAttribute("totcnt", totcnt);
		request.setAttribute("userList", userList);
		
		return "board/boardhome";
		
	}
	
	
	



	



	
	
	
	
}
