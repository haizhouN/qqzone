package myssm.myspringmvc;

import myssm.ioc.BeanFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    public DispatcherServlet() {

    }

    public void init() throws ServletException {
        super.init();
        //beanFactory = new ClassPathxmlApplicationContext();
        ServletContext appplication = getServletContext();
        Object beanFactoryObj = appplication.getAttribute("beanFactory");
        if (beanFactoryObj != null) {
            beanFactory = (BeanFactory) beanFactoryObj;

        } else {
            throw new RuntimeException("IOC容器获取失败");
        }

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //设置编码
        request.setCharacterEncoding("utf-8");
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
//        System.out.println("没有剪切servletPath值" + servletPath);
        int lastDotIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastDotIndex);
//        System.out.println("剪切后servletPath值" + servletPath);
        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = request.getParameter("operate");
        if (operate == null || "".equals(operate)) {
            operate = "index";
        }
        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (operate.equals(method.getName())) {
                    //1.统一获取请求参数
                    //获取当前 方法的参数，返回参数数组
                    Parameter[] parameters = method.getParameters();
                    //parameterValue装载参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameteName = parameter.getName();
                        if (parameteName.equals("request")) {
                            parameterValues[i] = request;
                        } else if ("response".equals(parameteName)) {
                            parameterValues[i] = response;
                        } else if ("session".equals(parameteName)) {
                            parameterValues[i] = request.getSession();
                        } else {
                            String typename = parameter.getType().getName();
                            Object parameterObj = request.getParameter(parameter.getName());
                            if (parameterObj != null)
                                if ("java.lang.Integer".equals(typename)) {
                                    parameterObj = Integer.parseInt(request.getParameter(parameter.getName()));
                                }
                            parameterValues[i] = parameterObj;
                        }
                    }
                    //2.controller组件中的方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);

                    //3.视图处理
                    String methodReturnStr = (String) returnObj;
                    if (methodReturnStr.startsWith("redirect:")) {//如redirect:fruit.do
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    } else {
                        super.processTemplate(methodReturnStr, request, response);//如："edit"
                    }
                }
            }
//            Method method=controllerBeanObj.getClass().getDeclaredMethod(operate,HttpServletRequest.class);
//            if (method!=null){
//                //2.controller组件中的方法调用
//                method.setAccessible(true);
//                Object returnObj= method.invoke(controllerBeanObj,request);
//
//                //3.视图处理
//                String methodReturnStr=(String) returnObj;
//                if(methodReturnStr.startsWith("redirect:")){//如redirect:fruit.do
//                    String redirectStr= methodReturnStr.substring("redirect".length());
//                    response.sendRedirect(redirectStr);
//                }else {
//                    super.processTemplate(methodReturnStr,request,response);//如："edit"
//                }
//            }else {
//                throw new RuntimeException("operate值非法");
//            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet出错了");
        }


    }
}
