package topia.com.prac.personalHistory.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import topia.com.prac.entity.*;

@Repository
public class UserInfoEduDao {

	@Autowired
	private SqlSessionTemplate tpl;
	
	public Integer insert(Map<String, Object> paramMap) {
		return tpl.insert("eduDao.insert", paramMap);
	}
	
	public Integer updateUserEdu(UserInfoEdu userInfoEdu) {
		return tpl.update("eduDao.updateUserEdu", userInfoEdu);
	}

	public List<UserInfoEdu> list(int intUserIdx) {
		return tpl.selectList("eduDao.list", intUserIdx);
	}
}
