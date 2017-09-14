package personal.ws.learning.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/8/10
 * @project：WSService
 */
@Aspect
@Component
public class AspectTest {

    // @Pointcut("execution(public * com.hongwei.moddle.*.controller.*.*(..))")
//    @Pointcut("execution(public * personal.ws.myservice.query.controller.*.*(..))")
   @Pointcut("execution(* *(..))")
    public void poin() {
    }

    @Before("execution( * *(..))")
    public void pointBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        Class c = getClass(joinPoint);
        Method method = getMethod(joinPoint);
        Map<String, String[]> map = request.getParameterMap();
        assert map.size()>0:"参数数组个数小于0";
        System.out.println(map.toString());
        System.out.println(111);
    }

    public Class getClass(JoinPoint joinpoint){
        return joinpoint.getSignature().getDeclaringType();
    }

    public Method getMethod(JoinPoint joinpoint){
        MethodSignature methodSignature = (MethodSignature)joinpoint.getSignature();
        return methodSignature.getMethod();
    }

}
