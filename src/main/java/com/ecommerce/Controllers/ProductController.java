package com.ecommerce.Controllers;

import com.ecommerce.Entities.Product;
import com.ecommerce.Handler.ResponseHandler;
import com.ecommerce.Service.CategoryService;
import com.ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/addProduct/{categoryId}")
    public ResponseEntity<Object> addProduct(@PathVariable("categoryId") Long id, @RequestBody Product product, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        if(!productService.productExist(product.getName()) && productService.categoryExist(id)){
            productService.addProduct(product, email, id);
            return ResponseHandler.generateResponse("Product Added Successfully", HttpStatus.OK);
        }
        else {
            return ResponseHandler.generateResponse("Duplicate Product or Category is not valid", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getProduct/{id}")
    public List<Object []> getProduct(@PathVariable Long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.getProductByCreator(id, email);
    }

    @GetMapping("/getAllProducts")
    public List<Object []> getProducts(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return productService.getAllProducts(email);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Object> deleteByid(@PathVariable Long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        productService.deleteProduct(id, email);
        return ResponseHandler.generateResponse("Product Deleted", HttpStatus.OK);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody Product product, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        if(!productService.productExist(product.getName())){
            productService.updateProduct(id, email, product);
            return ResponseHandler.generateResponse("Product Updated Successfully", HttpStatus.OK);
        }
        else {
            return ResponseHandler.generateResponse("Duplicate Product", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/activateProduct/{id}")
    public void activateProduct(@PathVariable Long id){
        productService.activateProduct(id);
    }

    @PutMapping("/deactivateProduct/{id}")
    public void deactivateProduct(@PathVariable Long id){
        productService.deactivateProduct(id);
    }
}
