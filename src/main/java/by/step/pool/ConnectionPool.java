package by.step.pool;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

public class ConnectionPool implements InitializingBean, DisposableBean {

    private final String username;
    private final Integer poolSize;
    private final List<Object> args;
    private Map<String, Object> properties;

//    @Value("#{'${db.hosts}'.split(',')[0]}")
//    @Value("#{19 + 1}")
    @Value("db.hosts")
    private String firstHost;

    public ConnectionPool(String username,
                          Integer poolSize,
                          List<Object> args,
                          Map<String, Object> properties) {
        this.username = username;
        this.poolSize = poolSize;
        this.args = args;
        this.properties = properties;
        System.err.println("---- ConnectionPool constructor finished ----");
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
        System.err.println("---- ConnectionPool setter finished ----");
    }

    private void initXml(){
        System.err.println(" --- ConnectionPool initXml method finished --- ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println(" --- ConnectionPool afterPropertiesSet method finished --- ");

    }

    @PostConstruct
    public void annotationInit(){
        System.err.println(" --- ConnectionPool PostConstruct method finished --- ");
    }

    public void destroyFromXml(){
        System.err.println(" --- ConnectionPool destroy method finished --- ");
    }

    @Override
    public void destroy() throws Exception {

    }

    @PreDestroy
    public void annotationDestroy() throws Exception {

    }
}
