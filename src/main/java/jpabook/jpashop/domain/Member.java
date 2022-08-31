package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    //식별자를 Id맵핑을 해주고 그 다음에 GeneratedValue로 해서 자동생성되게 한다.
    @Id @GeneratedValue
    @Column(name="member_id")
    private  Long id;
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")     //연관관계 거울을 나타냄. Order테이블에 있는 member 필드에 의해 매핑됨. 읽기전용
   private List<Order> orders= new ArrayList<>();
}
