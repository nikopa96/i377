package project.order.utils;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

/**
 * Copyright!!!
 *
 * Author: Mart Kälmo
 * Source: https://bitbucket.org/mkalmo/exjdbc/src/master/src/main/java/util/DataSourceProvider.java
 */
public class DataSourceProvider {

    private static String databaseUrl;
    private static BasicDataSource basicDataSource;

    public static void setDatabaseUrl(String dbUrl) {
        databaseUrl = dbUrl;
    }

    public static DataSource getDataSource() {
        if (basicDataSource != null) {
            return basicDataSource;
        }

        if (databaseUrl == null) {
            throw new IllegalStateException(
                    "Database url not configured. Use setDatabaseUrl()");
        }

        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        basicDataSource.setUrl(databaseUrl);
        basicDataSource.setMaxActive(3);

        return basicDataSource;
    }
}
