package guru.sfg.kkrbeerservice.services.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerInventoryDto {
    private UUID id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private UUID beerId;
    private Integer quantityOnHand;
}
