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
	@Transactional
	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	@Transactional
	public User findById(int userId) {
		// TODO Auto-generated method stub
		return userDao.findById(userId);
	}

	@Override
	@Transactional
	public User userByName(String name, String password) {
		// TODO Auto-generated method stub
		return userDao.userByName(name, password);
	}

	@Override
	@Transactional
	public List<User> findUserNameByOrgnId(int orgId) {
		// TODO Auto-generated method stub
		return userDao.findUserNameByOrgnId(orgId);
	}

	@Override
	@Transactional
	public User findUserIdByName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findUserIdByName(userName);
	}

    @Override
    @Transactional
    public User findUserIdByEmail(String userEmail) {
        // TODO Auto-generated method stub
        return userDao.findUserIdByEmail(userEmail);
    }

	
    
}
