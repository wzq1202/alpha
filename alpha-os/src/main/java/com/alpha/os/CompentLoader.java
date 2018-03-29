package com.alpha.os;

import com.alpha.os.annotation.Executor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.Set;

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
//        registry.registerAlias();
        for (BeanDefinition bdf : beanDefinitions) {
            System.out.println("A===" + bdf.getBeanClassName());
        }
        scanner.scan();
    }
}
