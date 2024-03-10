import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * @author csq
 * @description:
 * @date 2024/3/5 15:51
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.springcloudalibaba", "com.myvocal"})
// 开启feign客户端，必须显式指定扫描的包，否则会启动失败
@EnableFeignClients(basePackages = "com.example.springcloudalibaba.order")
public class OrderApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(OrderApplication.class, args);
        // 读取nacos配置中心，必须在bootstrap.yml中配置好配置中心地址
        // 由于Nacos的配置加载比application.yml或application.properties早，
        // 你需要在bootstrap.yml或bootstrap.properties文件中添加Nacos配置中心的相关配置。
        // 这个文件也应该位于src/main/resources目录。
        // 配置DataId匹配规则： application.name-profile.active-file.extension
        // 不指定profile-active，则不连接该值，file.extension默认为properties，所以dataId是order-service.properties
//        while (true){
//            String username = applicationContext.getEnvironment().getProperty("user.name");
//            String password = applicationContext.getEnvironment().getProperty("user.password");
//            System.out.println("name:" + username + ", password:" + password);
//            System.out.println(applicationContext.getEnvironment().getProperty("aes.flag"));
//            System.out.println(applicationContext.getEnvironment().getProperty("extra.flag"));
//            Thread.sleep(10000);
//        }
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
