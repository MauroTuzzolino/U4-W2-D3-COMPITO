import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product(1L, "Harry Potter", "Books", 120.0),
                new Product(2L, "Game of thrones", "Books", 95.0),
                new Product(3L, "Laptop", "Electronics", 999.0),
                new Product(4L, "Lord of the Rings", "Books", 150.0),
                new Product(5L, "Notebook", "Electronics", 5.0),
                new Product(6L, "Baby Bottle", "Baby", 25.0),
                new Product(7L, "Diapers", "Baby", 30.0),
                new Product(8L, "Action Figure", "Boys", 40.0),
                new Product(9L, "Toy Car", "Boys", 25.0)
        );

        Customer franco = new Customer(1L, "Franco", 1);
        Customer bob = new Customer(2L, "Bob", 2);
        Customer maria = new Customer(3L, "Maria", 3);
        Customer stefania = new Customer(4L, "Stefania", 2);

        List<Order> orders = Arrays.asList(
                new Order(101L, "SHIPPED", LocalDate.of(2025, 1, 15), LocalDate.of(2025, 1, 20),
                        Arrays.asList(products.get(0), products.get(5)), bob),
                new Order(102L, "PENDING", LocalDate.of(2025, 2, 5), LocalDate.of(2025, 2, 10),
                        Arrays.asList(products.get(2)), franco),
                new Order(103L, "DELIVERED", LocalDate.of(2025, 2, 20), LocalDate.of(2025, 2, 25),
                        Arrays.asList(products.get(6), products.get(1)), maria),
                new Order(104L, "CANCELLED", LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 6),
                        Arrays.asList(products.get(3), products.get(4)), stefania),
                new Order(105L, "DELIVERED", LocalDate.of(2021, 2, 5), LocalDate.of(2021, 2, 10),
                        Arrays.asList(products.get(0), products.get(8)), bob)
        );

        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 1);

        System.out.println("========== Parte 1 ==========");
        List<Product> expensiveBooks = products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
                .filter(p -> p.getPrice() > 100)
                .toList();

        System.out.println("Prodotti 'Books' con prezzo > 100");
        expensiveBooks.forEach(System.out::println);


        System.out.println("========== Parte 2 ==========");
        List<Order> babyOrders = orders.stream()
                .filter(order -> order.getProducts().stream()
                        .anyMatch(product -> product.getCategory().equalsIgnoreCase("Baby")))
                .toList();

        System.out.println("Ordini con almeno un prodotto nella categoria 'Baby':");
        babyOrders.forEach(System.out::println);


        System.out.println("========== Parte 3 ==========");
        List<Product> boysDiscounted = products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Boys"))
                .map(p -> new Product(
                        p.getId(),
                        p.getName(),
                        p.getCategory(),
                        p.getPrice() * 0.9
                )).toList();

        System.out.println("Prodotti 'Boys' con 10% di sconto:");
        boysDiscounted.forEach(System.out::println);


        System.out.println("========== Parte 4 ==========");
        List<Product> productsTier2InRange = new ArrayList<>();

        for (Order order : orders) {
            if (order.getCustomer().getTier() == 2 &&
                    !order.getOrderDate().isBefore(startDate) &&
                    order.getOrderDate().isBefore(endDate)) {
                productsTier2InRange.addAll(order.getProducts());
            }
        }

        System.out.println("Prodotti ordinati da clienti tier 2 tra 01-Feb-2021 e 01-Apr-2021:");
        productsTier2InRange.forEach(System.out::println);
    }
}
