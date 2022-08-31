package topia.com.prac.personalHistory.serv;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import topia.com.prac.entity.Page;
import topia.com.prac.personalHistory.dao.BoardDAO;

@Service
public class BoardServImpl implements BoardServ{

	@Autowired
	private  BoardDAO bdao;
	
	
	

	// 이력서전체 리스트
	@Override
	public List<Object> getPersonalHistoryList(Page page) {
	
		
		page = pagging(page);
		
		System.out.println("@@@@@@@@@@@@@@ 서비스 페이지 : " + page);
		System.out.println("총 데이터 "+ cnt(page));
		List<Object>  list = bdao.getPersonalHistoryList(page); 
		System.out.println(list);
		return list;
		
	}
	

	
	
	// 공용 페이징 처리
	public Page pagging(Page page) {
		System.out.println("Pagging - page : "+ page);
		// 페이징 설정
				int curPage = page.getCurPage();
				int perPage = page.getPerPage();
				int perBlock = page.getPerBlock();
				
				//  게시물 시작번호
				int startNum = (curPage-1) * perPage +1;
				
				// 게시물 끝번호
				int endNum = startNum+perPage-1;
				
				// 페이지 블럭 시작페이지
				int startPage = curPage-((curPage-1)%perBlock);
				// 페이지 블럭 끝페이지
				int endPage = startPage+perBlock-1;
				
				// 전체 페이지 수
				int totcnt= bdao.cnt(page);
				
				int totPage = totcnt/perPage;
				if(totcnt%perPage !=0 ) totPage++; // 나눈 나머지가 잇다면 페이지 1추가
				if(endPage > totPage) endPage = totPage;
				// end page 는 totpage 와 동일하거나 작어야함.
				
				// 페이지 세팅
				page.setStartNum(startNum);
				page.setEndNum(endNum);
				page.setStartPage(startPage);
				page.setEndPage(endPage);
				page.setTotPage(totPage);
				page.setTotcnt(totcnt);
			return page;
	}




	@Override
	public int cnt(Page page) {

		return bdao.cnt(page);
	}






}
