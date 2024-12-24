package com.gsmct.security;

import com.gsmct.entities.Admin;
import com.gsmct.entities.User;
import com.gsmct.repositories.AdminRepository;
import com.gsmct.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gsmct.enums.Role.ADMIN;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Admin admin = adminRepository.findById(username).orElse(null);
    if (admin == null) {
      User user = userRepository.findByUsername(username).orElseThrow(() ->
        new UsernameNotFoundException("Admin Not Found with username: " + username));
      return new UserDetailsImpl(user.getUsername(), user.getPassword(), List.of());
    } else {
      return new UserDetailsImpl(admin.getEmail(), admin.getPassword(),
        List.of(new SimpleGrantedAuthority(ADMIN.name())));
    }
  }
}
