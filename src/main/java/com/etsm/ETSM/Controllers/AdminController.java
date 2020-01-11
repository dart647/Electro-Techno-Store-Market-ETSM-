package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.*;
import com.etsm.ETSM.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    MainService mainService;
    UserInformationService userInformationService;
    private ProductService productService;
    private HeaderService headerService;
    private UserService userService;
    private ERPService erpService;

    public AdminController(AdminService adminService,
                           MainService mainService,
                           UserInformationService userInformationService,
                           ProductService productService,
                           UserService userService,
                           ERPService erpService) {
        this.adminService = adminService;
        this.mainService = mainService;
        this.userInformationService = userInformationService;
        this.productService = productService;
        this.userService = userService;
        this.erpService = erpService;
    }

    @Autowired
    public void setHeaderService(HeaderService headerService) {
        this.headerService = headerService;
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
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setErpService(ERPService erpService) {
        this.erpService = erpService;
    }

    @GetMapping("/all")
    public ModelAndView getAllUsers(@RequestParam(name = "page", defaultValue = "0")String page, Principal principal) {
        headerService.setHeader(principal);
        List<Integer> pages = new ArrayList<>();
        int maxInPage = 20;
        List<User> adminServiceUsers = adminService.findUsersPages(page, maxInPage);
        for (int i = 0; i < Math.ceil((float)adminService.findUsersCount() / maxInPage); i++) {
            pages.add(i);
        }
        return new ModelAndView("admin/all",
                Map.of("users", adminServiceUsers,
                        "role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories(),
                        "pages", pages),
                HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ModelAndView user(@PathVariable long userId, Principal principal) {
        headerService.setHeader(principal);

        return adminService.findUserById(userId)
                .map(user -> new ModelAndView("admin/user",
                        Map.of("user", user,
                                "role", headerService.getHeaderRole(),
                                "categories", headerService.getHeaderCategories()),
                        HttpStatus.OK))
                .orElseGet(() -> new ModelAndView("errors/404",
                        Map.of("error", "Couldn't find a user"), HttpStatus.NOT_FOUND));

    }

    @GetMapping("{userId}/deleteUser")
    public String deleteOneUser(@PathVariable long userId) {
        Optional<User> optionalUser = adminService.findUserById(userId);
        optionalUser.ifPresent(user -> userInformationService.deleteUser(user));
        return "redirect:/admin/all";
    }

    @GetMapping("{userId}/block")
    public String deactivateUser(@PathVariable long userId) {
        userService.deactivateUser(userId);
        return "redirect:/admin/all";
    }

    @PostMapping("{userId}/changeRole")
    public String changeUserRole(@PathVariable long userId,
                                 @ModelAttribute("roleName") String roleName ) {
        userService.changeUserRole(userId,roleName);
        return "redirect:/admin/all";
    }

    @GetMapping("/addProduct")
    public ModelAndView AddProduct(@RequestParam(name = "page", defaultValue = "0")String page, Principal principal) {
        headerService.setHeader(principal);
        Product product = new Product();
        String minorCategoryName = "";

        List<Integer> pages = new ArrayList<>();
        int maxInPage = 20;
        List<Product> allProductsPages = productService.findAllProductsPages(page, maxInPage) ;
        for (int i = 0; i < Math.ceil((float)productService.findAllProductsCount() / maxInPage); i++) {
            pages.add(i);
        }

        return new ModelAndView("admin/addProduct",
                Map.of("product", product,
                        "role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories(),
                        "minorCategoryName", minorCategoryName,
                        "categoryList", productService.findCategories(),
                        "subCategoryList", productService.findSubCategories(),
                        "minorCategoryList", productService.findMinorCategories(),
                        "productList", allProductsPages,
                        "pages", pages),
                HttpStatus.OK);
    }


    @PostMapping("/addProduct")
    public String AddProduct(@ModelAttribute Product product,
                             @ModelAttribute("minorCategoryName") String minorCategoryName) {
        adminService.addNewProduct(product, minorCategoryName);
        return "redirect:/admin/addProduct";
    }

    @GetMapping("/addCategory")
    public ModelAndView addCategory(Principal principal) {
        headerService.setHeader(principal);
        Category category = new Category();
        return new ModelAndView("admin/addCategory",
                Map.of("category", category,
                        "categoryList", productService.findCategories(),
                        "productList", productService.findAllProducts(),
                        "role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories()),
                HttpStatus.OK);
    }

    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute Category category) {
        adminService.addNewCategory(category);
        return "redirect:/admin/addCategory";
    }

    @GetMapping("/addSubCategory")
    public ModelAndView addSubCategory(Principal principal) {
        headerService.setHeader(principal);
        SubCategory subCategory = new SubCategory();
        String categoryName = "";
        return new ModelAndView("admin/addSubCategory",
                Map.of("subCategory", subCategory,
                        "categoryName", categoryName,
                        "categoryList", productService.findCategories(),
                        "subCategoryList", productService.findSubCategories(),
                        "role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories()),
                HttpStatus.OK);
    }

    @PostMapping("/addSubCategory")
    public String addSubCategory(@ModelAttribute SubCategory subCategory,
                                 @ModelAttribute("categoryName") String categoryName
    ) {
        adminService.addNewSubCategory(categoryName, subCategory);
        return "redirect:/admin/addSubCategory";
    }

    @GetMapping("/addMinorCategory")
    public ModelAndView addMinorCategory(Principal principal) {
        headerService.setHeader(principal);
        MinorCategory minorCategory = new MinorCategory();
        String subCategoryName = "";

        return new ModelAndView("admin/addMinorCategory",
                Map.of("minorCategory", minorCategory,
                        "subCategoryName", subCategoryName,
                        "subCategoryList", productService.findAllSubCategories(),
                        "minorCategoryList", productService.findMinorCategories(),
                        "role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories()),
                HttpStatus.OK);
    }

    @PostMapping("/addMinorCategory")
    public String addMinorCategory(@ModelAttribute MinorCategory minorCategory,
                                   @ModelAttribute("subCategoryName") String subCategoryName) {
        adminService.addNewMinorCategory(subCategoryName, minorCategory);
        return "redirect:/admin/addMinorCategory";
    }

    @GetMapping("/addAttribute")
    public ModelAndView addAttribute(@RequestParam(name = "page", defaultValue = "0")String page, Principal principal) {
        headerService.setHeader(principal);
        Attribute attribute = new Attribute();
        List<Integer> pages = new ArrayList<>();
        int maxInPage = 20;
        List<Attribute> attributes = productService.findAttributesPages(page, maxInPage);
        for (int i = 0; i < Math.ceil((float)productService.findAttributesCount() / maxInPage); i++) {
            pages.add(i);
        }
        return new ModelAndView("admin/addAttribute",
                Map.of("attribute", attribute,
                        "role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories(),
                        "attributesList", attributes,
                        "productList", productService.findAllProducts(),
                        "attributeGroupsList", adminService.findAllAtrubutesGroups(),
                        "pages", pages),
                HttpStatus.OK);
    }

    @PostMapping("/addAttribute")
    public String addAttribute(@ModelAttribute Attribute attribute,
                               @ModelAttribute("attribute_group") String attribute_group) {
        adminService.addNewAttribute(attribute, attribute_group);
        return "redirect:/admin/addAttribute";
    }

    @GetMapping("/addAttributeGroup")
    public ModelAndView addAttributeGroup(Principal principal) {
        headerService.setHeader(principal);
        Attribute_Group attributeGroup = new Attribute_Group();
        return new ModelAndView("admin/addAttributeGroup",
                Map.of( "role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories(),
                        "productList", productService.findAllProducts(),
                        "attributeGroup", attributeGroup),
                HttpStatus.OK);
    }

    @PostMapping("/addAttributeGroup")
    public String addAttributeGroup(@ModelAttribute("attribute_group") String attributeGroup) {
        adminService.addNewAttributeGroup(attributeGroup);
        return "redirect:/admin/addAttributeGroup";
    }

    @GetMapping("/addAttributeToProduct")
    public ModelAndView addAttributeToProduct(Principal principal) {
        headerService.setHeader(principal);
        return new ModelAndView("admin/addAttributeToProduct",
                Map.of( "productAttrValue", new ProductAttrValue(),
                        "role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories(),
                        "attributesList", productService.findAttributes(),
                        "productList", productService.findAllProducts()),
                HttpStatus.OK);
    }

    @PostMapping("/addAttributeToProduct")
    public String addAttributeToProduct(@ModelAttribute ProductAttrValue productAttrValue,
                                        @ModelAttribute("productN") String product,
                                        @ModelAttribute("attributeN") String attribute) {
        if(adminService.addNewAttributeToProduct(product, attribute, productAttrValue)){
            return "redirect:/admin/";
        }
        else {
            return "redirect:/admin/addAttributeToProduct";
        }
    }

    @GetMapping("/charts")
    public ModelAndView statisticCharts(Principal principal) {
        headerService.setHeader(principal);
        List<List<Map<Object,Object>>> canvasjsIncomeDataList = erpService.getIncomeChartData();
        return new ModelAndView("admin/charts",
                Map.of("role", headerService.getHeaderRole(),
                        "categories", headerService.getHeaderCategories(),
                        "incomeDataList",canvasjsIncomeDataList,
                        "incomeList", erpService.getIncomeList(),
                        "saleCount", erpService.getSalesCount()),
                HttpStatus.OK);
    }

//    @PostMapping("/add100k")
//    public String add100k() {
//        adminService.add100k();
//        return "redirect:/admin";
//    }
}
