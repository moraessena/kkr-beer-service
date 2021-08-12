package br.com.kkrbeerservice.services;

import br.com.kkrbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    void delete(UUID id);

    BeerDto getById(UUID id);

    BeerDto createOrUpdate(UUID id, BeerDto beerDto);
}
