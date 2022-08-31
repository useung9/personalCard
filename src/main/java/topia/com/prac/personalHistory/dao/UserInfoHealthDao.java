package topia.com.prac.personalHistory.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoHealthDao {

	@Autowired
	private SqlSessionTemplate tpl;

	public Integer insert(Map<String, Object> paramMap) {
		return tpl.insert("health.insert", paramMap);
	}

	


	public List<Object> selectone(String userIdx) {
		
		
		return tpl.selectList("health.selectone", userIdx);
	}




	public int delete(HashMap<String, Object> userIdx) {
		return tpl.delete("health.delete", userIdx);
		
	}

	/*
	 * update delete select
	 */
}
