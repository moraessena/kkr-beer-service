package br.com.kkrbeerservice.services.inventory;

import br.com.kkrbeerservice.configuration.BeerInventoryClientConfiguration;
import br.com.kkrbeerservice.services.inventory.dto.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class BeerInventoryClientImpl implements BeerInventoryClient {

    private String inventoryPath = "/api/v1/beer/{id}/inventory";

    @Autowired
    @Qualifier(BeerInventoryClientConfiguration.INVENTORY_CLIENT_BEAN)
    private RestTemplate apiClient;

    @Override
    public Integer getBeerQuantityOnHand(UUID id) {
        try {
            ResponseEntity<List<BeerInventoryDto>> response = apiClient.exchange(
                    inventoryPath,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<BeerInventoryDto>>() {
                    },
                    id.toString());
            if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
                return response.getBody().stream().mapToInt(BeerInventoryDto::getQuantityOnHand).sum();
            }
        } catch (Exception e) {
            log.error("Failed to retrieve inventory information for beer {}", id.toString());
            return null;
        }
        return null;
    }
}
