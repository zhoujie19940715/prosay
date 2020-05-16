package com.prosay.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

public class TransactionTest {

	public static void main(String[] args) throws SQLException {
		/*PJdbcTemplate pj = new PJdbcTemplate();
		DbUtil.openTransaction();
		Savepoint sp = null;
		try{
		pj.excuteUpdate("insert into t_user values(30,'缘来(J170743-程龙海)')");
		pj.excuteUpdate("insert into t_user values(31,'♥清风落叶♥(J170723	李水平)')");
		sp = DbUtil.getConnection().setSavepoint();
		int a = 1/0;
		pj.excuteUpdate("insert into t_user values(32,'♥清风落叶♥(J170723	李水平)')");
		}catch(Exception ex){
			ex.printStackTrace();
			try {
				DbUtil.getConnection().rollback(sp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}finally{
			DbUtil.commitTransaction();
		}*/
		/**
		 * 批量更新 发送到数据库作为一个单元执行的一组更新语句
		 * 
		 */
		BaseDao pj = new BaseDao();
		String sql = "insert into t_user values(?,?)";
		List<Object[]> params = new ArrayList<Object[]>();
		//long startTime = System.currentTimeMillis();
		for(int i = 22400; i <22500;i++){
			params.add(new Object[]{i,"jame"+i});
			//pj.excuteUpdate(sql, i,"jame"+i);
		}
		//long endTime = System.currentTimeMillis();
	//	System.out.println("执行时间："+(endTime-startTime)+"毫秒");
		long startTime = System.currentTimeMillis();
	/*	DbUtil.openTransaction();
		try{
			pj.executeBatch(sql, params);
		}catch(Exception ex){
			DbUtil.rollbackTransaction();
		}*/
		DbUtil.commitTransaction();
		long endTime = System.currentTimeMillis();
		System.out.println("执行时间："+(endTime-startTime)+"毫秒");
	}

}
