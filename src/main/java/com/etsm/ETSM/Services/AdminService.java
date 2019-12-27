package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AdminService {
    boolean addNewProduct(Product product, String subCategoryName);

    List<User> findUsers();

    List<Attribute_Group> findAllAtrubutesGroups();

    Optional<User> findUserById(Long id);

    boolean addNewCategory(Category category);

    boolean addNewSubCategory(String CategoryName, SubCategory subCategory);

    boolean addNewMinorCategory(String subCategoryName, MinorCategory minorCategory);

    boolean addNewAttribute(Attribute attribute, String attribute_group);

    boolean addNewAttributeToProduct(String product, String attribute, ProductAttrValue oldProductAttrValue);

    List<User> findUsersPages(String page, int maxInPage);

    long findUsersCount();

//    void add100k();
}

@Service
class AdminServiceImpl implements AdminService {

    private UserRepository userRepository;

    SubCategoryRepository subCategoryRepository;

    ProductRepository productRepository;

    CategoryRepository categoryRepository;

    AttributeRepository attributeRepository;

    MinorCategoryRepository minorCategoryRepository;

    AttributeGroupRepository attributeGroupRepository;

    ProductAttrValueRepository productAttrValueRepository;

    public boolean addNewProduct(Product product, String minorCategoryName) {
        if (productRepository.findByName(product.getName()).isPresent()) {
            return false;
        }
        if (product.getPrice() < 0) {
            return false;
        }
        MinorCategory minorCategory = minorCategoryRepository.findByName(minorCategoryName).get();
        Product newProduct = new Product();
        newProduct.setDescription(product.getDescription());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setAttribute_groups(new ArrayList<>());
        newProduct.setMinorCategory_id(minorCategory);
        productRepository.saveAndFlush(newProduct);
        return true;
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Attribute_Group> findAllAtrubutesGroups() {
        return attributeGroupRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean addNewCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            return false;
        }
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        categoryRepository.saveAndFlush(newCategory);
        return true;
    }

    @Override
    public boolean addNewSubCategory(String categoryName, SubCategory subCategory) {
        if (subCategoryRepository.findByName(subCategory.getName()).isPresent()) {
            return false;
        }
        Category category = categoryRepository.findByName(categoryName).get();
        SubCategory newSubCategory = new SubCategory();
        newSubCategory.setCategory_id(category);
        newSubCategory.setName(subCategory.getName());
        subCategoryRepository.saveAndFlush(newSubCategory);
        return true;
    }

    @Override
    public boolean addNewMinorCategory(String subCategoryName, MinorCategory minorCategory) {
        if (minorCategoryRepository.findByName(minorCategory.getName()).isPresent()) {
            return false;
        }
        SubCategory subCategory = subCategoryRepository.findByName(subCategoryName).get();
        MinorCategory newMinorCategory = new MinorCategory();
        newMinorCategory.setSubcategory_id(subCategory);
        newMinorCategory.setName(minorCategory.getName());
        minorCategoryRepository.saveAndFlush(newMinorCategory);
        return true;
    }

    @Override
    public boolean addNewAttribute(Attribute attribute, String attribute_group) {
        if (attributeRepository.findByName(attribute.getName()).isPresent()) {
            return false;
        }
        Attribute newAttribute = new Attribute();
        newAttribute.setName(attribute.getName());
        newAttribute.setAttribute_groups(attributeGroupRepository.findByName(attribute_group).get());
        attributeRepository.saveAndFlush(newAttribute);

        return false;
    }

    @Override
    public boolean addNewAttributeToProduct(String product, String attribute, ProductAttrValue oldProductAttrValue) {
        if (productAttrValueRepository.findByValue(oldProductAttrValue.getValue()) != null) {
            Product UpProduct = productRepository.findByName(product).get();
            Attribute attributeForProduct = attributeRepository.findByName(attribute).get();
            Boolean isProductHasAttrGr = false;

            for (Attribute_Group attribute_group : UpProduct.getAttribute_groups()) {
                if (attribute_group.equals(attributeForProduct.getAttribute_groups())) {
                    isProductHasAttrGr = true;
                    break;
                }
            }
            if (!isProductHasAttrGr) {
                UpProduct.getAttribute_groups().add(attributeForProduct.getAttribute_groups());
            }
            ProductAttrValue newProductAttrValue = new ProductAttrValue();
            newProductAttrValue.setAttribute(attributeForProduct);
            newProductAttrValue.setProduct(UpProduct);
            newProductAttrValue.setValue(oldProductAttrValue.getValue());
            UpProduct.getProductAttrValue().add(newProductAttrValue);

            productRepository.save(UpProduct);
        }

        return true;
    }

    @Override
    public List<User> findUsersPages(String page, int maxInPage) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), maxInPage, Sort.by("login"));
        return userRepository.findAllByLoginLike("%%", pageable);
    }

    @Override
    public long findUsersCount() {
        return userRepository.count();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSubCategoryRepository(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setAttributeRepository(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Autowired
    public void setMinorCategoryRepository(MinorCategoryRepository minorCategoryRepository) {
        this.minorCategoryRepository = minorCategoryRepository;
    }

    @Autowired
    public void setAttributeGroupRepository(AttributeGroupRepository attributeGroupRepository) {
        this.attributeGroupRepository = attributeGroupRepository;
    }

    @Autowired
    public void setProductAttrValueRepository(ProductAttrValueRepository productAttrValueRepository) {
        this.productAttrValueRepository = productAttrValueRepository;
    }


//    @Override
//    public void add100k() {
//        String[] description = new String[]{
//                "Простое описание товара.",
//                "Описание лучшего товара на свете, других таких не найдешь!!!",
//                "Лучший товар, что можно только найти на белом свете!!!",
//                "Описание для товара из списка товаров.",
//                "Товар. Простой. Как есть."
//        };
//
//        String[] nameP1 = new String[]{
//                "Bri",
//                "Horn",
//                "Mer",
//                "Flex",
//                "Lot"
//        };
//
//        String[] nameP2 = new String[]{
//                "tron",
//                "lotl",
//                "end",
//                "nex",
//                "gol"
//        };
//
//        String[] nameP3 = new String[]{
//                "X",
//                "T",
//                "Y",
//                "W",
//                "S"
//        };
//
//        String[] colors = new String[]{
//                "Красный",
//                "Черный",
//                "Коричневый",
//                "Желтый",
//                "Зеленый"
//        };
//
//        String[] images = new String[]{
//                "https://avatars.mds.yandex.net/get-zen_doc/1708669/pub_5caf954c0b1fb500b33a91a4_5caf9635db481700b3e2c67c/scale_1200",
//                "https://avatars.mds.yandex.net/get-pdb/750514/b80354ed-2ddf-41b4-9f09-38cdb7699e96/s1200",
//                "https://avatars.mds.yandex.net/get-zen_doc/1548443/pub_5d9785b6fbe6e700b099ea62_5d978fa58f011100b48e9cad/scale_1200",
//                "https://user43214.clients-cdnnow.ru/image/cache/catalog/197/197762_73f81-1000x1000.jpg",
//                "https://i.pinimg.com/originals/19/7a/47/197a47d1c82df35e129da50cc1a9cfaf.jpg"
//        };
//
//        for (MinorCategory minorCategory:minorCategoryRepository.findAll()) {
//            for (int i = 0; i < (new Random().nextInt(5) + 10); i++) {
//
//                String name = nameP1[new Random().nextInt(nameP1.length)]
//                        + nameP2[new Random().nextInt(nameP2.length)]
//                        + " "
//                        + nameP3[new Random().nextInt(nameP3.length)]
//                        + i;
//
//                Product newProduct = new Product();
//                ProductAttrValue newProductAttrValue = new ProductAttrValue();
//                newProduct.setAttribute_groups(new ArrayList<>());
//                newProduct.setProductAttrValue(new ArrayList<>());
//                newProduct.setDescription(description[new Random().nextInt(description.length)]);
//                newProduct.setName(name);
//                newProduct.setPrice((new Random().nextInt(100000) + 100));
//                newProduct.setMinorCategory_id(minorCategory);
//                newProduct.setImg(images[new Random().nextInt(images.length)]);
//                newProduct.setCount((new Random().nextInt(100) + 2));
//                newProduct.getAttribute_groups().add(attributeGroupRepository.findById(1L).get());
//                newProduct.getAttribute_groups().add(attributeGroupRepository.findById(((long)new Random().nextInt((int)attributeGroupRepository.count()) + 1)).get());
//                newProduct.getAttribute_groups().add(attributeGroupRepository.findById(((long)new Random().nextInt((int)attributeGroupRepository.count()) + 1)).get());
//                newProduct.getAttribute_groups().add(attributeGroupRepository.findById(((long)new Random().nextInt((int)attributeGroupRepository.count()) + 1)).get());
//                newProductAttrValue.setAttribute(attributeRepository.findByName("Цвет").get());
//                newProductAttrValue.setProduct(newProduct);
//                newProductAttrValue.setValue(colors[new Random().nextInt(colors.length)]);
//                newProduct.getProductAttrValue().add(newProductAttrValue);
//
//                productRepository.saveAndFlush(newProduct);
//            }
//        }
//    }
}
