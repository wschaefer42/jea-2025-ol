package com.example.springclassroom.ioc;

import jakarta.inject.Inject;

public class AppStore {
    private final Store store;

    @Inject
    public AppStore(Store store) {
        this.store = store;
    }
}
