package guru.sfg.kkrbeerservice.services.beer;

import guru.sfg.commons.dto.BeerDto;
import guru.sfg.kkrbeerservice.web.model.PagedBeerDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeerService {
    void delete(UUID id);

    BeerDto getById(UUID id);

    BeerDto getByUPC(String upc);

    BeerDto createOrUpdate(UUID id, BeerDto beerDto);

    PagedBeerDto listBeers(Pageable page);

    PagedBeerDto listBeersByName(String name, Pageable page);

    PagedBeerDto listBeersByNameAndStyle(String name, String style, Pageable page);
}
