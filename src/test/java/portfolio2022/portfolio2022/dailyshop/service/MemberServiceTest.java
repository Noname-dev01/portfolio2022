package portfolio2022.portfolio2022.dailyshop.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import portfolio2022.portfolio2022.dailyshop.domain.member.Member;
import portfolio2022.portfolio2022.dailyshop.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

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
                .userId("kim")
                .password("1234")
                .build();
        //when
        Long saveId = memberService.join(member);
        //then
        Assertions.assertThat(member.getId()).isEqualTo(saveId);
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = Member.builder()
                .userId("kim")
                .build();

        Member member2 = Member.builder()
                .userId("kim")
                .build();
        //when
        memberService.join(member1);
//        memberService.join(member2); //중복 예외 발생해야함.
        //then
        Assertions.assertThatThrownBy(()->memberService.join(member2))
                .isInstanceOf(IllegalStateException.class);
    }


}