package project;

import project.order.utils.DataSourceProvider;
import project.order.utils.Util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebListener()
public class ProjectListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context initialized");

        DataSourceProvider.setDatabaseUrl("jdbc:hsqldb:mem:db");
        DataSource dataSource = DataSourceProvider.getDataSource();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(Util.readFile("schema.sql"));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
