package guru.sfg.kkrbeerservice.jobs;

import guru.sfg.kkrbeerservice.configuration.RabbitMqConfiguration;
import guru.sfg.kkrbeerservice.domain.Beer;
import guru.sfg.commons.events.BrewBeerEvent;
import guru.sfg.kkrbeerservice.repositories.BeerRepository;
import guru.sfg.kkrbeerservice.services.inventory.BeerInventoryClient;
import guru.sfg.kkrbeerservice.web.mappers.BeerMapper;
import guru.sfg.commons.dto.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
public class BrewingJob {

    private final long jobRate = Duration.ofSeconds(2).toMillis();
    private final BeerMapper beerMapper;
    private final FanoutExchange fanoutExchange;
    private final RabbitTemplate rabbitTemplate;
    private final BeerRepository beerRepository;
    private final BeerInventoryClient beerInventoryClient;

    @Autowired
    public BrewingJob(BeerMapper beerMapper,
                      @Qualifier(RabbitMqConfiguration.FANOUT_BREWRY_EXCHANGE_NAME) FanoutExchange fanoutExchange,
                      RabbitTemplate rabbitTemplate,
                      BeerRepository beerRepository,
                      BeerInventoryClient beerInventoryClient) {
        this.beerMapper = beerMapper;
        this.fanoutExchange = fanoutExchange;
        this.rabbitTemplate = rabbitTemplate;
        this.beerRepository = beerRepository;
        this.beerInventoryClient = beerInventoryClient;
    }

    @Scheduled(initialDelay = 1000, fixedRate = 5000)
    public void checkForLowInventory() {
        Slice<Beer> beerSlice = beerRepository.findAll(PageRequest.of(0, 10));
        if (beerSlice.hasContent()) {
            do {
                List<Beer> beerList = beerSlice.getContent();
                beerList.forEach(beer -> {
                    Integer quantityOnHand = beerInventoryClient.getBeerQuantityOnHand(beer.getId());
                    log.debug("Quantity on hand for beer {} is {}", beer.getId(), quantityOnHand);
                    if (quantityOnHand <= beer.getMinOnHand()) {
                        BeerDto dto = beerMapper.beertoBeerDto(beer);
                        BrewBeerEvent brewbeerEvent = new BrewBeerEvent(dto);
                        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", brewbeerEvent);
                    }
                });
            } while (beerSlice.hasNext());
        }
    }

}
