package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Repositories.CategoryRepository;
import com.etsm.ETSM.Repositories.SubCategoryRepository;
import com.etsm.ETSM.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    private UserService userService;
    MainService mainService;
    UserInformationService userInformationService;
    private CategoryRepository categoryRepository;
    private SubCategoryRepository subCategoryRepository;
    private ProductService productService;

    public AdminController(AdminService adminService, UserService userService, MainService mainService, UserInformationService userInformationService, CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository, ProductService productService) {
        this.adminService = adminService;
        this.userService = userService;
        this.mainService = mainService;
        this.userInformationService = userInformationService;
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.productService = productService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @Autowired
    public void setUserInformationService(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setSubCategoryRepository(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ModelAndView getAllUsers(Principal principal) {
        User userForRole = new User();
        userForRole.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        if (principal != null) {
            userForRole = (User) userService.loadUserByUsername(principal.getName());
        }
        return new ModelAndView("admin/all",
                Map.of("users", adminService.findUsers(),
                        "role", userForRole.getRoles().toArray()[0].toString()),
                HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ModelAndView user(@PathVariable long userId, Principal principal) {
        User userForRole = new User();
        userForRole.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        if (principal != null) {
            userForRole = (User) userService.loadUserByUsername(principal.getName());
        }

        User finalUserForRole = userForRole;

        return adminService.findUserById(userId)
                .map(user -> new ModelAndView("admin/user",
                        Map.of("user",user,
                                "role", finalUserForRole.getRoles().toArray()[0].toString()), HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error","Couldn't find a user"), HttpStatus.NOT_FOUND));

    }

    @GetMapping("{userId}/deleteUser")
    public String deleteOneUser(@PathVariable long userId) {
        Optional<User> optionalUser = adminService.findUserById(userId);
        optionalUser.ifPresent(user -> userInformationService.deleteUser(user));
        return "redirect:/admin";
    }

    @GetMapping("/addProduct")
    public ModelAndView AddProduct(Principal principal){
        User userForRole = new User();
        userForRole.setRoles(new HashSet<Role>(Collections.singleton(Role.USER)));
        if (principal != null) {
            userForRole = (User) userService.loadUserByUsername(principal.getName());
        }
        Product product = new Product();
        String subCategoryName = "";
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        return new ModelAndView("admin/addProduct",
                Map.of("product", product,
                        "role", userForRole.getRoles().toArray()[0].toString(),
                        "subCategoryName",subCategoryName,
                        "subCategoryList",subCategoryList),
                HttpStatus.OK);
    }


    @PostMapping("/addProduct")
    public String AddProduct(@ModelAttribute Product product,
                             @ModelAttribute("subCategoryName") String subCategoryName){
        adminService.addNewProduct(product,subCategoryName);
        return "redirect:/catalog/list";
    }

    @GetMapping("/addCategory")
    public ModelAndView addCategory() {
        Category category = new Category();
        List<Category> categoryList = categoryRepository.findAll();
        return new ModelAndView("admin/addCategory",
                Map.of("category", category,
                        "categoryList",categoryList),
                HttpStatus.OK);
    }

    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute Category category) {
        adminService.addNewCategory(category);
        return "redirect:/admin/addCategory";
    }

    @GetMapping("/addSubCategory")
    public ModelAndView addSubCategory() {
        SubCategory subCategory = new SubCategory();
        String categoryName = "";
        List<Category> categoryList = categoryRepository.findAll();
        return new ModelAndView("admin/addSubCategory",
                Map.of("subCategory", subCategory,
                        "categoryName",categoryName,
                        "categoryList",categoryList
                        ),
                HttpStatus.OK);
    }

    @PostMapping("/addSubCategory")
    public String addSubCategory(@ModelAttribute SubCategory subCategory,
                                 @ModelAttribute("categoryName") String categoryName
                                 ) {
        adminService.addNewSubCategory(categoryName,subCategory);
        return "redirect:/admin";
    }

    @GetMapping("/addAttribute")
    public ModelAndView addAttribute() {
        Attribute attribute = new Attribute();
        return new ModelAndView("admin/addAttribute",
                Map.of("attribute",attribute),
                HttpStatus.OK);
    }

    @PostMapping("/addAttribute")
    public String addAttribute(@ModelAttribute Attribute attribute) {
        adminService.addNewAttribute(attribute);
        return "redirect:/admin";
    }
@Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
