package guru.sfg.kkrbeerservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class BeerInventoryClientConfiguration {

    public static final String INVENTORY_CLIENT_BEAN = "inventory_client_bean";

    @Value("${kkr.inventory.host}")
    private String apiHost;

    @Bean(INVENTORY_CLIENT_BEAN)
    public RestTemplate apiClient(RestTemplateBuilder builder) {
        return builder.rootUri(this.apiHost)
                .setConnectTimeout(Duration.ofSeconds(30))
                .setReadTimeout(Duration.ofSeconds(30))
                .build();
    }

}
