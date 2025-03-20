package com.xh.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 系统安全配置类
 * 用于系统安全设计和并发控制
 */
@Component
public class SystemSecurityConfig implements HandlerInterceptor {
    
    private static final Logger logger = LoggerFactory.getLogger(SystemSecurityConfig.class);
    
    // 用于存储用户登录状态和权限信息
    private static final Map<String, UserSession> USER_SESSIONS = new ConcurrentHashMap<>();
    
    // 用于操作锁控制，防止并发问题
    private static final Map<String, ReentrantLock> RESOURCE_LOCKS = new ConcurrentHashMap<>();
    
    // 定义资源操作的锁
    private static final String FACILITY_LOCK = "facilityLock";
    private static final String USER_LOCK = "userLock";
    private static final String REPAIRS_LOCK = "repairsLock";
    
    /**
     * 初始化系统锁
     */
    static {
        RESOURCE_LOCKS.put(FACILITY_LOCK, new ReentrantLock());
        RESOURCE_LOCKS.put(USER_LOCK, new ReentrantLock());
        RESOURCE_LOCKS.put(REPAIRS_LOCK, new ReentrantLock());
    }
    
    /**
     * 获取指定资源的锁
     * @param resourceName 资源名称
     * @return 资源锁
     */
    public static ReentrantLock getLock(String resourceName) {
        return RESOURCE_LOCKS.computeIfAbsent(resourceName, k -> new ReentrantLock());
    }
    
    /**
     * 处理请求前的拦截器方法
     * 用于权限验证和安全检查
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 设置安全响应头
        setSecurityHeaders(response);
        
        // 获取当前请求路径
        String requestURI = request.getRequestURI();
        
        // 登录和静态资源不需要验证
        if (isPublicResource(requestURI)) {
            return true;
        }
        
        // 获取会话中的用户信息
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 用户未登录，重定向到登录页
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        
        // CSRF防护：验证CSRF Token
        if (isRequireCSRFCheck(request.getMethod()) && !validateCSRFToken(request)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Invalid CSRF Token");
            return false;
        }
        
        // 权限检查
        if (!hasPermission(session, requestURI)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Insufficient permissions");
            return false;
        }
        
        return true;
    }
    
    /**
     * 设置安全响应头
     */
    private void setSecurityHeaders(HttpServletResponse response) {
        // 防止XSS攻击
        response.setHeader("X-XSS-Protection", "1; mode=block");
        
        // 控制页面在iframe中的加载，防止点击劫持
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        
        // 禁止浏览器嗅探资源的MIME类型
        response.setHeader("X-Content-Type-Options", "nosniff");
        
        // HTTP严格传输安全
        response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
        
        // 内容安全策略
        response.setHeader("Content-Security-Policy", 
                "default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline';");
    }
    
    /**
     * 判断是否为公共资源（不需要权限验证）
     */
    private boolean isPublicResource(String uri) {
        return uri.contains("/login") || 
               uri.contains("/static/") || 
               uri.contains("/public/") ||
               uri.contains("/error/") ||
               uri.endsWith(".js") ||
               uri.endsWith(".css") ||
               uri.endsWith(".jpg") ||
               uri.endsWith(".png") ||
               uri.endsWith(".gif");
    }
    
    /**
     * 判断请求方法是否需要CSRF检查
     */
    private boolean isRequireCSRFCheck(String method) {
        return "POST".equals(method) || "PUT".equals(method) || "DELETE".equals(method);
    }
    
    /**
     * 验证CSRF Token
     */
    private boolean validateCSRFToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        
        String sessionToken = (String) session.getAttribute("csrfToken");
        String requestToken = request.getParameter("csrfToken");
        
        return sessionToken != null && sessionToken.equals(requestToken);
    }
    
    /**
     * 检查用户是否有访问特定URI的权限
     */
    private boolean hasPermission(HttpSession session, String uri) {
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            return false;
        }
        
        // 此处根据实际用户类型进行类型转换
        // 假设User类中有一个roles字段表示用户角色
        Integer role = (Integer) session.getAttribute("role");
        
        // 管理员可以访问所有
        if (role != null && role == 1) {
            return true;
        }
        
        // 工作人员权限
        if (role != null && role == 2 && uri.contains("/work/")) {
            return true;
        }
        
        // 教师权限
        if (role != null && role == 3 && uri.contains("/teacher/")) {
            return true;
        }
        
        // 学生权限
        if (role != null && role == 4 && uri.contains("/student/")) {
            return true;
        }
        
        // 公共区域
        if (uri.contains("/public/")) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 请求处理完成后的操作
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求完成后的清理工作
        if (ex != null) {
            logger.error("请求处理发生异常: {}", request.getRequestURI(), ex);
        }
    }
    
    /**
     * 内部类：用户会话信息
     */
    static class UserSession {
        private Integer userId;
        private String username;
        private Integer role;
        private long lastAccessTime;
        
        public UserSession(Integer userId, String username, Integer role) {
            this.userId = userId;
            this.username = username;
            this.role = role;
            this.lastAccessTime = System.currentTimeMillis();
        }
        
        public Integer getUserId() {
            return userId;
        }
        
        public String getUsername() {
            return username;
        }
        
        public Integer getRole() {
            return role;
        }
        
        public long getLastAccessTime() {
            return lastAccessTime;
        }
        
        public void updateAccessTime() {
            this.lastAccessTime = System.currentTimeMillis();
        }
    }
} 