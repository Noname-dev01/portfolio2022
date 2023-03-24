package portfolio2022.portfolio2022.s3upload;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class webController {

    private final S3Uploader s3Uploader;

    @GetMapping("/s3upload")
    public String index(){
        return "s3upload/index";
    }

    @PostMapping("/s3upload/upload")
    @ResponseBody
    public String upload(@RequestParam("data")MultipartFile multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile,"static");
    }
}
