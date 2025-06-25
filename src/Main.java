import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product(1L, "Harry Potter", "Books", 120.0),
                new Product(2L, "Game of thrones", "Books", 95.0),
                new Product(3L, "Laptop", "Electronics", 999.0),
                new Product(4L, "Lord of the Rings", "Books", 150.0),
                new Product(5L, "Notebook", "Electronics", 5.0)
        );

        List<Product> expensiveBooks = products.stream().filter(p -> p.getCategory().equalsIgnoreCase("Books")).filter(p -> p.getPrice() > 100).collect(Collectors.toList());

        System.out.println("Prodotti 'Books' con prezzo > 100");
        expensiveBooks.forEach(System.out::println);
    }
}
