import java.time.LocalDate;
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
                new Product(7L, "Diapers", "Baby", 30.0)
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
                        Arrays.asList(products.get(3), products.get(4)), stefania)
        );

        System.out.println("========== Parte 1 ==========");
        List<Product> expensiveBooks = products.stream().filter(p -> p.getCategory().equalsIgnoreCase("Books")).filter(p -> p.getPrice() > 100).toList();

        System.out.println("Prodotti 'Books' con prezzo > 100");
        expensiveBooks.forEach(System.out::println);


        System.out.println("========== Parte 2 ==========");
        List<Order> babyOrders = orders.stream()
                .filter(order -> order.getProducts().stream().anyMatch(product -> product.getCategory().equalsIgnoreCase("Baby"))).toList();

        System.out.println("Ordini con almeno un prodotto nella categoria 'Baby':");
        babyOrders.forEach(System.out::println);
    }
}
