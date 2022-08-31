package topia.com.prac.personalHistory.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import topia.com.prac.entity.Page;

@Repository
public class BoardDAO {

	@Autowired
	private  SqlSessionTemplate tpl;
	
	
	// 이력서전체 리스트
	public List<Object> getPersonalHistoryList(Page page) {
		
		return tpl.selectList("board.boardlist", page);
	}
	
	
	

	// 이력서 총 수
	public int getTotalCnt() {
		return tpl.selectOne("board.getTotalCnt");
	}
	// 이력서 총 수
	public int cnt(Page page) {
		return tpl.selectOne("board.cnt", page);
	}
}
