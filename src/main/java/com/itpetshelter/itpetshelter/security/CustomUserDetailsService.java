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
