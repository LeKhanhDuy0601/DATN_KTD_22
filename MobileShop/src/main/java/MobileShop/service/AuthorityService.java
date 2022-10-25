package MobileShop.service;

import java.util.List;

import MobileShop.Entity.Authority;

public interface AuthorityService {

	List<Authority> findAll();

	Authority create(Authority authority);

	void delete(Integer id);

}
