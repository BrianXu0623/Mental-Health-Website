package elec5619.sydney.edu.au.mental_health_support_website;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "elec5619.sydney.edu.au.mental_health_support_website.models")
public class AppConfig {
    // Your configuration code here
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.mysql.JDBC");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/my_local_db");
//        return dataSource;
//    }
}
