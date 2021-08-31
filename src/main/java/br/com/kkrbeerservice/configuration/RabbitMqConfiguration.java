package br.com.kkrbeerservice.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    public static final String FANOUT_BREWRY_EXCHANGE_NAME = "brewry_exchange";
    public static final String BREW_BEER_QUEUE = "brew_beer_queue";

    @Bean
    public Queue queue() {
        return new Queue(BREW_BEER_QUEUE);
    }

    @Bean(name = FANOUT_BREWRY_EXCHANGE_NAME)
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_BREWRY_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, FanoutExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange);
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
