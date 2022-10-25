package MobileShop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MobileShop.Dao.RoleDAO;
import MobileShop.Entity.Role;
import MobileShop.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDAO roleDao;

	@Override
	public List<Role> getListRole() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}
}
