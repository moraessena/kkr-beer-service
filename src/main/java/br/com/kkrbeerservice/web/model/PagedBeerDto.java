package br.com.kkrbeerservice.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PagedBeerDto extends PageImpl<BeerDto> implements Serializable {

    private static final long serialVersionUID = 3632932664750520821L;

    public PagedBeerDto(List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PagedBeerDto(List<BeerDto> content) {
        super(content);
    }
}
