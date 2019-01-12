package cn.harryai.microservicemovie.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/envStr")
@RefreshScope
public class ConfigController {
    @Value("${env}")
    private String env;


    @GetMapping("/print")
    public String pringEnv(){

        return this.env;

    }
}
