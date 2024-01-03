package portfolio2022.portfolio2022.dailyshop.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Role;
import portfolio2022.portfolio2022.dailyshop.repository.MemberRepository;
import portfolio2022.portfolio2022.dailyshop.security.config.OAuth2UserInfo;

import java.time.LocalDateTime;
import java.util.Map;

import static portfolio2022.portfolio2022.dailyshop.security.config.OAuth2UserInfo.*;

@Service
@RequiredArgsConstructor
public class PrincipalOauthUserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    //구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //"registrationId" 로 어떤 OAuth 로 로그인 했는지 확인 가능(google,Naver 등)
        System.out.println("getClientRegistration: " +userRequest.getClientRegistration());
        System.out.println("getAccessToken: " + userRequest.getAccessToken().getTokenValue());
        System.out.println("getAttributes: " + super.loadUser(userRequest).getAttributes());
        //구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인 완료 -> code를 리턴(OAuth-Client 라이브러리가 받아줌) -> code를 통해서 AccessToken요청(access토큰 받음)
        // => "userRequest"가 감고 있는 정보
        //회원 프로필을 받아야하는데 여기서 사용되는것이 "loadUser" 함수이다. -> 구글로 부터 회원 프로필을 받을 수 있다.

        /**
         *  OAuth 로그인 회원 가입
         */
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = null;

        if (userRequest.getClientRegistration().getRegistrationId().equals("google"))
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        }else {
            System.out.println("지원하지 않은 로그인 서비스 입니다.");
        }

        String provider = oAuth2UserInfo.getProvider();//google,naver
        String providerId = oAuth2UserInfo.getProviderId();
//        String username = provider + "_" + providerId;
        String username = oAuth2UserInfo.getName();
        String email = oAuth2UserInfo.getEmail();
        Role role = Role.USER;

        Member userEntity = memberRepository.findByUsername(username);
        //처음 서비스를 이용한 회원일 경우
        if (userEntity == null){
            LocalDateTime createTime = LocalDateTime.now();
            userEntity = Member.builder()
                    .username(username)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .provideId(providerId)
                    .createDate(createTime)
                    .build();

            memberRepository.save(userEntity);
        }

        return new MemberDetails(userEntity, oAuth2User.getAttributes());
    }
}
