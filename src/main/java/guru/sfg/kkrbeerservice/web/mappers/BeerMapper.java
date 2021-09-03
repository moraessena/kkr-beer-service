package guru.sfg.kkrbeerservice.web.mappers;

import guru.sfg.kkrbeerservice.domain.Beer;
import guru.sfg.commons.dto.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    @Mapping(source = "name", target = "beerName")
    @Mapping(source = "style", target = "beerStyle")
    BeerDto beertoBeerDto(Beer beer);

    @Mapping(source = "beerName", target = "name")
    @Mapping(source = "beerStyle", target = "style")
    Beer beerDtoToBeer(BeerDto beeroDto);
}
