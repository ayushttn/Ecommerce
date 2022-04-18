package com.ecommerce.Controllers;

import com.ecommerce.Entities.Category;
import com.ecommerce.Entities.CategoryMetaData;
import com.ecommerce.Entities.CategoryMetadataFieldValue;
import com.ecommerce.Handler.ResponseHandler;
import com.ecommerce.Service.CategoryService;
import com.ecommerce.dto.CategoryMetaDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addMetadataField")
    public ResponseEntity<Object> addMetadataField(@RequestBody CategoryMetaDataDto categoryMetaData){
        if(!categoryService.fieldExist(categoryMetaData.getName())){
            categoryService.addCategoryMeta(categoryMetaData);
            return ResponseHandler.generateResponse("Added Successfully", HttpStatus.OK);
        }
        else {
            return ResponseHandler.generateResponse("Already Exists", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllMetadataField")
    public List<CategoryMetaData> getAllMetaData(){
        return categoryService.getAll();
    }

    @PostMapping(value = {"/addCategory/{id}", "/addCategory"})
    public ResponseEntity<Object> addCategory(@PathVariable(required = false) Long id, @RequestBody Category category){
        if(!categoryService.categoryExist(category.getName())){
            categoryService.addCategory(category, id);
            return ResponseHandler.generateResponse("Added Successfully", HttpStatus.OK);
        }
        return ResponseHandler.generateResponse("Already Exists", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getCategory/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    @GetMapping("/getAllCategory")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<Object> updateCategoryById(@PathVariable Long id, @RequestBody Category category){
        if(!categoryService.categoryExist(category.getName())){
            categoryService.updateCategory(category, id);
            return ResponseHandler.generateResponse("Update Successfully", HttpStatus.OK);
        }
        else {
            return ResponseHandler.generateResponse("Category with this name already Exists", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/mapCategoryToCategoryMetaDataFieldValue/{categoryid}/{categorymetadatafieldid}")
    public ResponseEntity<Object> addCategoryToCategoryMetaDataFieldValue(@PathVariable Long categoryid, @PathVariable Long categorymetadatafieldid,@RequestBody CategoryMetadataFieldValue categoryMetadataFieldValue){
        if (!categoryService.categoryFieldValueExist(categoryMetadataFieldValue.getValue())){
            categoryService.addCategoryMetaDataToCategory(categoryid, categorymetadatafieldid, categoryMetadataFieldValue);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK);
        }
        else {
            return ResponseHandler.generateResponse("Values already Exist", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateMapCategoryToCategoryMetaDataFieldValue/{categoryid}/{categorymetadatafieldid}")
    public ResponseEntity<Object> updateCategoryToCategoryMetaDataFieldValue(@PathVariable Long categoryid, @PathVariable Long categorymetadatafieldid,@RequestBody CategoryMetadataFieldValue categoryMetadataFieldValue){
        if (!categoryService.categoryFieldValueExist(categoryMetadataFieldValue.getValue())){
            categoryService.updateCategoryMetaDataToCategory(categoryid, categorymetadatafieldid, categoryMetadataFieldValue);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK);
        }
        else {
            return ResponseHandler.generateResponse("Values already Exist", HttpStatus.BAD_REQUEST);
        }
    }

    //Customer
    @GetMapping("/getCategoriesWithChildByCategoryId/{id}")
    public List<Category> getCategoriesWithChildbyId(@PathVariable Long id){
        return categoryService.getChildCategories(id);
    }

    //Seller
    @GetMapping("/seller/findAllCategory")
    public List<CategoryMetadataFieldValue> getAllCategoryOfSeller(){
        return categoryService.findAllCategoryOfSeller();
    }
}
