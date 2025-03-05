package com.xh.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 安全工具类
 * 用于防止XSS攻击和SQL注入
 */
public class SecurityUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
    
    /**
     * 清理输入字符串，防止XSS攻击
     * @param input 需要清理的输入
     * @return 清理后的字符串
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        
        // 替换特殊字符
        String sanitized = input
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;")
                .replace("%", "&#37;")
                .replace("(", "&#40;")
                .replace(")", "&#41;")
                .replace("&", "&amp;")
                .replace("/", "&#47;")
                .replace("\\", "&#92;");
        
        logger.debug("Input sanitized: {} -> {}", input, sanitized);
        return sanitized;
    }
    
    /**
     * 验证输入是否为整数
     * @param input 需要验证的字符串
     * @return 是否为整数
     */
    public static boolean isInteger(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * 验证日期格式是否有效 (简单验证 YYYY-MM-DD)
     * @param dateStr 日期字符串
     * @return 是否有效
     */
    public static boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return false;
        }
        
        // 简单的格式检查，实际应用中可以使用更复杂的验证
        return dateStr.matches("\\d{4}-\\d{2}-\\d{2}");
    }
    
    /**
     * 验证字符串长度是否在指定范围内
     * @param input 输入字符串
     * @param minLength 最小长度
     * @param maxLength 最大长度
     * @return 是否合法
     */
    public static boolean isValidLength(String input, int minLength, int maxLength) {
        if (input == null) {
            return minLength == 0;
        }
        int length = input.length();
        return length >= minLength && length <= maxLength;
    }
} 