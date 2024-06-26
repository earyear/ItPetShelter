package com.itpetshelter.itpetshelter.security;

import com.itpetshelter.itpetshelter.domain.Consumer;
import com.itpetshelter.itpetshelter.domain.Manager;
import com.itpetshelter.itpetshelter.repository.ConsumerRepository;
import com.itpetshelter.itpetshelter.repository.ManagerRepository;
import com.itpetshelter.itpetshelter.security.dto.ConsumerSecurityDTO;
import com.itpetshelter.itpetshelter.security.dto.ManagerSecurityDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


//시큐리티 설정이 되면, 자동으로 로그인 로직처리를 해줘요.
// config
// http.formLogin(
//        formLogin -> formLogin.loginPage("/consumer/login").permitAll()
//        );
// 등록 된 로그인 페이지에서.
// post , 로그인 페이지.
// 로그인 폼에서, 아이디 이름: username  , 패스워드 : password,
// /consumer/login , post 로 보내면,
// 로직 처리를
// 여기서 해요.
// consumer -> /consumer/login
// manager -> /consumer/login

@Log4j2
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ManagerRepository managerRepository;

    public CustomUserDetailsService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("CustomUserDetailsService loadUserByUsername 확인 : " + username);

        Optional<Consumer> consumerResult = consumerRepository.getWithRoles(username);

        if (consumerResult.isPresent()) {
            Consumer consumer = consumerResult.get();
            ConsumerSecurityDTO consumerSecurityDTO = new ConsumerSecurityDTO(
                    consumer.getCid(),
                    consumer.getCpw(),
                    consumer.getEmail(),
                    consumer.isDel(),
                    false,
                    consumer.getUuid(),
                    consumer.getFileName(),
                    consumer.getRoleSet().stream().map(
                            memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name())
                    ).collect(Collectors.toList())
            );
            log.info("CustomUserDetailsService loadUserByUsername consumerSecurityDTO 확인 :" + consumerSecurityDTO);
            return consumerSecurityDTO;
        }

        Optional<Manager> managerResult = managerRepository.getWithRoles(username);

        if (managerResult.isPresent()) {
            Manager manager = managerResult.get();
            ManagerSecurityDTO managerSecurityDTO = new ManagerSecurityDTO(
                    manager.getMid(),
                    manager.getMpw(),
                    manager.getMname(),
                    manager.getRoleSet().stream().map(
                            memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name())
                    ).collect(Collectors.toList())
            );
            log.info("ManagerDetailsService loadUserByUsername managerSecurityDTO 확인 :" + managerSecurityDTO);
            return managerSecurityDTO;
        }

        throw new UsernameNotFoundException("유저가 존재하지 않습니다");
    }
}
