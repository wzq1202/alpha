package com.alpha.spring;

import com.alpha.spring.annotation.Executor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

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
            BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(bdf,bdf.getBeanClassName());
            BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder,registry);
        }
        System.out.println("===============================================");
        Stream.of(registry.getBeanDefinitionNames()).forEach(System.out::println);

        AlphaSpringApplication aa = beanFactory.getBean(AlphaSpringApplication.class);
        System.out.println(aa);

    }
}
