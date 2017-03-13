package com.ryan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ryan.domain.User;
import com.ryan.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("username：{} 验证开始",username);
        User user = userRepository.findByUsername(username);
        log.info("user find by username: {}",user);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在！");
        } else if (!user.isEnabled()) {
            throw new DisabledException("用户被禁用！");
        } else if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException("用户已过期！");
        } else if (!user.isAccountNonLocked()) {
            throw new LockedException("用户已被锁定！");
        } else if (!user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("凭证过期！");
        }
        log.info("user权限：{}",user.getAuthorities());
        return user;
	}

}
