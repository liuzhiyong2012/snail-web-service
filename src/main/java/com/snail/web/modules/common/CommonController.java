package com.snail.web.modules.common;

import com.snail.web.dto.BaseResponse;
import com.snail.web.utils.FileUploadUtils;
import com.snail.web.utils.ResponseUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequestMapping("/common")
public class CommonController {
    @PostMapping("/upload")
    public BaseResponse uploadFile(MultipartFile file){

        Map<String, String> map = FileUploadUtils.uploadFile(file);
        return ResponseUtils.convert(map);

    }
}
