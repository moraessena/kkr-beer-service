package guru.sfg.commons.events;

import guru.sfg.commons.dto.BeerDto;

public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent() {
        super();
    }

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
