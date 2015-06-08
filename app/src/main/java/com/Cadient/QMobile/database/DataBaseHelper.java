package com.Cadient.QMobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.remote.MealRating;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey Vereshchaga on 29.08.14.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DataBaseHelper.class.getSimpleName();

    /**
     * map of all classes which need presentation in database
     */
    private static final List<Class<? extends BaseModel>> tablesClasses = new ArrayList<Class<? extends BaseModel>>() {
        {
            add(MealRating.class);
        }
    };

    public DataBaseHelper(Context context) {
        super(context, DataBaseUtils.DATABASE_NAME, null, DataBaseUtils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        for (Class<? extends BaseModel> tablesClass : tablesClasses) {
            try {
                TableUtils.createTable(connectionSource, tablesClass);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer,
                          int newVer) {
        for (Class<? extends BaseModel> tablesClass : tablesClasses) {
            try {
                TableUtils.dropTable(connectionSource, tablesClass, true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        onCreate(db, connectionSource);
    }

    //executed when the application is closed
    @Override
    public void close() {
        super.close();
    }
}
