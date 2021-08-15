package br.com.kkrbeerservice.services.beer;

import br.com.kkrbeerservice.domain.Beer;
import br.com.kkrbeerservice.exceptions.NotFoundException;
import br.com.kkrbeerservice.repositories.BeerRepository;
import br.com.kkrbeerservice.web.mappers.BeerMapper;
import br.com.kkrbeerservice.web.model.BeerDto;
import br.com.kkrbeerservice.web.model.PagedBeerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public PagedBeerDto listBeers(Pageable page) {
        Page<BeerDto> result = beerRepository.findAll(page).map(beerMapper::beertoBeerDto);
        if (!result.hasContent()) throw new NotFoundException("Beer not found");
        return new PagedBeerDto(result.getContent(), page, result.getTotalElements());
    }

    @Override
    public PagedBeerDto listBeersByName(String name, Pageable page) {
        Page<BeerDto> result = beerRepository.findByName(name, page).map(beerMapper::beertoBeerDto);
        if (!result.hasContent()) throw new NotFoundException("Beer not found");
        return new PagedBeerDto(result.getContent(), page, result.getTotalElements());
    }

    @Override
    public PagedBeerDto listBeersByNameAndStyle(String name, String style, Pageable page) {
        Page<BeerDto> result = beerRepository.findByNameAndStyle(name, style, page).map(beerMapper::beertoBeerDto);
        if (!result.hasContent()) throw new NotFoundException("Beer not found");
        return new PagedBeerDto(result.getContent(), page, result.getTotalElements());
    }
}
