package portfolio2022.portfolio2022.dailyshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
//import portfolio2022.portfolio2022.dailyshop.repository.OrderRepository;


import javax.persistence.EntityManager;

//@SpringBootTest
//@Transactional
class OrderServiceTest {

//    @Autowired
//    EntityManager em;
//    @Autowired OrderService orderService;
//    @Autowired
//    OrderRepository orderRepository;

//    @Test
//    public void 상품주문() throws Exception {
//        //given
//        Member member = createMember();
//
//        Product product = createProduct(10, 10000, "시골 JPA");
//
//        int orderCount = 2;
//
//        //when
//        Long orderId = orderService.order(member.getId(), product.getId(), orderCount);
//        //then
//        Order getOrder = orderRepository.findById(orderId).get();
//
//        assertEquals(OrderStatus.ORDER, getOrder.getStatus(),"상품 주문시 상태는 ORDER");
//        assertEquals(1,getOrder.getOrderItems().size(),"주문한 상품 종류 수가 정확해야 한다.");
//        assertEquals(10000 * orderCount,getOrder.getTotalPrice(),"주문가격은 가격 * 수량이다.");
//        assertEquals(8,product.getStockQuantity(),"주문 수량만큼 재고가 줄어야 한다.");
//    }

//    @Test
//    public void 상품주문_재고수량초과() throws Exception {
//        //given
//        Member member = createMember();
//        Product item = createProduct(10, 10000, "시골 JPA");
//
//        int orderCount = 11;
//        //when
//
//        Assertions.assertThatThrownBy(()->orderService.order(member.getId(), item.getId(), orderCount),"재고 수량 부족 예외가 발생해야 한다.")
//                .isInstanceOf(NotEnoughStockException.class);
//        //then
//    }

//    @Test
//    public void 주문취소() throws Exception {
//        //given
//        Member member = createMember();
//        Product item = createProduct(10,10000,"시골 JPA");
//
//        int orderCount = 2;
//        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
//        //when
//        orderService.cancelOrder(orderId);
//        //then
//        Order getOrder = orderRepository.findById(orderId).get();
//
//        assertEquals(OrderStatus.CANCEL,getOrder.getStatus(),"주문 취소시 상태는 CANCEL이어야 한다.");
//        assertEquals(10,item.getStockQuantity(),"주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");
//    }

//    private Product createProduct(int stockQuantity, int price, String name) {
//        Product product = new Product();
//        product.setName(name);
//        product.setPrice(price);
//        product.setStockQuantity(stockQuantity);
//        em.persist(product);
//        return product;
//    }

//    private Member createMember() {
//        Member member = new Member("회원1",new Address("123","123","123"));
//        em.persist(member);
//        return member;
//    }
}