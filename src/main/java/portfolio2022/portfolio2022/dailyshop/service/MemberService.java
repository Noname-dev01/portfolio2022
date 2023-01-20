package portfolio2022.portfolio2022.dailyshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.exception.DuplicateMemberException;
import portfolio2022.portfolio2022.dailyshop.repository.MemberRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CartService cartService;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증
        Member memberEntity = memberRepository.save(member);
        if (Objects.equals(memberEntity.getRole(), "ROLE_USER")){
            cartService.createCart(member);
        }
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new DuplicateMemberException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 id로 찾기
    public Member findMember(Long memberId){return memberRepository.findById(memberId).get();}

    /**
     * 회원 정보 수정
     */
    @Transactional
    public void memberModify(Member member){
        Member memberUpdate = memberRepository.findById(member.getId()).get();
        memberUpdate.setName(member.getName());
        memberUpdate.setEmail(member.getEmail());
        memberUpdate.setPhone(member.getPhone());
        memberUpdate.setAddress(member.getAddress());
    }
}
