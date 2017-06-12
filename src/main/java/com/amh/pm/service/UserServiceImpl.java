package com.amh.pm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amh.pm.dao.UserDao;
import com.amh.pm.entity.User;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public User findById(int userId) {
		// TODO Auto-generated method stub
		return userDao.findById(userId);
	}

	@Override
	public User userByName(String name, String password) {
		// TODO Auto-generated method stub
		return userDao.userByName(name, password);
	}

	@Override
	public List<User> findUserNameByOrgnId(int orgId) {
		// TODO Auto-generated method stub
		return userDao.findUserNameByOrgnId(orgId);
	}

	@Override
	public User findUserIdByName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findUserIdByName(userName);
	}

	
    
}
