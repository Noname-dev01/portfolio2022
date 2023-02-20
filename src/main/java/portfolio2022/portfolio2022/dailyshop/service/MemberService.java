package portfolio2022.portfolio2022.dailyshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cart;
import portfolio2022.portfolio2022.dailyshop.domain.entity.ChargeList;
import portfolio2022.portfolio2022.dailyshop.domain.entity.ChargeStatus;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.exception.DuplicateMemberException;
import portfolio2022.portfolio2022.dailyshop.repository.CartRepository;
import portfolio2022.portfolio2022.dailyshop.repository.chargeList.ChargeListRepository;
import portfolio2022.portfolio2022.dailyshop.repository.MemberRepository;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final ChargeListRepository chargeListRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증
        Member memberEntity = memberRepository.save(member);
        if (Objects.equals(memberEntity.getRole(), "ROLE_USER")){
            Cart cart = Cart.createCart(member);
            cartRepository.save(cart);
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
    /**
     * 권한 정보 수정
     */
    @Transactional
    public void memberRoleChange(Long id,String role){
        Member memberUpdate = memberRepository.findById(id).get();
        memberUpdate.setRole(role);
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }
    /**
     * 관리자 권한으로 충전하기
     */
    @Transactional
    public void chargePoint(Long id,int amount){
        Member member = memberRepository.findById(id).get();
        member.setCoin(member.getCoin()+amount);

    }
    /**
     * 회원이 충전요청 하기
     */
    @Transactional
    public void mypageCharge(Long id,int amount){
        Member member = memberRepository.findById(id).get();
        ChargeList chargeList = new ChargeList();
        chargeList.setMember(member);
        chargeList.setChargeStatus(ChargeStatus.READY);
        chargeList.setChargeAmount(amount);
        chargeListRepository.save(chargeList);
    }

    /**
     * 충전 요청 결과 리스트
     */
    public List<ChargeList> memberChargeList(Long id){
        return chargeListRepository.findChargeListByMemberId(id);
    }
}
