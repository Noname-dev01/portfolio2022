package portfolio2022.portfolio2022.dailyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio2022.portfolio2022.dailyshop.domain.Member;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByName(@NotEmpty String name);
}
