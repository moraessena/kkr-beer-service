package br.com.kkrbeerservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
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
    @Null
    private UUID id;

    @NotBlank
    private String beerName;

    @NotNull
    private Style beerStyle;

    @NotNull
    @Positive
    private Long upc;

    @NotNull
    @Positive
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;
    private Integer stockQuantity;

    @Null
    private Integer version;

    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.ss", shape = JsonFormat.Shape.STRING)
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
