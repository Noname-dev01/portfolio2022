package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Order;
import portfolio2022.portfolio2022.dailyshop.domain.entity.OrderItem;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Product;
import portfolio2022.portfolio2022.dailyshop.security.service.MemberDetails;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;
import portfolio2022.portfolio2022.dailyshop.service.OrderSearch;
import portfolio2022.portfolio2022.dailyshop.service.OrderService;
import portfolio2022.portfolio2022.dailyshop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ProductService productService;

    /**
     * 주문 내역 조회
     */
    @GetMapping("/mypage/orderHistory/{id}")
    public String orderList(@PathVariable("id")Long id, @AuthenticationPrincipal MemberDetails memberDetails,Model model){
        if (Objects.equals(memberDetails.getMember().getId(), id)){
            List<OrderItem> orderItems = orderService.findMemberOrderItems(id);

            model.addAttribute("totalPrice", orderService.totalPrice(id));
            model.addAttribute("orderItems",orderItems);
            model.addAttribute("member",memberService.findMember(id));
        }
        return "dailyshop/order-list";
    }

    /**
     * 상세페이지에서 주문하기
     */
    @Transactional
    @PostMapping("/product/view/{productId}/member/{id}")
    public String buyProduct(@PathVariable("productId")Long productId, @PathVariable("id")Long id, @AuthenticationPrincipal MemberDetails memberDetails,int count){
        if (Objects.equals(memberDetails.getMember().getId(), id)){
            //주문 상품 조회
            Product product = productService.findProduct(productId);
            //회원 조회
            Member member = memberService.findMember(id);
            //재고 확인
            if (product.getStockQuantity() == 0 || product.getStockQuantity() < count){
                return "redirect:/dailyShop/main";
            }
            if (member.getCoin() < product.getPrice() * count){
                return "redirect:/dailyShop/mypage/{id}"; //충전하기 페이지로 이동하게 해야됨 일단은 마이페이지로 이동하게 해둠
            }else {
                //회원 포인트에서 결제금액만큼 차감
                member.setCoin(member.getCoin() - product.getPrice() * count);
                orderService.order(id, productId, count);
                return "redirect:/dailyShop/mypage/orderHistory/{id}";
            }
        }
        return "redirect:/dailyShop/main";
    }
    /**
     * 주문 취소하기
     */
    @PostMapping("/mypage/orderHistory/{id}/{orderId}")
    public String cancelOrder(@PathVariable("id")Long id,@PathVariable("orderId")Long orderId,@AuthenticationPrincipal MemberDetails memberDetails){
        if (Objects.equals(memberDetails.getMember().getId(), id)) {
                Order order = orderService.findOrder(orderId);
                orderService.cancelOrder(order);
            }
        return "redirect:/dailyShop/mypage/orderHistory/{id}";
    }

    /**
     * 주문내역 삭제하기
     */
    @GetMapping("/mypage/orderHistory/delete/{id}/{orderId}")
    public String deleteOrder(@PathVariable("orderId") Long orderId,@PathVariable("id") Long id,@AuthenticationPrincipal MemberDetails memberDetails){
        if (Objects.equals(memberDetails.getMember().getId(), id)) {
            orderService.deleteOrder(orderId);
        }
        return "redirect:/dailyShop/mypage/orderHistory/{id}";
    }
}
