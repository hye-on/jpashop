package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)//ManyToOne은 기본값이 EAGER라서 바꿔줘야한다.
    @JoinColumn(name = "member_id") //매핑을 뭘로 할거냐
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;//주문시간


    @Enumerated(EnumType.STRING)
    private OrderStatus status;//주문상태 [ORDER,CANCEL]

    //==연관관계 메서드==// 원자적으로 묶는다.
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
    //==생성 메서드==//
    public  static Order createOrder(Member member,Delivery delivery,OrderItem...orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비지니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel(){
        if(delivery.getStatus()==DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가느합니다.");

        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem :orderItems){
            orderItem.cancel();//한번 주문 할떄 두개 주문 할 수도 있으니까 두개 다 캔슬해준다.
        }
    }
    //==조회 로직==//

    /**
     *주문상품 전체 가격 조회
     */

    public int getTotalPrice(){
       int totalPrice=0;
       for(OrderItem orderItem : orderItems){
           totalPrice +=orderItem.getTotalPrice();
       }
       return totalPrice;
    }
}

