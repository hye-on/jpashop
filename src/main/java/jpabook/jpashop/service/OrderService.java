package jpabook.jpashop.service;


import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class OrderService {
    private  final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private  final ItemRepository itemRepository;
    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId,Long itemId,int count)
    {
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);
        //배송정보 생성   :원래는 deliveryRepository가 있어서 따로 생성해야한다.
        Delivery delivery =new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        //주문상품 생성     원래는 jpa에게 값을 넣어준 다음에 세팅해야한다.
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);//ctrl+space
        
        //주문 저장
        orderRepository.save(order);//cascade 옵션

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId)//Id만 넘어옴
    {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }

    //검색
    /*public List<Order> findOrders(OrderSearch orderSeach){
        return orderRepository.findAll(orderSeach);
    }*/



}
