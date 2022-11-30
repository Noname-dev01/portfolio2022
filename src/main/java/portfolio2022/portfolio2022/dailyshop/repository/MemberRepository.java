package portfolio2022.portfolio2022.dailyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio2022.portfolio2022.dailyshop.domain.member.Member;


@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
}
