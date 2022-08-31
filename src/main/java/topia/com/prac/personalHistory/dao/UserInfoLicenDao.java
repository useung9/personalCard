package topia.com.prac.personalHistory.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import topia.com.prac.entity.*;

@Repository
public class UserInfoLicenDao {

	@Autowired
	private SqlSessionTemplate tpl;
	
	public Integer insert(Map<String, Object> paramMap) {
		return tpl.insert("licenDao.insert", paramMap);
	}
	
	public Integer updateUserLicen(UserInfoLicen userInfoLicen) {
		return tpl.update("licenDao.updateUserLicen", userInfoLicen);
	}

	public List<UserInfoLicen> list(int intUserIdx) {
		return tpl.selectList("licenDao.list", intUserIdx);
	}
}
