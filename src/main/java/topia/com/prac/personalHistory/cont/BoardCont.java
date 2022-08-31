package topia.com.prac.personalHistory.cont;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import topia.com.prac.entity.Page;

public interface BoardCont {
	
	String home( HttpServletRequest request, Model model, Page page);
	

	
	
}
