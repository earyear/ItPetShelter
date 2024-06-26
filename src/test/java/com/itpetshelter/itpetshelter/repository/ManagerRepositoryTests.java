package com.itpetshelter.itpetshelter.repository;


import com.itpetshelter.itpetshelter.domain.Manager;
import com.itpetshelter.itpetshelter.domain.MemberRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ManagerRepositoryTests {
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertConsumerTest() {
        // 샘플로 100명의 더미 디비 넣기. 병렬처리
        IntStream.rangeClosed(1,100).forEach(i ->{
            Manager manager = Manager.builder()
                    .Mid("lsy"+i)
                    // 주의사항, 멤버 넣을 때, 패스워드 평문 안됨, 암호화 필수.
                    .Mpw(passwordEncoder.encode("1234"))
                    .Mname("이상용"+i)
                    .build();
            // 권한주기. USER, ADMIN
            manager.addRole(MemberRole.ADMIN);
            // 90번 이상부터는, 동시권한, USER 이면서 ADMIN 주기.


            // 엔티티 클래스를 저장, 실제 디비 반영이되는 비지니스 모델.
            managerRepository.save(manager);

        });
    } // 닫기

    @Test
    public void testRead() {
        Optional<Manager> result = managerRepository.getWithRoles("lsy");
        Manager manager = result.orElseThrow();

        log.info("ManagerRepositoryTests testRead, manager:  "+ manager);
        log.info("ManagerRepositoryTests testRead, manager.getRoleSet():  "+ manager.getRoleSet());

        manager.getRoleSet().forEach(memberRole -> {
            log.info("ConsumerRepositoryTests testRead, memberRole:  "+memberRole);
        });
    }




    }


