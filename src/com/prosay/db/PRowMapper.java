package com.prosay.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 行映射接口，通过传入ResultSet 由调用的开发人员来将记录映射成对象
 * @author jame
 *
 * @param <T>
 */
public interface PRowMapper<T> {

	public T mappingRow(ResultSet rs,int rownum) throws SQLException;
}
