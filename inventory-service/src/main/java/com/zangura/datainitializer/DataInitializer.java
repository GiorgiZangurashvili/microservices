package com.zangura.datainitializer;

import com.zangura.domain.Inventory;
import com.zangura.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;


    @Override
    public void run(String... args) throws Exception {
        if (inventoryRepository.count() != 0) {
            return;
        }

        Inventory lenovo = new Inventory();
        lenovo.setSkuCode("lenovo_v15");
        lenovo.setQuantity(20);

        Inventory hp = new Inventory();
        hp.setSkuCode("hp_pavilion_dv7");
        hp.setQuantity(50);

        inventoryRepository.save(lenovo);
        inventoryRepository.save(hp);

    }

}
