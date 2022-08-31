package topia.com.prac.personalHistory.serv;

import java.util.HashMap;
import java.util.List;

import topia.com.prac.entity.Page;

public interface BoardServ {
// 전체 리스트 조회
	List<Object> getPersonalHistoryList(Page page);
	
	
	int cnt(Page page);

}
