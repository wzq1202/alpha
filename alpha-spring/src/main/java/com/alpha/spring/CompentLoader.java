package com.alpha.spring;

import com.alpha.spring.annotation.Membership;
import com.alpha.spring.biz.MembershipHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by qiang on 2018/3/29.
 */
@Component
public class CompentLoader implements BeanFactoryPostProcessor {
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
    private Map<String,Object> beanStore = new HashMap<>();
    private final static String ID_DELIMITER = "#";
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Membership.class));
        Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents(this.getClass().getPackage().getName());

        for (BeanDefinition bdf : beanDefinitions) {
            AnnotatedBeanDefinition abd = (AnnotatedBeanDefinition) bdf;
            AnnotationMetadata metadata = abd.getMetadata();
            if (metadata.hasAnnotation(Membership.class.getName())) {
                String beanName = beanNameGenerator.generateBeanName(bdf, registry);
                BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(bdf, beanName);
                BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, registry);
                this.store(beanDefinitionHolder,beanFactory);
            }
        }

        String[] beanNames = beanFactory.getBeanNamesForType(MembershipHandler.class);
        System.out.println(beanStore.toString());
        Stream.of(beanNames).forEach(System.out::println);
    }

    private void store(BeanDefinitionHolder beanDefinitionHolder,ConfigurableListableBeanFactory beanFactory){
        Object bean = beanFactory.getBean(beanDefinitionHolder.getBeanName());
        Membership ann = bean.getClass().getAnnotation(Membership.class);
        String pckid = ann.packageId();
        String pid = ann.productId();
        StringJoiner joiner = new StringJoiner(ID_DELIMITER);
        joiner.add(pckid);
        if (!StringUtils.isEmpty(pid)) {
            joiner.add(pid);
        }

        if (beanStore.containsKey(pckid)) {
            throw new RuntimeException("Duplicate pckid. The pckid is " + pckid);
        }
        beanStore.put(joiner.toString(),bean);
    }
}
