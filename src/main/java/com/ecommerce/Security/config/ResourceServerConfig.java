package com.ecommerce.Security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID = "ecommerceapp";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/register/seller", "/register/customer","/resend-activationlink","/forgot-password", "/logout").permitAll()
                .mvcMatchers(HttpMethod.PUT, "/confirm-account","/reset-password").permitAll()
                .mvcMatchers(HttpMethod.GET,"/allCustomers","/allSellers").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/activateCustomer/{id}", "/deactivateCustomer/{id}", "/activateSeller/{id}", "/deactivateSeller/{id}","/activateProduct/{id}", "/deactivateProduct/{id}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/updateProfile", "/updateMyPassword", "/updateAddress/{id}").hasRole("CUSTOMER")
                .mvcMatchers(HttpMethod.POST,"/addAddress").hasRole("CUSTOMER")
                .mvcMatchers(HttpMethod.GET,"/viewMyProfile","/viewMyAddresses","/getCategoriesWithChildByCategoryId/{id}").hasRole("CUSTOMER")
                .mvcMatchers(HttpMethod.DELETE, "/deleteAddress/{id}").hasRole("CUSTOMER")
                .mvcMatchers(HttpMethod.GET,"/viewProfile", "/getProduct/{id}","/getAllProducts", "/seller/findAllCategory").hasRole("SELLER")
                .mvcMatchers(HttpMethod.PUT,"/updateSellerProfile","/updateSellerPassword","/updateSellerAddress/{id}","/updateProduct/{id}").hasRole("SELLER")
                .mvcMatchers(HttpMethod.POST, "/addProduct/{categoryId}").hasRole("SELLER")
                .mvcMatchers(HttpMethod.DELETE, "/deleteProduct/{id}").hasRole("SELLER")
                .mvcMatchers(HttpMethod.POST, "/addMetadataField", "/addCategory/{id}", "/addCategory","/mapCategoryToCategoryMetaDataFieldValue/{categoryid}/{categorymetadatafieldid}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/getAllMetadataField", "/getCategory/{id}", "/getAllCategory").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/updateMapCategoryToCategoryMetaDataFieldValue/{categoryid}/{categorymetadatafieldid}", "/updateCategory/{id}").hasRole("ADMIN")
                .anyRequest().denyAll().and().csrf().disable();
    }
}
