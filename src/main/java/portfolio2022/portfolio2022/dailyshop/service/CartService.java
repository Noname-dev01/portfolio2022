package portfolio2022.portfolio2022.dailyshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cart;
import portfolio2022.portfolio2022.dailyshop.domain.entity.CartItem;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Product;
import portfolio2022.portfolio2022.dailyshop.repository.CartItemRepository;
import portfolio2022.portfolio2022.dailyshop.repository.CartRepository;
import portfolio2022.portfolio2022.dailyshop.repository.MemberRepository;
import portfolio2022.portfolio2022.dailyshop.repository.ProductRepository;
import portfolio2022.portfolio2022.dailyshop.security.service.MemberDetails;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    //?
    private final MemberRepository memberRepository;

    /**
     * 회원가입 하면 카트 하나 생성
     */
    public void createCart(Member member){
        Cart cart = Cart.createCart(member);
        cartRepository.save(cart);
    }

    /**
     * 카트에 담기
     */
    @Transactional
    public void addCart(Member member, Product newProduct,int amount){
        //유저 id로 해당 유저의 카트 찾기
        Cart cart = cartRepository.findByMemberId(member.getId());

        //카트가 없다면
        if (cart == null){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        Product product = productRepository.findProductById(newProduct.getId());
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());

        //상품이 카트에 존재하지 않으면 카트상품 생성 후 추가
        if (cartItem == null){
            cartItem = CartItem.createCartItem(cart,product,amount);
            cartItemRepository.save(cartItem);
        }
        //상품이 장바구니에 이미 존재한다면 수량만 추가
        else {
            cartItem.setCart(cartItem.getCart());
            cartItem.setProduct(cartItem.getProduct());
            cartItem.addCount(amount);
            cartItem.setCount(cartItem.getCount());
//            cartItemRepository.save(update);
        }

        //카트 상품 총 개수 증가
        cart.setCount(cart.getCount()+amount);
    }

    /**
     * 유저 id로 해당 유저의 카트 찾기
     */
    public Cart findMemberCart(Long memberId){
        return cartRepository.findCartByMemberId(memberId);
    }

    /**
     * 카트 상품 리스트 중 해당하는 유저가 담은 상품만 리턴
     * 유저의 카트 Id 와 카트상품의 카트 Id가 같아야 함
     */
    public List<CartItem> allUserCartView(Cart memberCart){
        //유저의 카트 id 조회
        Long memberCartId = memberCart.getId();

        //id에 해당하는 유저가 담은 상품들 넣어둘 곳
        List<CartItem> memberCartItems = new ArrayList<>();

        //유저 상관 없이 카트에 있는 모든 상품 불러오기
        List<CartItem> cartItems = cartItemRepository.findAll();

        for (CartItem cartItem : cartItems) {
            if (cartItem.getCart().getId() == memberCartId){
                memberCartItems.add(cartItem);
            }
        }
        return memberCartItems;
    }

    /**
     * 카트 상품 리스트 중 해당하는 상품 id의 상품들 반환
     */
    public List<CartItem> findCartItemByProductId(Long id){

        return cartItemRepository.findCartItemByProductId(id);
    }
    /**
     * 카트 상품 중 해당하는 상품 id의 상품 반환
     */
    public CartItem findCartItemById(Long id){
        return cartItemRepository.findCartItemById(id);
    }

    /**
     * 카트의 상품 개별 삭제
     */
    public void cartItemDelete(Long id){
        cartItemRepository.deleteById(id);
    }

    /**
     * 카트 삭제
     */
    public void cartDelete(Long id){
        cartRepository.deleteById(id);
    }

    /**
     * 카트의 상품 전체 삭제 -> 매개변수는 유저 id
     */
    public void allCartItemDelete(Long id){
        //전체 cartItem 조회
        List<CartItem> cartItems = cartItemRepository.findAll();

        //반복문을 사용하여 해당하는 회원의 cartItem 만 찾아서 삭제
        for (CartItem cartItem : cartItems) {
            if (cartItem.getCart().getMember().getId() == id){
                cartItem.getCart().setCount(0);
                cartItemRepository.deleteById(cartItem.getId());
            }
        }
    }
}
