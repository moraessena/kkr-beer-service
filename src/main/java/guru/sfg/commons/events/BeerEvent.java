package guru.sfg.commons.events;

import guru.sfg.commons.dto.BeerDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = -1977931963861260289L;
    private final BeerDto beerDto;

    public BeerEvent() {
        this.beerDto = null;
    }

    public BeerEvent(BeerDto beerDto) {
        this.beerDto = beerDto;
    }
}
