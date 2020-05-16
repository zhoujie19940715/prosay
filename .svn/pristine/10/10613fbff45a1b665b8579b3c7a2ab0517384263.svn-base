package com.prosay.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 数据库的连接池
		 * 在系统初始化的时候，将数据库连接对象存储在内存中，当用户需要访问数据库操作的时候
		 * 从连接池中直接拿一个空闲的连接对象（置为正在使用），使用完以后再放回去（置为空闲）
		 * durid 
		 * 
		 * */
	/*	Map<String,String> properties = new HashMap<String,String>();
		properties.put("url", "jdbc:oracle:thin:@localhost:1521:orcl");
		properties.put("username", "scott");
		properties.put("password", "orcl");
		properties.put("driverClassName","oracle.jdbc.driver.OracleDriver");
		try {
			DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
			Connection conn = dataSource.getConnection();
			String sql = "select * from emp";
			PreparedStatement psd = conn.prepareStatement(sql);
			ResultSet rs = psd.executeQuery();
			rs.next();
			System.out.println(rs.getString(1)+":"+rs.getString(2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		BaseDao pj = new BaseDao();
		String sql = "select deptno,empno,ename,sal from emp";
	/*	List<Emp> result = pj.queryForList(new PRowMapper<Emp>() {
			public Emp mappingRow(ResultSet rs,int rownum)throws SQLException{
				Emp e = new Emp();
				e.setDeptno(rs.getInt("deptno"));
				e.setEmpno(rs.getInt("empno"));
				e.setEname(rs.getString("ename"));
				e.setSal(rs.getFloat("sal"));
				return e;
			}
		}, sql);*/
		/*List<Emp> result = pj.queryForList(Emp.class, sql);
		for(Emp e : result){
			System.out.println(e.getEname());
		}*/
		/**
		 * 事务
		 * 原子性
		 * 一致性
		 * 隔离性
		 * 持久性
		 */
		
		
	}

}
