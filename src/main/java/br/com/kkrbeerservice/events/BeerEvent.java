package br.com.kkrbeerservice.events;

import br.com.kkrbeerservice.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = -1977931963861260289L;
    private final BeerDto beerDto;
}
