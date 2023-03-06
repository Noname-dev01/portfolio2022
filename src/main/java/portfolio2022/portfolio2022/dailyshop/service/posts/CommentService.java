package portfolio2022.portfolio2022.dailyshop.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cs.Comment;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cs.Posts;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.repository.MemberRepository;
import portfolio2022.portfolio2022.dailyshop.repository.cs.CommentRepository;
import portfolio2022.portfolio2022.dailyshop.repository.cs.PostsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostsRepository postsRepository;


    /**
     * 댓글 등록
     */
    @Transactional
    public void commentRegister(Member member, String comment, Posts posts){
        Comment comments = new Comment();
        comments.setComment(comment);
        comments.setMember(member);
        comments.setPosts(posts);
        commentRepository.save(comments);
    }

    /**
     * 게시글 별로 댓글 조회
     */
    public List<Comment> findCommentByPostId(Long postId){
        return commentRepository.findCommentByPostsId(postId);
    }

    /**
     * 댓글 삭제
     */
    public void commentDelete(Long commentId){commentRepository.deleteById(commentId);}

    /**
     * 댓글 한건 조회
     */
    public Comment findComment(Long commentId){
        return commentRepository.findById(commentId).get();
    }
}
