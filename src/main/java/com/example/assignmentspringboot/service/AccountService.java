package com.example.assignmentspringboot.service;

import com.example.assignmentspringboot.repository.AccountRepository;
import com.example.assignmentspringboot.entity.Account;
import com.example.assignmentspringboot.entity.dto.AccountDto;
import com.example.assignmentspringboot.entity.dto.CredentialDto;
import com.example.assignmentspringboot.repository.AccountRepository;
import com.example.assignmentspringboot.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    final AccountRepository accountRepository;

    final PasswordEncoder passwordEncoder;

    public Account register(AccountDto accountDto) {
       Account account = Account.builder()
               .username(accountDto.getUsername())
               .fullName(accountDto.getFullName())
               .password(passwordEncoder.encode(accountDto.getPassword())) // ma hoa mat khau
               .build();
       return accountRepository.save(account);
    }

    public Account login() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found in database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new User(account.getUsername(), account.getPassword(), authorities);
    }

    public boolean matchPassword(String rawPassword, String hashPassword){
        return passwordEncoder.matches(rawPassword, hashPassword);
    }

    public CredentialDto generateCredential(UserDetails userDetail, HttpServletRequest request) {
        String accessToken = JWTUtil.generateToken(userDetail.getUsername(),
                userDetail.getAuthorities().iterator().next().getAuthority(),
                request.getRequestURI().toString(),
                JWTUtil.ONE_DAY * 7);

        String refreshToken = JWTUtil.generateToken(userDetail.getUsername(),
                userDetail.getAuthorities().iterator().next().getAuthority(),
                request.getRequestURI().toString(),
                JWTUtil.ONE_DAY * 14);
        return new CredentialDto(accessToken, refreshToken);
    }


}
