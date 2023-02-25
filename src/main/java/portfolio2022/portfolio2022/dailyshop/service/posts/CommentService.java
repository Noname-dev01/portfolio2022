package portfolio2022.portfolio2022.dailyshop.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio2022.portfolio2022.dailyshop.repository.MemberRepository;
import portfolio2022.portfolio2022.dailyshop.repository.cs.CommentRepository;
import portfolio2022.portfolio2022.dailyshop.repository.cs.PostsRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostsRepository postsRepository;


}
