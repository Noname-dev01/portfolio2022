package portfolio2022.portfolio2022.dailyshop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문 가격
    private int orderCount;//주문 수량

    //==생성 메서드==//
    public static OrderItem createOrderItem(Product product,Member member, int orderPrice, int orderCount){
        OrderItem orderItem = new OrderItem();
        orderItem.setMember(member);
        orderItem.setProduct(product);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setOrderCount(orderCount);

        product.minusStock(orderCount);
        return orderItem;
    }

    //== 비즈니스 로직 ==//
    public void cancel() {
        getProduct().addStock(orderCount);
        getMember().setCoin(getMember().getCoin()+getTotalPrice());
    }

    //== 조회 로직 ==//
    /**
     * 주문상품 전체 가격 조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getOrderCount();
    }
}
