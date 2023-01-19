package portfolio2022.portfolio2022.dailyshop.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.repository.MemberRepository;


@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);
        if (member == null){
//            if (memberRepository.countByUsername(username) == 0){
//                throw new UsernameNotFoundException("No user found with username: "+username);
//            }
            throw new UsernameNotFoundException("No user found with username: "+username);
        }else {
            return new MemberDetails(member);
        }
    }
}
