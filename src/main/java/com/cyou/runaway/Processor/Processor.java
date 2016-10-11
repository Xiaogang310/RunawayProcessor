package com.cyou.runaway.Processor;

import com.cyou.runaway.Processor.DocBuilder.BindingSet;
import com.cyou.runaway.Processor.DocBuilder.Builder;
import com.cyou.runaway.Processor.annotation.CommandAnnotation;
import com.cyou.runaway.Processor.annotation.FieldAnnotation;
import com.cyou.runaway.Processor.annotation.MethodAnnotation;
import com.cyou.runaway.Processor.internal.FunctionInfo;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by Gang on 2016/10/11.
 */

public class Processor extends AbstractProcessor
{
    private final boolean DEBUG = false;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
    {
        List<BindingSet> bindings = parseTargets(roundEnv);
        Builder.build(bindings);

        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion()
    {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes()
    {
        Set<String> types = new LinkedHashSet<>();

        for (Class<? extends Annotation> annotation : getSupportedAnnotations())
        {
            types.add(annotation.getCanonicalName());
        }

        return types;
    }

    private List<BindingSet> parseTargets(RoundEnvironment environment)
    {
        Map<String, String> cmdMap = parseCommandAnnotation(environment);
        Map<String, String> componentMap = parseFieldAnnotation(environment);
        Map<String, List<FunctionInfo>> functionInfoMap = parseMethodAnnotation(environment);

        if (DEBUG)
        {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Cmd : " + cmdMap.toString());
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Component : " + componentMap.toString());
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Function : " + functionInfoMap.toString());
        }

        return buildBindings(cmdMap, componentMap, functionInfoMap);
    }

    private List<BindingSet> buildBindings(Map<String, String> cmdMap, Map<String, String> componentMap, Map<String, List<FunctionInfo>> functions)
    {
        List<BindingSet> bindings = new LinkedList<>();

        for (String className : cmdMap.keySet())
        {
            String cmdName = cmdMap.get(className);
            BindingSet binding = buildBindingSet(cmdName, className, componentMap, functions);
            bindings.add(binding);
        }

        return bindings;
    }

    private BindingSet buildBindingSet(String cmdName, String className, Map<String, String > componentMap, Map<String, List<FunctionInfo>> functions)
    {
        String componentName = componentMap.get(className);
        List<FunctionInfo> functionInfos = functions.get(componentName);

        return new BindingSet(cmdName, functionInfos);
    }

    private Map<String, String> parseCommandAnnotation(RoundEnvironment environment)
    {
        Map<String,String> cmdMap = new HashMap<>();

        for (Element element : environment.getElementsAnnotatedWith(CommandAnnotation.class))
        {
            String className = element.toString();
            CommandAnnotation annotation = element.getAnnotation(CommandAnnotation.class);
            String cmdName = annotation.command();

            cmdMap.put(className, cmdName);
        }

        return cmdMap;
    }

    private Map<String, String> parseFieldAnnotation(RoundEnvironment environment)
    {
        Map<String, String > fieldMap = new HashMap<>();

        for (Element element : environment.getElementsAnnotatedWith(FieldAnnotation.class))
        {
            String className = element.getEnclosingElement().asType().toString();
            String componentName = element.asType().toString();

            fieldMap.put(className, componentName);
        }

        return fieldMap;
    }

    private Map<String, List<FunctionInfo>> parseMethodAnnotation(RoundEnvironment environment)
    {
        Map<String, List<FunctionInfo>> methodMap = new HashMap<>();

        for (Element element : environment.getElementsAnnotatedWith(MethodAnnotation.class))
        {
            String componentName = element.getEnclosingElement().asType().toString();
            MethodAnnotation annotation = element.getAnnotation(MethodAnnotation.class);

            FunctionInfo functionInfo = new FunctionInfo(element.getSimpleName().toString());
            functionInfo.setDescription(annotation.description());
            functionInfo.setMethodType(annotation.type());
            functionInfo.setParams(annotation.params());
            functionInfo.setResult(annotation.result());

            if (methodMap.containsKey(componentName))
            {
                methodMap.get(componentName).add(functionInfo);
            }
            else
            {
                List<FunctionInfo> functionInfos = new LinkedList<>();
                functionInfos.add(functionInfo);
                methodMap.put(componentName, functionInfos);
            }
        }

        return methodMap;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations()
    {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();

        annotations.add(CommandAnnotation.class);
        annotations.add(FieldAnnotation.class);
        annotations.add(MethodAnnotation.class);

        return annotations;
    }
}
