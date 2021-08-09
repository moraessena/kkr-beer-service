package br.com.kkrbeerservice.web.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "beerName"})
public class BeerDto {
    private UUID id;
    private String beerName;
    private Style beerStyle;
    private Long upc;
    private BigDecimal price;
    private Integer stockQuantity;

    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum Style {
        LAGER,
        PILSENER,
        STOUT,
        GOSE,
        PORTER,
        ALE,
        WHEAT,
        IPA,
        PALE_ALE,
        SAISON;
    }
}
