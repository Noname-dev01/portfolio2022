package portfolio2022.portfolio2022.dailyshop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio2022.portfolio2022.dailyshop.domain.member.Member;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    List<Member> findByName = new ArrayList<>();
}
