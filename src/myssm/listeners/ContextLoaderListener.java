package myssm.listeners;

import myssm.ioc.BeanFactory;
import myssm.ioc.ClassPathxmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//监听上下文启动，在上下文启动的时候创建ioc容器然后将其保存到application作用域内
//后面中央控制器在从application作用域获取ioc容器
@WebListener
public class ContextLoaderListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext application = servletContextEvent.getServletContext();
        String path = application.getInitParameter("ContextConfigLocation");
        BeanFactory beanFactory = new ClassPathxmlApplicationContext(path);
        application.setAttribute("beanFactory", beanFactory);


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
