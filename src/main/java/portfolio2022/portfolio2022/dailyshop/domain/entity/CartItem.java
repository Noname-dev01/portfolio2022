package portfolio2022.portfolio2022.dailyshop.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CartItem {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int count; //상품 개수

    public static CartItem createCartItem(Cart cart, Product product, int amount){
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setCount(amount);
        return cartItem;
    }

    //이미 카트에 있는 상품 추가 할 경우 수량 증가
    public void addCount(int count) {this.count +=count;}

}
