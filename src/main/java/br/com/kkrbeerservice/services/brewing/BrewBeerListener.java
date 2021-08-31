package br.com.kkrbeerservice.services.brewing;

import br.com.kkrbeerservice.configuration.RabbitMqConfiguration;
import br.com.kkrbeerservice.events.BrewBeerEvent;
import br.com.kkrbeerservice.events.NewInventoryEvent;
import br.com.kkrbeerservice.repositories.BeerRepository;
import br.com.kkrbeerservice.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;

@Slf4j
@Service
public class BrewBeerListener {

    private final RabbitTemplate rabbitTemplate;
    private final BeerRepository beerRepository;

    public BrewBeerListener(RabbitTemplate rabbitTemplate,
                            BeerRepository beerRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.beerRepository = beerRepository;
    }

    @RabbitListener(queues = {RabbitMqConfiguration.BREW_BEER_QUEUE})
    public void listentoBrewBeerQueue(Message<BrewBeerEvent> beerEventMessage, MessageHeaders headers) {
        if (headers.containsKey(MessageHeaders.TIMESTAMP)) {
            Instant instant = Instant.ofEpochMilli((Long) headers.get(MessageHeaders.TIMESTAMP));
            log.info("Receiving message sent at {}", instant.atZone(ZoneId.of("UTC")));
        }
        BrewBeerEvent payload = beerEventMessage.getPayload();
        BeerDto beerDto = payload.getBeerDto();
        beerRepository.findById(beerDto.getId()).ifPresent(beer -> {
            beerDto.setStockQuantity(beer.getQuantityToBrew());
            NewInventoryEvent newIntoryEvent = new NewInventoryEvent(beerDto);
            rabbitTemplate.convertAndSend(RabbitMqConfiguration.FANOUT_INVENTORY_EXCHANGE_NAME, "", newIntoryEvent);
        });
    }

}
