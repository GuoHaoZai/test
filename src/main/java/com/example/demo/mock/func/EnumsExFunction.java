package com.example.demo.mock.func;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;
import java.util.Random;

public class EnumsExFunction implements TemplateMethodModelEx {

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        int random = new Random().nextInt(arguments.size());
        return arguments.get(random);
    }
}
