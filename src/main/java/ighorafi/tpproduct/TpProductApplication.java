package ighorafi.tpproduct;

import ighorafi.tpproduct.entity.Category;
import ighorafi.tpproduct.entity.Product;
import ighorafi.tpproduct.repository.CategoryRepository;
import ighorafi.tpproduct.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class TpProductApplication implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(TpProductApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category();
        category1.setCode(1L);
        category1.setLabel("Electronics");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setCode(2L);
        category2.setLabel("Clothing");
        categoryRepository.save(category2);

        Category category3 = new Category();
        category3.setCode(3L);
        category3.setLabel("Books");
        categoryRepository.save(category3);



        Product product1 = new Product();
        product1.setCode(1L);
        product1.setLabel("Laptop");
        product1.setPrice(1000L);
        product1.setCategory(category1);
        productRepository.save(product1);

    }

}