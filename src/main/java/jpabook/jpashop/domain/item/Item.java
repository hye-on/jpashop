package jpabook.jpashop.domain.item;


import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//추상 클래스
//싱글테이블 전략을 쓰기 때문에 전략을 부모 클래스에 잡아줘야함
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    //상속관계 매핑
   @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

   //공통 속성
   private  String name;
   private  int price;
   private  int stockQuantity;


   @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

   //==비지니스 로직==//

    /**
     * 재고 수량 증가 (stock)
     */
    public  void addStock(int quantity){//여기에 있는 것이 좋다.
        this.stockQuantity+=quantity;
    }

    /**
     * stock 감수
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity-quantity;
        if(restStock<0){
            throw new NotEnoughStockException ("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
