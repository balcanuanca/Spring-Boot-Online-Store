package com.onlinestore.onlinestore.service;

import com.onlinestore.onlinestore.data.FileStorageRepository;
import com.onlinestore.onlinestore.data.ProductRepository;
import com.onlinestore.onlinestore.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final FileStorageRepository fileStorageRepository;

    public ProductService(ProductRepository productRepository, FileStorageRepository fileStorageRepository) {
        this.productRepository = productRepository;
        this.fileStorageRepository = fileStorageRepository;
    }

    @Transactional
    public Product save(Product product, InputStream photoStream) {
        Product savedProduct = productRepository.save(product);
        fileStorageRepository.save(product.getPhotoFile(),photoStream);
        return savedProduct;
    }

    public Optional<Product> findById(Long aLong) {
        return productRepository.findById(aLong);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteAllById(Iterable<Long> ids) {
        Iterable<Product> productsToDelete = productRepository.findAllById(ids);
        Stream<Product> productsStream = StreamSupport.stream(productsToDelete.spliterator(), false);
        Set<String> fileNames = productsStream
                .map(Product::getPhotoFile)
                .collect(Collectors.toSet());
        productRepository.deleteAllById(ids);
        fileStorageRepository.deleteAllByName(fileNames);
    }

    public void deleteById(Long id) {
        fileStorageRepository.deleteByName(findById(id).get().getPhotoFile());
        productRepository.deleteById(id);
    }

}
