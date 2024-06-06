package com.example.nasdaq.config.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.ScriptUtils;
import com.example.nasdaq.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogoutAuthSuccesshandler implements LogoutSuccessHandler {

  @Autowired
  @Lazy
  private UserService userService;

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    userService.updateIsLoginByName(userDetails.getUsername(), false);
    ScriptUtils.alertAndMovePage(response, "로그아웃합니다.", "/v1/nasdaq/main");
    // response.sendRedirect("/v1/nasdaq/main");
  }
  
}
