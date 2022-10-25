package MobileShop.service.impl;

import javax.transaction.Transactional;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import MobileShop.Dao.AccountDAO;
import MobileShop.Entity.Account;


@Service
public class AccountDetailsServiceImpl implements UserDetailsService{

	@Autowired
	AccountDAO accountDAO;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Account account = accountDAO.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: "+username));
		return AccountDetailsImpl.build(account);
	}

}