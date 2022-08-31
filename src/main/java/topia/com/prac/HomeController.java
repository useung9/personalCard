package topia.com.prac;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		//<!-- 다른 폼을 이용한.  idx값 클릭시 실행 homectroller -> 유저 idx값 세팅 - home.jsp에서 해당 값 유무 확인 있다면 정보조회 -->
		
		
		String userIdx = request.getParameter("userIdx"); 
		System.out.println(userIdx);
		
		if(userIdx == null ) {
			return "home";
			}
		else {
		model.addAttribute("userIdx", userIdx);
		return "home";
		}
	}
	
}
