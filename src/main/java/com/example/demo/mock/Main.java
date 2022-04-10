package com.example.demo.mock;

import com.example.demo.mock.domain.Mock;
import freemarker.template.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {


    public static void main(String[] args) throws IOException, TemplateException {
        String RES = Mock.jsonTemplate(String.join("\n\r", Files.readAllLines(Paths.get("C:\\Users\\guohao01\\Downloads\\demo\\demo\\src\\main\\resources\\templates\\json.ftl"))))
                .arg("user", "{\"name\":\"guohao\",\"age\":11}")
                .arg("addr", "{\"first\": \"beijing\",\"second\":\"haidian\"}")
                .arg("tels", "[11333,3333]")
                .parse();
        System.out.println(RES);
    }

}
