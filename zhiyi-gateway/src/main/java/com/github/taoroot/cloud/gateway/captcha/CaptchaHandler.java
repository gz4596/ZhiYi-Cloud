package com.github.taoroot.cloud.gateway.captcha;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RestController
public class CaptchaHandler {

    @GetMapping("/captcha/image")
    public Mono<ResponseEntity> smsCreate(@RequestParam String uuid,
                                          @RequestParam(defaultValue = "200") Integer width,
                                          @RequestParam(defaultValue = "100") Integer height) {
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(width, height);
        captcha.setGenerator(randomGenerator);
        captcha.createCode();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("content-type", Collections.singletonList(MediaType.IMAGE_JPEG_VALUE));
        headers.put("captcha-uuid", Collections.singletonList(uuid));
        return Mono.just((new ResponseEntity<>(captcha.getImageBytes(), headers, HttpStatus.OK)));
    }

    @GetMapping("/captcha/sms")
    public Mono<ResponseEntity> imageCreate() {
        return Mono.just((new ResponseEntity<>(123, HttpStatus.OK)));
    }
}
