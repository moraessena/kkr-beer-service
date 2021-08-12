package br.com.kkrbeerservice.web.controller;

import br.com.kkrbeerservice.services.BeerService;
import br.com.kkrbeerservice.web.model.BeerDto;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("id") UUID id) {
        BeerDto beer = beerService.getById(id);
        return ResponseEntity.ok(beer);
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
