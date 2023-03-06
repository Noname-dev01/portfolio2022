package portfolio2022.portfolio2022.dailyshop.controller.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cs.Comment;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cs.Posts;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.security.service.MemberDetails;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;
import portfolio2022.portfolio2022.dailyshop.service.posts.CommentService;
import portfolio2022.portfolio2022.dailyshop.service.posts.PostsService;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class PostsController {

    private final PostsService postsService;
    private final MemberService memberService;
    private final CommentService commentService;

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
        postsService.postRegister(member,title,content);
        return "redirect:/dailyShop/mypage/posts";
    }

    /**
     * 댓글 작성
     */
    @PostMapping("/mypage/posts/view/{postId}/comment")
    public String commentRegister(@PathVariable("postId")Long postId,@AuthenticationPrincipal MemberDetails memberDetails,String comment){
        Member member = memberDetails.getMember();
        Posts post = postsService.findPost(postId);
        commentService.commentRegister(member,comment,post);
        return "redirect:/dailyShop/mypage/posts/view/{postId}";
    }

    /**
     * 댓글 삭제
     */
    @GetMapping("/mypage/comment/delete/{postId}/{commentId}")
    public String commentDelete(@PathVariable("postId")Long postId,@PathVariable("commentId")Long commentId,@AuthenticationPrincipal MemberDetails memberDetails){
        Comment comment = commentService.findComment(commentId);
        if (Objects.equals(memberDetails.getMember().getId(), comment.getMember().getId())) {
            commentService.commentDelete(commentId);
        }
        return "redirect:/dailyShop/mypage/posts/view/{postId}";
    }

    /**
     * 게시글 상세보기
     */
    @GetMapping("/mypage/posts/view/{postId}")
    public String postView(@PathVariable("postId")Long postId, Model model, @AuthenticationPrincipal MemberDetails memberDetails){
        Long loginId = memberDetails.getMember().getId();
        Member member = memberService.findMember(loginId);
        Posts post = postsService.findPost(postId);
        postsService.viewPlus(postId);
        List<Comment> comments = commentService.findCommentByPostId(postId);

        model.addAttribute("member",member);
        model.addAttribute("post",post);
        model.addAttribute("comments",comments);
        return "dailyshop/posts/posts-view";
    }
}
