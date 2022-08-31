package topia.com.prac.personalHistory.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import lombok.extern.slf4j.*;
import topia.com.prac.entity.*;

@Repository
@Slf4j
public class UserInfoCareerDao {
	
	@Autowired
	private SqlSessionTemplate tpl;
	
	public Integer insert(Map<String, Object> paramMap) {
		//log.info("==================================={}",paramMap);
		return tpl.insert("careerDao.insert", paramMap);
	}
	
	public Integer updateUserCareer(UserInfoCareer userInfoCareer) {
		return tpl.update("careerDao.updateUserCareer", userInfoCareer);
	}

	public List<UserInfoCareer> list(int intUserIdx) {
		return tpl.selectList("careerDao.list", intUserIdx);
	}

}
