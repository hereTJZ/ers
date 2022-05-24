package com.example.ers.biz.impl;

import com.example.ers.entity.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class myHttpSessionListener implements HttpSessionListener {

    // 存储内存中活跃的session（static变量）
    private static final Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    // 监听session的创建
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        sessions.put(session.getId(), session);
    }

    // 监听session的销毁
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessions.remove(event.getSession().getId());
    }

    // 通过id查找内存中的session
    public static HttpSession find(String sessionId) {
        return sessions.get(sessionId);
    }

    // 遍历session查找用户，判断登录状态
    public static boolean isUserActive(int userID) {
        for (String key : sessions.keySet()) {
            User userTemp = (User) sessions.get(key).getAttribute("user");
            if (userTemp != null && userTemp.getId() == userID) return true;
        }
        return false;
    }
}

