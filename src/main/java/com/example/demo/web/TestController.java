package com.example.demo.web;

import com.example.demo.client.TestClient;
import feign.Feign;
import feign.Logger;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private TestClient testClient;

    @GetMapping("todo/{id:\\d+}")
    public TestClient.Todo getTodo(@PathVariable Long id) {
        return testClient.todos(id);
    }

}
