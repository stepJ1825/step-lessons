import by.step.pool.ConnectionPool;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        // ------- 1 ---------
        //        var connectionPool = new ConnectionPool();
        //        var userRepository = new UserRepository(connectionPool);
        //        var companyRepository = new CompanyRepository(connectionPool);
        //        var userService = new UserService(userRepository, companyRepository);

        // ------- 2 ---------
        //        var container = new Container();
        //        var connectionPool = container.get(ConnectionPool.class);
        //        var userRepository = container.get(UserRepository.class);
        //        var companyRepository = container.get(CompanyRepository.class);

        // ------- 3 ---------
        //        var userService = container.get(UserService.class);

        // ------- 4 ---------
                var context = new ClassPathXmlApplicationContext("application.xml");
                var connectionPool = context.getBean("p1", ConnectionPool.class);
                System.out.println(connectionPool);
    }
}
