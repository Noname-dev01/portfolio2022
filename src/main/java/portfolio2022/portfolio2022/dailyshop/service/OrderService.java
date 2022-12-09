package portfolio2022.portfolio2022.dailyshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio2022.portfolio2022.dailyshop.domain.*;
import portfolio2022.portfolio2022.dailyshop.repository.MemberRepository;
import portfolio2022.portfolio2022.dailyshop.repository.OrderRepository;
import portfolio2022.portfolio2022.dailyshop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId,Long productId,int count){

        //엔티티 조회
        Member member = memberRepository.findById(memberId).get();
        Product product = productRepository.findById(productId).get();

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(product, product.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId).get();
        order.cancel();
    }

    /**
     * 주문 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }
}
