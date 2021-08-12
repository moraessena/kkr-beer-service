package br.com.kkrbeerservice.services;

import br.com.kkrbeerservice.domain.Beer;
import br.com.kkrbeerservice.exceptions.NotFoundException;
import br.com.kkrbeerservice.repositories.BeerRepository;
import br.com.kkrbeerservice.web.mappers.BeerMapper;
import br.com.kkrbeerservice.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    public BeerServiceImpl(BeerMapper beerMapper,
                           BeerRepository beerRepository) {
        this.beerMapper = beerMapper;
        this.beerRepository = beerRepository;
    }

    @Override
    public void delete(UUID id) {
        beerRepository.deleteById(id);
    }

    @Override
    public BeerDto getById(UUID id) {
        Optional<Beer> beer = beerRepository.findById(id);
        return beer.map(beerMapper::beertoBeerDto).orElseThrow(() -> new NotFoundException("Beer not found"));
    }

    @Override
    public BeerDto createOrUpdate(UUID id, BeerDto beerDto) {
        Beer beer = beerMapper.beerDtoToBeer(beerDto);
        Beer existing;
        if (id != null) {
            existing = beerRepository.findById(id).orElseThrow(() -> new NotFoundException("Beer not found"));
            existing.updateWith(beer);
            beerRepository.save(existing);
        } else {
            existing = beerRepository.save(beer);
        }
        return beerMapper.beertoBeerDto(existing);
    }
}
