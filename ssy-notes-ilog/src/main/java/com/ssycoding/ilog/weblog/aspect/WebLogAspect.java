package com.ssycoding.ilog.weblog.aspect;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Comments: 自定义日志：切面类
 * @Author: Sean
 * @Date: 2020/5/16 19:55
 */
@Slf4j
@Aspect
@Component
//@Profile({"dev","sit"}) // 指定只能作用于 dev & sit 环境
public class WebLogAspect {

    private static final String LINE_SEPARATOP = System.lineSeparator();

    /**
     * 以自定义 @WebLog 注解为切点
     *
     *  方式1：@Pointcut("@annotation(com.iweb.log.entry.WebLog)") 需要在Controller方法上加入注解 @WebLog(description = "测试自定义日志 - Controller")
     *  方式2：@Pointcut("execution(public * com.iweb.log.action.*Controller.*(..))") 指定包，无需注解
     */
//    @Pointcut("@annotation(com.iweb.log.entry.WebLog)")
    @Pointcut("execution(public * com.ssycoding.ilog.weblog.action.*Controller.*(..))")
    public void webLog() {}

    /**
     * 定义 @Around 环绕，用于何时执行切点
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAroud(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 起始时间, 记录一下调用接口的开始时间
        long startTime = System.currentTimeMillis();
        // 执行切点, 执行切点后, 会去依次调用 @Before -> 接口逻辑代码 -> @After -> @AfterReturning
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        log.info("Response Args                : {}", new Gson().toJson(result));
        // 执行耗时, 打印接口处理耗时
        log.info("Time-Consuming               : ms", System.currentTimeMillis() - startTime);
        // 返回接口返参结果
        return result;
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取 @WebLog 注解的描述信息
//        String methodDescription = getAspectLogDescription(joinPoint);
        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL                          : {}", request.getRequestURL().toString());
        // 打印 @WebLog 注解的描述信息
//        logger.info("Description                  : {}", methodDescription);
        // 打印 Http method
        log.info("HTTP Method                  : {}", request.getMethod());
        // 打印调用 控制层controller 的全路径以及执行方式
        log.info("Class Method                 : {} · {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP                           : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args                 : {}", new Gson().toJson(joinPoint.getArgs()));
    }

    @After("webLog()")
    public void doAfter() throws Throwable {
        // 接口结束就换行, 方便分割查看
        log.info("=========================================== END ===========================================" + LINE_SEPARATOP);
    }

    /**
     * 获取切面注解的描述。配合方式一
     * @param joinPoint 切点
     * @return
     */
//    public String getAspectLogDescription(JoinPoint joinPoint) throws Exception {
//        String targetName = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        Object[] arguments = joinPoint.getArgs();
//        Class targetClazz = Class.forName(targetName);
//        Method[] methods = targetClazz.getMethods();
//        StringBuilder desctiption = new StringBuilder("");
//        for (Method method : methods) {
//            if (method.getName().equals(methodName)) {
//                Class[] clazz = method.getParameterTypes();
//                if (clazz.length == arguments.length) {
//                    desctiption.append(method.getAnnotation(WebLog.class).description());
//                    break;
//                }
//            }
//        }
//        return desctiption.toString();
//    }
}

