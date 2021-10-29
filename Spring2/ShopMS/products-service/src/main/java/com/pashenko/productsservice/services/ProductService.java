package com.pashenko.productsservice.services;


import com.pashenko.contract.Product;
import com.pashenko.contract.ProductDTO;
import com.pashenko.productsservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryService categoryService;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<Product> getAllProducts() {
        return (List<Product>)(productRepository.findAll());
    }

    public List<Product> getAllProductsWithFilter(Specification<Product> productSpecs) {
        return (List<Product>)(productRepository.findAll(productSpecs));
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Page<Product> getAllProductsByPage(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public Page<Product> getProductsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Product> productSpecification) {
        return productRepository.findAll(productSpecification, PageRequest.of(pageNumber, pageSize));
    }

    public boolean isProductWithTitleExists(String productTitle) {
        return productRepository.findOneByTitle(productTitle) != null;
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product addNewProduct(ProductDTO productDTO) {
        Product p = productRepository.findOneByTitle(productDTO.getTitle());
        if(p != null){
            return null;
        }
        p = new Product();
        p.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()));
        p.setVendorCode(productDTO.getVendorCode());
        p.setTitle(productDTO.getTitle());
        p.setShortDescription(productDTO.getShortDescription());
        p.setFullDescription(productDTO.getFullDescription());
        p.setPrice(productDTO.getPrice());
        return productRepository.save(p);
    }

}
