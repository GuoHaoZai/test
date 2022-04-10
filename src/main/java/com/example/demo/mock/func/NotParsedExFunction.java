package com.example.demo.mock.func;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

public class NotParsedExFunction implements TemplateMethodModelEx {

    private final String name;

    public NotParsedExFunction(String name) {
        this.name = name;
    }

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return String.format("${%s('%s')}", this.name, arguments.get(0));
    }
}
