package com.xh.controller;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.google.gson.Gson;

@WebServlet("/StatisticsServlet")
public class StatisticsController extends HttpServlet {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bishe?useUnicode=true&characterEncoding=utf-8",
                "root", "52077");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        Map<String, Object> statisticsData = new HashMap<>();

        try (Connection conn = getConnection()) {
            // 统计新增设备数量
            statisticsData.put("addFacilityCount", getTableCount(conn, "addfacility"));

            // 统计报废设备数量
            statisticsData.put("deleteFacilityCount", getTableCount(conn, "deletefacility"));

            // 统计维修设备数量
            statisticsData.put("repairsCount", getTableCount(conn, "repairs"));

            // 设备状态分布
            statisticsData.put("facilityStatusDistribution", getFacilityStatusDistribution(conn));

            // 每月新增设备趋势
            statisticsData.put("monthlyAddFacilityTrend", getMonthlyTrend(conn, "addfacility"));
            statisticsData.put("monthlyDeleteFacilityTrend", getMonthlyTrend(conn, "deletefacility"));
            statisticsData.put("monthlyRepairsTrend", getMonthlyTrend(conn, "repairs"));

            // 设备类型分布
            statisticsData.put("facilityTypeDistribution", getFacilityTypeDistribution(conn));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.getWriter().write(gson.toJson(statisticsData));
    }

    private int getTableCount(Connection conn, String tableName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tableName;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    private Map<String, Integer> getFacilityStatusDistribution(Connection conn) throws SQLException {
        Map<String, Integer> distribution = new HashMap<>();
        String[] tables = {"addfacility", "deletefacility", "repairs"};

        for (String table : tables) {
            String sql = "SELECT plan, COUNT(*) as count FROM " + table + " GROUP BY plan";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String status = rs.getString("plan");
                    int count = rs.getInt("count");
                    distribution.put(status, distribution.getOrDefault(status, 0) + count);
                }
            }
        }
        return distribution;
    }

    private List<Map<String, Object>> getMonthlyTrend(Connection conn, String tableName) throws SQLException {
        String sql = "SELECT DATE_FORMAT(createTime, '%Y-%m') as month, COUNT(*) as count " +
                "FROM " + tableName + " GROUP BY month ORDER BY month";
        List<Map<String, Object>> trend = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Map<String, Object> monthData = new HashMap<>();
                monthData.put("month", rs.getString("month"));
                monthData.put("count", rs.getInt("count"));
                trend.add(monthData);
            }
        }
        return trend;
    }

    private Map<String, Integer> getFacilityTypeDistribution(Connection conn) throws SQLException {
        Map<String, Integer> distribution = new HashMap<>();
        String sql = "SELECT fname, COUNT(*) as count FROM addfacility GROUP BY fname";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String type = rs.getString("fname");
                int count = rs.getInt("count");
                distribution.put(type, count);
            }
        }
        return distribution;
    }
}
