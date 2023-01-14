package portfolio2022.portfolio2022.dailyshop.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Address;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.exception.DuplicateMemberException;
import portfolio2022.portfolio2022.dailyshop.repository.MemberRepository;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = Member.builder()
                .username("kim")
                .password("1234")
                .name("kim")
                .build();
        //when
//        Long saveId = memberService.join(member);
        //then
//        Assertions.assertThat(member.getId()).isEqualTo(saveId);
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = Member.builder()
                .username("kim")
                .password("1234")
                .name("kim")
                .address(new Address("asd","asd","asd"))
                .build();

        Member member2 = Member.builder()
                .username("kim")
                .password("1234")
                .name("kim")
                .address(new Address("asd","asd","asd"))
                .build();
        //when
        memberService.join(member1);
//        memberService.join(member2); //중복 예외 발생해야함.
        //then
        Assertions.assertThatThrownBy(()->memberService.join(member2))
                .isInstanceOf(DuplicateMemberException.class);
    }


}