package guru.sfg.kkrbeerservice.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    public static final String BREWRY_BINDING = "brewry_binding";
    public static final String FANOUT_BREWRY_EXCHANGE_NAME = "brewry_exchange";
    public static final String BREW_BEER_QUEUE = "brew_beer_queue";

    public static final String NEW_INVENTORY_BINDING = "new_inventory_binding";
    public static final String FANOUT_INVENTORY_EXCHANGE_NAME = "inventory_exchange";
    public static final String NEW_INVENTORY_QUEUE = "new_inventory_queue";

    // Beer queue
    @Bean(name = BREW_BEER_QUEUE)
    public Queue brewBeerQueue() {
        return new Queue(BREW_BEER_QUEUE);
    }

    @Bean(name = FANOUT_BREWRY_EXCHANGE_NAME)
    public FanoutExchange fanoutBrewryExchange() {
        return new FanoutExchange(FANOUT_BREWRY_EXCHANGE_NAME);
    }

    @Bean(name = BREWRY_BINDING)
    public Binding brewBeerbinding(@Qualifier(BREW_BEER_QUEUE) Queue queue,
                                   @Qualifier(FANOUT_BREWRY_EXCHANGE_NAME) FanoutExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange);
    }

    // New inventory queue
    @Bean(name = NEW_INVENTORY_BINDING)
    public Binding newInventoryBinding(@Qualifier(NEW_INVENTORY_QUEUE) Queue queue,
                                       @Qualifier(FANOUT_INVENTORY_EXCHANGE_NAME) FanoutExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange);
    }

    @Bean(name = NEW_INVENTORY_QUEUE)
    public Queue newInventoryqueue() {
        return new Queue(NEW_INVENTORY_QUEUE);
    }

    @Bean(name = FANOUT_INVENTORY_EXCHANGE_NAME)
    public FanoutExchange fanoutNewInventoryExchange() {
        return new FanoutExchange(FANOUT_INVENTORY_EXCHANGE_NAME);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory,
                                         final Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(final ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
