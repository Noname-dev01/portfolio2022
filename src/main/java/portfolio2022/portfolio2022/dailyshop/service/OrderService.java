package portfolio2022.portfolio2022.dailyshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio2022.portfolio2022.dailyshop.domain.entity.*;
import portfolio2022.portfolio2022.dailyshop.repository.MemberRepository;
import portfolio2022.portfolio2022.dailyshop.repository.OrderItemRepository;
import portfolio2022.portfolio2022.dailyshop.repository.OrderRepository;
import portfolio2022.portfolio2022.dailyshop.repository.ProductRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId,Long productId,int count){

        //엔티티 조회
        Member member = memberRepository.findById(memberId).get();
        Product product = productRepository.findById(productId).get();

        //배송정보 생성
//        Delivery delivery = new Delivery();
//        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(product, member,product.getPrice(), count);

        //주문 생성
//        Order order = Order.createOrder(member, delivery, orderItem);
        Order order = Order.createOrder(member, orderItem);
        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Order cancelOrder){
        Order order = orderRepository.findById(cancelOrder.getId()).get();
        order.cancel();
    }
    /**
     * 주문 조회 (회원별 조회)
     */
    public List<OrderItem> findMemberOrderItems(Long id) {
        return orderItemRepository.findOrderItemsByMemberId(id);
    }

    /**
     * 전체 주문 가격 조회
     */
    public int totalPrice(Long id){
        List<OrderItem> orders = orderItemRepository.findOrderItemsByMemberId(id);
        int totalPrice = 0;
        for (OrderItem order : orders) {
            if (order.getOrder().getStatus() == OrderStatus.ORDER) {
                totalPrice += order.getTotalPrice();
            }
        }
        return totalPrice;
    }

    /**
     * 주문 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }

    /**
     * 주문 한개 찾기
     */
    public Order findOrder(Long orderId){
        return orderRepository.findById(orderId).get();
    }

    /**
     * 주문 상품 한개 찾기
     */
    public OrderItem findOrderItem(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).get();
    }

    /**
     * 주문 삭제
     */
    @Transactional
    public void deleteOrder(Long orderId){
        Order order = orderRepository.findById(orderId).get();
        if (order.getStatus() == OrderStatus.CANCEL){
            orderRepository.deleteById(orderId);
        }
    }
}
