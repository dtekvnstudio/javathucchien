package com.shop.repository;

import com.shop.model.Category;
import com.shop.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class ProductRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveAndFindProductById() {
        Category electronics = categoryRepository.save(new Category("Điện tử"));
        Category phones = categoryRepository.save(new Category("Điện thoại", electronics));

        Product product = new Product(
                "iPhone 15",
                new BigDecimal("25990000.00"),
                10,
                phones
        );
        product.setDescription("Smartphone Apple");
        product.setImageUrl("https://example.com/iphone15.jpg");

        Product saved = productRepository.save(product);

        Optional<Product> found = productRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("iPhone 15", found.get().getName());
        assertEquals(0, new BigDecimal("25990000.00").compareTo(found.get().getPrice()));
        assertEquals(10, found.get().getStock());
        assertEquals(phones.getId(), found.get().getCategory().getId());
        assertNotNull(found.get().getCreatedAt());
    }

    @Test
    void findProductsByCategoryId() {
        Category phones = categoryRepository.save(new Category("Điện thoại"));

        productRepository.save(new Product("iPhone 15", new BigDecimal("25990000.00"), 10, phones));
        productRepository.save(new Product("Samsung Galaxy S24", new BigDecimal("19990000.00"), 5, phones));

        List<Product> products = productRepository.findByCategoryId(phones.getId());
        assertEquals(2, products.size());
    }

    @Test
    void findRootCategories() {
        Category electronics = categoryRepository.save(new Category("Điện tử"));
        categoryRepository.save(new Category("Điện thoại", electronics));

        List<Category> roots = categoryRepository.findByParentIsNull();
        assertFalse(roots.isEmpty());
        assertTrue(roots.stream().anyMatch(c -> "Điện tử".equals(c.getName())));
        assertTrue(roots.stream().noneMatch(c -> "Điện thoại".equals(c.getName())));
    }
}
