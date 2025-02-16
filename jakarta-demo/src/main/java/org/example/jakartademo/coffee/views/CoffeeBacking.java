package org.example.jakartademo.coffee.views;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.example.jakartademo.coffee.persistance.Coffee;
import org.example.jakartademo.coffee.persistance.CoffeeBeanType;
import org.example.jakartademo.coffee.persistance.CoffeeRepository;
import org.example.jakartademo.common.AlreadyExistsException;
import org.example.jakartademo.common.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/// ## Coffee Backing
/// The Coffee Backing (Bean) is the connector in the MVC model
/// [Picture](https://learning.oreilly.com/api/v2/epubs/urn:orm:book:9781484273104/files/images/454457_2_En_8_Chapter/454457_2_En_8_Fig1_HTML.jpg)
/// As in the book chapter [Jakarta Faces, Backing Bean](https://learning.oreilly.com/library/view/the-definitive-guide/9781484273104/html/454457_2_En_8_Chapter.xhtml)
/// recommended we use the Postfix `Backing` to mark a class as an MVC Faces Bean.
///
/// ### Session vs. View Scoped
/// As we described in the __product__ section, a View Scope is the right choice for a single Master-Detail page.
@Named
@ViewScoped
@Getter
@Log
public class CoffeeBacking implements java.io.Serializable {
    public enum EditMode { NONE, NEW, EDIT }

    private CoffeeData current = null;
    private EditMode editMode = EditMode.NONE;

    @Setter
    private String coffeeName;
    @Setter
    private Double coffeePrice;
    @Setter
    private String coffeeBeanType;

    @Inject
    private CoffeeRepository coffeeService;

    public List<CoffeeData> getCoffees() {
        return coffeeService.findAll().stream()
                .map(coffee -> new CoffeeData(
                        coffee.getId(),
                        coffee.getName(),
                        coffee.getPrice(),
                        coffee.getType()))
                .toList();
    }

    public void newCoffee() {
        log.info("New coffee");
        editMode = EditMode.NEW;
    }

    public void saveCoffee() {
        log.info("Save coffee");
        log.info(String.format("Saving %s %.2f %s", coffeeName, coffeePrice, coffeeBeanType));
        var beanType = CoffeeBeanType.valueOf(coffeeBeanType);
        var anotherCoffee = coffeeService.findByName(coffeeName);
        if (current == null) {
            if (anotherCoffee != null) {
                throw new AlreadyExistsException("Such a coffee already exists");
            }
            var coffee = coffeeService.save(Coffee.of(coffeeName, coffeePrice, beanType));
            current = new CoffeeData(coffee);
        } else {
            if (anotherCoffee != null && anotherCoffee.getId() != current.id()) {
                throw new AlreadyExistsException("Such a coffee already exists");
            }
            var coffee = Objects.requireNonNull(coffeeService.findById(current.id()));
            coffee.setName(coffeeName);
            coffee.setPrice(coffeePrice);
            coffee.setType(beanType);
            editMode = EditMode.EDIT;
            coffeeService.save(coffee);
        }
    }

    public void selectCoffee(Long id) {
        log.info("Select coffee with ID " + id);
        var coffee = coffeeService.findById(id);
        if (coffee != null) {
            current = new CoffeeData(coffee);
            coffeeName = coffee.getName();
            coffeePrice = coffee.getPrice();
            coffeeBeanType = coffee.getType().name();
            editMode = EditMode.EDIT;
        } else {
            throw new NotFoundException("Coffee with ID %d not found", id);
        }
    }

    public void cancel() {
        log.info("Cancel editing");
        current = null;
        coffeeName = null;
        coffeePrice = null;
        editMode = EditMode.NONE;
    }

    public boolean isSelected(Long id) {
        return (current != null && current.id() == id);
    }

    public List<String> coffeeBeanTypes() {
        return Stream.of(CoffeeBeanType.values()).map(CoffeeBeanType::name).toList();
    }
}
