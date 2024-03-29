package portfolio2022.portfolio2022.dailyshop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import portfolio2022.portfolio2022.dailyshop.domain.dto.JoinDto;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(DuplicateMemberException.class)
    public String dupMemberEx(DuplicateMemberException e, Model model){
        model.addAttribute("memberForm",new JoinDto());
        log.error(e.getMessage());
        return "dailyshop/joinForm";
    }

}
