package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.ProductRepository;
import com.etsm.ETSM.Repositories.SubCategoryRepository;
import com.etsm.ETSM.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AdminService{
    void addNewProduct(Product product);
    List<User> findUsers();
    Optional<User> findUserById(Long id);
}

@Service
class AdminServiceImpl implements AdminService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    ProductRepository productRepository;

    public void addNewProduct(Product product){
        Product newProduct = new Product();
        newProduct.setDescription(product.getDescription());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setSubCategory_id(this.subCategoryRepository.findById(1L).get());
        productRepository.saveAndFlush(newProduct);
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
