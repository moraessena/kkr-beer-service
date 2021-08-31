package br.com.kkrbeerservice.events;

import br.com.kkrbeerservice.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
