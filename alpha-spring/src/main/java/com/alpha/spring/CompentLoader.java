package com.alpha.spring;

import com.alpha.spring.annotation.Executor;
import org.springframework.beans.BeansException;
import org.springframework.beans.annotation.AnnotationBeanUtils;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by qiang on 2018/3/29.
 */
@Component
public class CompentLoader implements BeanFactoryPostProcessor{
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry)beanFactory;
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Executor.class));
        Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents(this.getClass().getPackage().getName());
        System.out.println("===============================================");
        for (BeanDefinition bdf : beanDefinitions) {
            AnnotatedBeanDefinition abd = (AnnotatedBeanDefinition)bdf;
            AnnotationMetadata metadata = abd.getMetadata();
            if (metadata.hasAnnotation(Executor.class.getName())) {
                Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(Executor.class.getName());
                String name = (String) annotationAttributes.get("name");
//                registry.registerBeanDefinition(name,bdf);
                BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(bdf,name,new String[]{"A","B","C"});
                BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder,registry);
            }
//            Set<String> metaAnnotationTypes = metadata.getMetaAnnotationTypes();

//            System.out.println(Executor.class.getEnumConstants());
//            annotationAttributes.get("name");
           /* System.out.println(annotationAttributes);
            System.out.println(metadata.hasAnnotation(Executor.class.getName()));
*/


        }
        System.out.println(Arrays.toString(beanFactory.getAliases("DDD")));
        System.out.println("===============================================");

        Stream.of(registry.getBeanDefinitionNames()).forEach(System.out::println);


    }
}
