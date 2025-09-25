package com.fitmeal.service;

import com.fitmeal.model.*;
import com.fitmeal.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final ProductImgRepository productImgRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository,
                          TagRepository tagRepository,
                          ProductImgRepository productImgRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.productImgRepository = productImgRepository;
    }

    @Transactional
    public Product createProduct(Product product) {
        // Ensure category exists
        Category category = categoryRepository.findById(product.getCategory().getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);

        // Handle tags
        List<Tag> persistedTags = new ArrayList<>();
        if (product.getTags() != null) {
            for (Tag tag : product.getTags()) {
                Tag existingTag = tagRepository.findByTagName(tag.getTagName())
                        .orElseGet(() -> tagRepository.save(tag));
                persistedTags.add(existingTag);
            }
        }
        product.setTags(persistedTags);

        // Handle product image
        if (product.getProductImg() != null) {
            ProductImg img = product.getProductImg();
            img.setProduct(product);
            product.setProductImg(img);
        }

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
