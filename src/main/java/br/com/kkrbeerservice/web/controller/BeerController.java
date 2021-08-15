package br.com.kkrbeerservice.web.controller;

import br.com.kkrbeerservice.services.beer.BeerService;
import br.com.kkrbeerservice.web.model.BeerDto;
import br.com.kkrbeerservice.web.model.PagedBeerDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping(name = "findById", value = "/{id}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("id") UUID id) {
        BeerDto beer = beerService.getById(id);
        return ResponseEntity.ok(beer);
    }

    @GetMapping(name = "listBeers")
    public ResponseEntity<PagedBeerDto> listBeers(
            @PageableDefault(size = 20, direction = Sort.Direction.ASC, sort = {"name"}) Pageable page) {
        PagedBeerDto beers = beerService.listBeers(page);
        return ResponseEntity.ok(beers);
    }

    @GetMapping(name = "listBeersByName", params = {"name"})
    public ResponseEntity<PagedBeerDto> listBeersByName(
            @RequestParam(name = "name") String name,
            @PageableDefault(size = 20, direction = Sort.Direction.ASC, sort = {"name"}) Pageable page) {
        PagedBeerDto beers = beerService.listBeersByName(name, page);
        return ResponseEntity.ok(beers);
    }

    @GetMapping(name = "listBeersByNameAndStyle", params = {"name", "style"})
    public ResponseEntity<PagedBeerDto> listBeersByNameAndStyle(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "style") String style,
            @PageableDefault(size = 20, direction = Sort.Direction.ASC, sort = {"name"}) Pageable page) {
        PagedBeerDto beers = beerService.listBeersByNameAndStyle(name, style, page);
        return ResponseEntity.ok(beers);
    }

    @PostMapping
    public ResponseEntity<BeerDto> createBeer(@Validated @RequestBody BeerDto beerDto) {
        beerService.createOrUpdate(null, beerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateBeer(@PathVariable("id") UUID id, @Validated @RequestBody BeerDto beerDto) {
        beerService.createOrUpdate(id, beerDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable("id") UUID id) {
        beerService.delete(id);
        return ResponseEntity.accepted().build();
    }

}
