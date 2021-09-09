package com.atschool.dao;

import com.atschool.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 增、删、改操作
     * @param sql
     * @param args
     * @return
     */
    public int update(String sql, Object... args) {

        Connection conn = JdbcUtils.getConnection();

        try {
            int update = queryRunner.update(conn, sql, args);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return -1;
    }

    /**
     * 查找某行数据
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {

        Connection conn = JdbcUtils.getConnection();

        try {
            T query = queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }

        return null;
    }

    /**
     * 查找多行数据
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {

        Connection conn = JdbcUtils.getConnection();

        try {
            List<T> query = queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }

        return null;
    }

    /**
     * 查找某行某列的数据
     * @param sql
     * @param args
     * @return
     */
    public Object queryForSingleValue(String sql, Object... args) {

        Connection conn = JdbcUtils.getConnection();

        try {
            Object query = queryRunner.query(conn, sql, new ScalarHandler(), args);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }

        return null;
    }
}
