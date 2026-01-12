import by.step.pool.ConnectionPool;
import by.step.pool.Driver;
import by.step.repository.CompanyRepository;
import by.step.repository.UserRepository;
import by.step.service.UserService;
import ioc.Container;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        // ------- 1 ---------
//        Driver driver = new Driver();
//        ConnectionPool connectionPool = new ConnectionPool(driver);
//        driver.setConnectionPool(connectionPool);
//        UserRepository userRepository = new UserRepository(connectionPool);
//        CompanyRepository companyRepository = new CompanyRepository(connectionPool);
//        UserService userService = new UserService(userRepository, companyRepository);
//
//        userService.someMethod();

        // ------- 2 ---------
//        Container container = new Container();
//        ConnectionPool connectionPool = container.get(ConnectionPool.class);
//        UserRepository userRepository = container.get(UserRepository.class);
//        CompanyRepository companyRepository = container.get(CompanyRepository.class);

        // ------- 3 ---------
//        UserService userService = container.get(UserService.class);
//        userService.someMethod();

        // ------- 4 ---------
        var context = new ClassPathXmlApplicationContext("application.xml");
        System.out.println();
//        var connectionPool = context.getBean("p1", ConnectionPool.class);
//        System.out.println(connectionPool);
    }
}
