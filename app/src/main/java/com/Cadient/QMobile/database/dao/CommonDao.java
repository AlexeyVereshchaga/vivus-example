package com.Cadient.QMobile.database.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexey Vereshchaga on 29.08.14.
 */
public class CommonDao<T> extends BaseDaoImpl<T, String> {
    public CommonDao(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    /**
     * get all rows from table
     *
     * @return
     * @throws SQLException
     */
    public List<T> getAllList() throws SQLException {
        return queryForAll();
    }

    /**
     * get part of table use offset and limit
     *
     * @param offset
     * @param limit
     * @return
     * @throws SQLException
     */
    public List<T> getList(Long offset, Long limit) throws SQLException {
        QueryBuilder<T, String> queryBuilder = queryBuilder();
        queryBuilder.offset(offset).limit(limit);
        return query(queryBuilder.prepare());
    }

    /**
     * add object in database
     *
     * @param data
     * @return
     * @throws SQLException
     */
    public int add(T data) throws SQLException {
        return create(data);
    }

    /**
     * clean all table rows
     *
     * @throws SQLException
     */
    public void removeAll() throws SQLException {
        delete(deleteBuilder().prepare());
    }
}
