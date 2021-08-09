package br.com.kkrbeerservice.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PagedBeerDto extends PageImpl<BeerDto> {
    public PagedBeerDto(List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PagedBeerDto(List<BeerDto> content) {
        super(content);
    }
}
