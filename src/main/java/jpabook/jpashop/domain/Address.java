package jpabook.jpashop.domain;

import lombok.Getter;


import javax.persistence.Embeddable;

//jpa의 내장 타입 ->어디인가에 내장이 될 수 있다.
@Embeddable
@Getter
public class Address {

    private  String city;
    private String street;
    private String zipcode;
}
