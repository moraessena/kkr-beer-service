package guru.sfg.kkrbeerservice.web.mappers;

import guru.sfg.kkrbeerservice.domain.Beer;
import guru.sfg.kkrbeerservice.services.inventory.BeerInventoryClient;
import guru.sfg.commons.dto.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper {

    @Autowired
    private BeerMapper beerMapper;

    @Autowired
    private BeerInventoryClient beerInventoryClient;

    @Override
    public BeerDto beertoBeerDto(Beer beer) {
        BeerDto dto = beerMapper.beertoBeerDto(beer);
        Integer quantityOnHand = beerInventoryClient.getBeerQuantityOnHand(beer.getId());
        dto.setStockQuantity(quantityOnHand);
        return dto;
    }

}
