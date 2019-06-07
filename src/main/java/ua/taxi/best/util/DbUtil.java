package ua.taxi.best.util;

import org.apache.log4j.Logger;
import org.postgresql.jdbc2.optional.PoolingDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {

    private static final Logger LOGGER = Logger.getLogger(DbUtil.class);

    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = createDataSource();
        }
        return dataSource;
    }

    private static DataSource createDataSource() {
        try {
            Properties properties = new Properties();
            properties.load(DbUtil.class.getClassLoader().getResourceAsStream("application.properties"));
            PoolingDataSource source = new PoolingDataSource();
            source.setDataSourceName(properties.getProperty("db.dataSourceName"));
            source.setServerName(properties.getProperty("db.serverName"));
            source.setDatabaseName(properties.getProperty("db.databaseName"));
            source.setPortNumber(Integer.parseInt(properties.getProperty("db.portNumber")));
            source.setUser(properties.getProperty("db.user"));
            source.setPassword(properties.getProperty("db.password"));
            source.setInitialConnections(Integer.parseInt(properties.getProperty("db.initialConnections")));
            source.setMaxConnections(Integer.parseInt(properties.getProperty("db.maxConnections")));
            return source;
        }catch (IOException e) {
            LOGGER.fatal("IOException. Couldn't connect to database.");
            throw new RuntimeException(e);
        }
    }
}
