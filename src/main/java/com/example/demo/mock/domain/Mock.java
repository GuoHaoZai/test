package com.example.demo.mock.domain;

import com.example.demo.mock.func.EnumsExFunction;
import com.example.demo.mock.func.JsonPathExFunction;
import com.example.demo.mock.func.NotParsedExFunction;
import com.jayway.jsonpath.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mock {
    private static final freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_29);

    private Template template;
    private Map<String, JsonPathExFunction> args = new HashMap<>();

    private Mock(String template) throws IOException {
        this.template = new Template("template", template, cfg);
        cfg.setDirectoryForTemplateLoading(new File("C:\\Users\\guohao01\\Downloads\\demo\\demo\\src\\main\\resources\\templates"));
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
    }

    public static Mock jsonTemplate(String template) throws IOException {
        return new Mock(template);
    }

    public Mock arg(String alias, String arg) {
        this.args.put(alias, new JsonPathExFunction(arg));
        return this;
    }


    public String parse() throws IOException, TemplateException {

        Map<String, Object> root = new HashMap<>();
        root.put("enums", new EnumsExFunction());
        root.put("this", new NotParsedExFunction("this"));
        root.put("args", new NotParsedExFunction("args"));
        root.putAll(this.args.keySet().stream().collect(Collectors.toMap(Function.identity(), NotParsedExFunction::new)));

        Writer firstOut = new StringWriter();
        template.process(root, firstOut);

        String json = firstOut.toString();
        System.out.println(json);

        Template template = new Template("first_parsed_json_template", firstOut.toString(), cfg);
        root.put("this", new JsonPathExFunction(json));
        root.putAll(this.args);
        root.put("args", new JsonPathExFunction("[{\"name\":\"aggg\",\"age\":14}, {\"test\":\"aa\"}]"));

        Writer secondOut = new StringWriter();

        template.process(root, secondOut);
        return secondOut.toString();
    }

}
