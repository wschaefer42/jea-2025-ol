package com.example.springgettingstarted;

import com.example.springgettingstarted.ioc.Store;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
public class StoreTest {
    @Inject
    private Store store;

    @Test
    void test() {
        assert (store != null && Objects.equals(store.getItem().toString(), "item2"));
    }
}
