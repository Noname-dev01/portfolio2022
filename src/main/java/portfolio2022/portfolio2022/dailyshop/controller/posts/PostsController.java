package portfolio2022.portfolio2022.dailyshop.controller.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cs.Posts;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.security.service.MemberDetails;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;
import portfolio2022.portfolio2022.dailyshop.service.posts.PostsService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class PostsController {

    private final PostsService postsService;
    private final MemberService memberService;

    /**
     * 게시판 리스트
     */
    @GetMapping("/mypage/posts")
    public String postsList(Model model, @AuthenticationPrincipal MemberDetails memberDetails){
        Long loginId = memberDetails.getMember().getId();
        Member member = memberService.findMember(loginId);
        List<Posts> postsList = postsService.postsAllView();

        model.addAttribute("member",member);
        model.addAttribute("postsList",postsList);
        return "dailyshop/posts/posts-list";
    }

    /**
     * 게시글 작성
     */
    @GetMapping("/mypage/posts/register")
    public String postRegister(Model model,@AuthenticationPrincipal MemberDetails memberDetails){
        Long loginId = memberDetails.getMember().getId();
        Member member = memberService.findMember(loginId);

        model.addAttribute("member",member);
        return "dailyshop/posts/posts-register";
    }
    @PostMapping("/mypage/posts/register")
    public String postRegister(@AuthenticationPrincipal MemberDetails memberDetails,String title,String content){
        Member member = memberDetails.getMember();
        postsService.productRegister(member,title,content);
        return "redirect:/dailyShop/mypage/posts";
    }
}
