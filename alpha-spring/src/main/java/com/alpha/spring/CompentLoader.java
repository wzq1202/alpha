package com.alpha.spring;

import com.alpha.spring.annotation.Membership;
import com.alpha.spring.biz.MembershipHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

/**
 * Created by qiang on 2018/3/29.
 */
@Component
public class CompentLoader implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Membership.class));
        scanner.scan(this.getClass().getPackage().getName());
        /*
        Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents(this.getClass().getPackage().getName());

        for (BeanDefinition bdf : beanDefinitions) {
            AnnotatedBeanDefinition abd = (AnnotatedBeanDefinition) bdf;
            AnnotationMetadata metadata = abd.getMetadata();
            if (metadata.hasAnnotation(Membership.class.getName())) {
                String beanName = beanNameGenerator.generateBeanName(bdf, registry);
                BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(bdf, beanName);
                BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, registry);
            }
        }*/


        MembershipRegistry membershipRegistry = beanFactory.getBean(MembershipRegistry.class);

        String[] beanNames = beanFactory.getBeanNamesForAnnotation(Membership.class);
        for (String name : beanNames) {
            Object obj = beanFactory.getBean(name);
            if (obj instanceof MembershipHandler) {
                membershipRegistry.register((MembershipHandler) obj);
            }
        }
    }
}
