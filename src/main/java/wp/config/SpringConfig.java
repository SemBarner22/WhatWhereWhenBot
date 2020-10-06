//package wp.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import wp.database.DatabaseUtils;
//import wp.domain.Team;
////import wp.repository.TeamRepository;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.List;
//import java.util.Optional;
//
//@Configuration
//public class SpringConfig {
    //I've got my data source defined in application.yml config file,
//so there is no need to configure it from java.
//    DataSource dataSource = DatabaseUtils.getDataSource();

//    @Bean //(name="emf")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        //JpaVendorAdapteradapter can be autowired as well if it's configured in application properties.
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(false);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        //Add package to scan for entities.
////        factory.setPackagesToScan("com.company.domain");
//        factory.setPackagesToScan("wp.domain");
//        factory.setDataSource(dataSource);
//        return factory;
//    }
//    @Bean(name="entityManagerFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//
//        return sessionFactory;
//    }

//    @Bean
//    public PlatformTransactionManager dbTransactionManager() {
//        JpaTransactionManager transactionManager
//                = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(
//                dbEntityManager().getObject());
//        return transactionManager;
//    }

//    @Bean(name="emf")
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory);
//        return txManager;
//    }
//}
