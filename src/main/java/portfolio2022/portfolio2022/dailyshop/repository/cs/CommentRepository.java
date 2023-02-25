package portfolio2022.portfolio2022.dailyshop.repository.cs;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cs.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
