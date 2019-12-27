package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.Category;
import com.etsm.ETSM.Models.Role;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public interface HeaderService {
    Header setHeader(Principal principal);
    String getHeaderRole();
    List<Category> getHeaderCategories();
    User getUser();
}

@Service
class HeaderServiceImpl implements HeaderService{
    private CategoryRepository categoryRepository;


    private UserService userService;

    private static Header header;

    @Override
    public Header setHeader(Principal principal) {
        User user = new User();
        user.setRoles(new HashSet<Role>(Collections.singleton(Role.GUEST)));
        if (principal != null) {
            user = (User) userService.loadUserByUsername(principal.getName());
        }

        List<Category> categories = categoryRepository.findAll();
        header = new Header(categories, user);
        return header;
    }

    @Override
    public String getHeaderRole() {
        return header.getUser().getRoles().toArray()[0].toString();
    }

    @Override
    public List<Category> getHeaderCategories() {
        return header.getCategories();
    }

    @Override
    public User getUser() {
        return header.getUser();
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

class Header{
    private List<Category> categories;
    private User user;

    Header(List<Category> categories, User user) {
        this.categories = categories;
        this.user = user;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public User getUser() {
        return user;
    }
}
