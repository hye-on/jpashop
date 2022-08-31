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


    protected  Address()//JPA 스펙상 만든것것
    {
    }

    //생성할 때만 값을 세팅.변경 불가능
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
