package br.com.kkrbeerservice.web.mappers;

import br.com.kkrbeerservice.domain.Beer;
import br.com.kkrbeerservice.services.inventory.BeerInventoryClient;
import br.com.kkrbeerservice.web.model.BeerDto;
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
