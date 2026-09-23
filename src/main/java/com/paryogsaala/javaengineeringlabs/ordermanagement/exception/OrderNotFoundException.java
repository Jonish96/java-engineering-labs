package com.paryogsaala.javaengineeringlabs.ordermanagement.exception;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) {
            super(message);
        }
}
