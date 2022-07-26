package com.gentleduo.springmvc.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gentleduo.springmvc.common.core.dao.BaseDao;
import com.gentleduo.springmvc.dao.UserMgrDao;
import com.gentleduo.springmvc.entity.User;

@Repository("userMgrDaoImpl")
public class UserMgrDaoImpl extends BaseDao implements UserMgrDao {

	@Override
	public int insertUserInfo(User user) {

		SqlSessionTemplate session = getSessionTemplate();
		/*return session.insert(getStatement("insertUserInfo"), user);*/
		return session.insert("usermgr.insertUserInfo", user);
		// session.selectOne("");
	}

}
