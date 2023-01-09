package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {
    //상품 공통 속성
    private Long id;
    private  String name;
    private int price;
    private int stockQuantity;

    //책과 관련되 특별한 속성
    private String author;
    private String isbn;
}
