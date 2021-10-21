package com.geekbrains.geekmarketwinter.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Aspect
@Component
public class CartAspects {

    // Checks if user is "Admin" and executes (or not)  *ToCart methods
    @Around("execution(public * com.geekbrains.geekmarketwinter.services.ShoppingCartService.*ToCart(..))")
    public Object verifyUserRightsBeforeCartActions(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<?> authorities = auth.getAuthorities();
        if(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            return proceedingJoinPoint.proceed();
        }
        return null;
    }

}
