package com.example.bookstore.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  @Before(
      "within(com.example.bookstore.controller.AuthorController) " +
      "|| within(com.example.bookstore.controller.BookController)")
  public void logBefore(JoinPoint joinPoint) {
    log.info("Endpoint accessed: {}", joinPoint.getSignature());
  }
}
