package com.example.springgettingstarted.ioc;

import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

/// ## IoC Test
/// `@Component` = Primitive type
/// `@Service` = Service Bean
/// `@Repository` = Repository Bean
/// ```shell
///  class x () {}
/// ```
@Component
public class Store {
    /*
    @Inject
    @Qualifier("item")
    private Item item;

    public Store() {
        assert(this.item != null);
    }
    */

    private final Item item2;

    private final Logger logger;

    @Inject
    public Store(@Qualifier("item2") Item item2,  Logger logger) {
        this.item2 = item2;
        this.logger = logger;
    }

    public Item getItem() {
        logger.info("getItem()");
        return item2;
    }


}
