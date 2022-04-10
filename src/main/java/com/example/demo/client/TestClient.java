package com.example.demo.client;


import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// https://jsonplaceholder.typicode.com/todos/1
@FeignClient(name = "jsonplaceholder", url = "https://jsonplaceholder.typicode.com")
public interface TestClient {

    @GetMapping("todos/{id}")
    Todo todos(@PathVariable Long id);

    @Data
    class Todo {
        private Long userId;
        private Long id;
        private String title;
        private Boolean completed;
    }

}
