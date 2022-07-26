package com.gentleduo.springmvc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gentleduo.springmvc.dao.UserMgrDao;
import com.gentleduo.springmvc.entity.User;
import com.gentleduo.springmvc.service.UserMgrService;

@Service("userMgrServiceImpl")
public class UserMgrServiceImpl implements UserMgrService {

	@Resource(name = "userMgrDaoImpl")
	private UserMgrDao userMgrDao;

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	public int addUser(User user) {

		int insres = 0;
		// TODO Auto-generated method stub
		insres = userMgrDao.insertUserInfo(user);
		return insres;
	}

}
