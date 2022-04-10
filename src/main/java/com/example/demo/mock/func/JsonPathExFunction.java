package com.example.demo.mock.func;

import com.jayway.jsonpath.JsonPath;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

public class JsonPathExFunction implements TemplateMethodModelEx {
    private final Object document;

    public JsonPathExFunction(String json) {
        this.document = com.jayway.jsonpath.Configuration.defaultConfiguration().jsonProvider().parse(json);
    }

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return JsonPath.read(document, arguments.get(0).toString());
    }
}
