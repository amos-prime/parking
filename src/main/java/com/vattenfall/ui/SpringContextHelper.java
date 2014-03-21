package com.vattenfall.ui;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Spring helper for Vaadin UI
 */
public class SpringContextHelper {

    /** Spring context */
    private ApplicationContext context;

    /**
     * Creates helper and request Spring context from servlet context.
     * 
     * @param servletContext servlet context
     */
    public SpringContextHelper(final ServletContext servletContext) {
        context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }

    /**
     * Gets bean from Spring context.
     * 
     * @param beanName unique name of bean in Spring context
     * @return spring bean for given name
     */
    public Object getBean(final String beanName) {
        return context.getBean(beanName);
    }

    /**
     * Gets bean from Spring context.
     * 
     * @param clazz class of a bean in Spring context
     * @return Spring bean for given class
     */
    public <T> T getBean(Class<T> clazz) {
        return (T) context.getBean(clazz);
    }
}
