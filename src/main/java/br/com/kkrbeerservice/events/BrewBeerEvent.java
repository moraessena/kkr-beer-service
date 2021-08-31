package br.com.kkrbeerservice.events;

import br.com.kkrbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
