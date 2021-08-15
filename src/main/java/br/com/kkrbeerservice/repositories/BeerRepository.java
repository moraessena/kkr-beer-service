package br.com.kkrbeerservice.repositories;

import br.com.kkrbeerservice.domain.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    Page<Beer> findByNameAndStyle(String name, String style, Pageable page);

    Page<Beer> findByName(String name, Pageable page);
}
