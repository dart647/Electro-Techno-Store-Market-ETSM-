package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.Category;
import com.etsm.ETSM.Models.Role;
import com.etsm.ETSM.Models.User;
import com.etsm.ETSM.Repositories.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import static com.etsm.ETSM.Models.Role.GUEST;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class HeaderServiceImplTest {

    @Test
    public void setHeaderTest(){
        HeaderServiceImpl headerService = new HeaderServiceImpl();
        UserService userServiceMock = mock(UserService.class);
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        headerService.setUserService(userServiceMock);
        headerService.setCategoryRepository(categoryRepositoryMock);
        ProductService productServiceMock = mock(ProductService.class);
        headerService.setProductService(productServiceMock);


        User user = new User();
        user.setRoles(Collections.singleton(Role.GUEST));
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "null";
            }
        };

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);

        Mockito.when(categoryRepositoryMock.findAll()).thenReturn(categoryList);
        Mockito.when(productServiceMock.findCategories()).thenReturn(categoryList);
        headerService.setHeader(principal);

    }

    @Test(expected=NullPointerException.class)
    public void getHeaderRoleTest(){

        HeaderServiceImpl headerService = new HeaderServiceImpl();
        UserService userServiceMock=mock(UserService.class);
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        headerService.setUserService(userServiceMock);
        headerService.setCategoryRepository(categoryRepositoryMock);

       User user = new User();
       user.setId(1L);
       user.setRoles(Collections.singleton(GUEST));

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);

        Header header = new Header(categoryList,user);

      String str =  header.getUser().getRoles().toArray()[0].toString();

    Object test = header.getUser().getRoles().toArray()[0];
//     Mockito.when(header.getUser().getRoles().toArray()[0]).thenReturn(test);



        Assert.assertEquals(headerService.getHeaderRole(),str);

    }

    @Test(expected=NullPointerException.class)
    public void getHeaderCategoriesTest(){

        HeaderServiceImpl headerService = new HeaderServiceImpl();
        UserService userServiceMock=mock(UserService.class);
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        headerService.setUserService(userServiceMock);
        headerService.setCategoryRepository(categoryRepositoryMock);

        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(GUEST));

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);

        Header header = new Header(categoryList,user);

        String str =  header.getUser().getRoles().toArray()[0].toString();

        Object test = header.getUser().getRoles().toArray()[0];
        //Mockito.when(header.getUser().getRoles().toArray()[0]).thenReturn(test);



        Assert.assertEquals(headerService.getHeaderCategories(),categoryList);

    }

   /* @Test(expected=NullPointerException.class)
    public void getUserTest(){

        HeaderServiceImpl headerService = new HeaderServiceImpl();
        UserService userServiceMock=mock(UserService.class);
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        headerService.setUserService(userServiceMock);
        headerService.setCategoryRepository(categoryRepositoryMock);

        User user = new User();
        user.setId(1L);
        user.setRoles(Collections.singleton(GUEST));

        Category category = new Category();
        category.setId(1L);
        List<Category> categoryList = List.of(category);

        Header header = new Header(categoryList,user);

        Assert.assertEquals(headerService.getUser(),user);

    }

    */

}