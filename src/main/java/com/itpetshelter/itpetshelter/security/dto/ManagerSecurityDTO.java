package com.itpetshelter.itpetshelter.security.dto;

//시큐리티 필터 설정이 되어 있고,
// 로그인 처리를 우리가 하는게 아니라, 시큐리티가 함.
// 시큐리티는 그냥 클래스를 요구하지 않고,
// 자기들이 정해둔 룰. UserDetails 를 반환하는 클래스를 요구를 해요.
// 시큐리티에서 정의해둔 특정 클래스를 상속을 받으면 됨.

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
//@AllArgsConstructor
// @AllArgsConstructor 대신에 권한도, 시큐리티에서 가져와서, 사용자정의해야하서.
public class ManagerSecurityDTO extends User {
    private String Mid;
    private String Mpw;
    private String Mname;


    //생성자
    public ManagerSecurityDTO(
            //로그인한 유저이름.
            String username,String password,String name,
            //GrantedAuthority 를 상속한 클래스는 아무나 올수 있다. 타입으로
            Collection<? extends GrantedAuthority> authorities
    ){
        super(username, password, authorities);
        this.Mid = username;
        this.Mpw = password;
        this.Mname = name;

    }
}