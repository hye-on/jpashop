package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue //식별자를 Id맵핑을 해주고 그 다음에 GeneratedValue로 해서 자동생성되게 한다.
    private long id;
    private String username;


}
