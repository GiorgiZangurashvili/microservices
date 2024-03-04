package com.zangura.inventoryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemNotInStockException extends RuntimeException{

    public ItemNotInStockException() {
        super("Item is not in stock");
    }
}
