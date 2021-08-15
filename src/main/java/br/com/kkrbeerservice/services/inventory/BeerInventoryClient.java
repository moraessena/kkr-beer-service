package br.com.kkrbeerservice.services.inventory;

import java.util.UUID;

public interface BeerInventoryClient {
    Integer getBeerQuantityOnHand(UUID id);
}
