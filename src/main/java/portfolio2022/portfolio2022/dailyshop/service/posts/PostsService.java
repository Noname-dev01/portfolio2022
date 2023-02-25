package portfolio2022.portfolio2022.dailyshop.service.posts;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ManyToAny;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cs.Posts;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.repository.cs.PostsRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    public List<Posts> postsAllView(){
        return postsRepository.findAll();
    }

    @Transactional
    public void productRegister(Member member,String title,String content){
        Posts posts = new Posts();
        posts.setTitle(title);
        posts.setMember(member);
        posts.setContent(content);
        posts.setView(0);
        posts.setWriter(member.getUsername());
        postsRepository.save(posts);
    }
}
