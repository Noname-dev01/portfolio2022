package portfolio2022.portfolio2022.dailyshop.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import portfolio2022.portfolio2022.dailyshop.domain.Member;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/members")
    public Result memberListApi() {
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers
                .stream()
                .map(m -> new MemberDto(m.getUserId(), m.getPassword()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @PostMapping("api/members")
    public CreateMemberResponse saveMember(@RequestBody @Valid CreateMemberRequest request){
        Member member = Member.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .build();
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String userId;
        private String password;
    }
    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
    @Data
    static class CreateMemberRequest{
        @NotEmpty
        private String userId;
        @NotEmpty
        private String password;
    }
    @Data
    static class UpdateMemberRequest{
        private String name;
        private String password;
    }
    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
        private String password;
    }
}
