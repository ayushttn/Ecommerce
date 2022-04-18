package com.ecommerce.Service;

import com.ecommerce.Entities.Category;
import com.ecommerce.Entities.CategoryMetaData;
import com.ecommerce.Entities.CategoryMetadataFieldValue;
import com.ecommerce.Repos.CategoryMetaDataFieldValueRepository;
import com.ecommerce.Repos.CategoryMetadataRepository;
import com.ecommerce.Repos.CategoryRepository;
import com.ecommerce.dto.CategoryMetaDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMetadataRepository categoryMetadataRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMetaDataFieldValueRepository categoryMetaDataFieldValueRepository;

    public Boolean fieldExist(String name){
        return categoryMetadataRepository.findByName(name) !=null ? true : false;
    }

    public Boolean categoryExist(String name){
        return categoryRepository.findByName(name) !=null ? true : false;
    }

    public Boolean categoryFieldValueExist(String value){
        return categoryMetaDataFieldValueRepository.findCategoryMetadataFieldValueByValue(value) !=null ? true : false;
    }


    public void addCategoryMeta (CategoryMetaDataDto categoryMetaDataDto){
        CategoryMetaData categoryMetaData = new CategoryMetaData();
        categoryMetaData.setName(categoryMetaDataDto.getName());
        categoryMetadataRepository.save(categoryMetaData);
    }

    public List<CategoryMetaData> getAll(){
        List<CategoryMetaData> list = categoryMetadataRepository.findAll();
        return list;
    }

    public void addCategory(Category category, Long id){
        Category category1 = new Category();
        category1.setName(category.getName());
        category1.setCategory(categoryRepository.findCategoryById(id));
        categoryRepository.save(category1);
    }

    public Category getCategory(Long id){
        return categoryRepository.findCategoryById(id);
    }

    public List<Category> getAllCategories(){
        List<Category> list = categoryRepository.findAll();
        return list;
    }

    public void updateCategory(Category category, Long id){
        Category category1 = categoryRepository.findCategoryById(id);
        category1.setName(category.getName());
        category1.setCategory(category.getCategory());
        categoryRepository.save(category1);
    }

    public void addCategoryMetaDataToCategory(Long categoryId, Long categoryMetaDataId, CategoryMetadataFieldValue categoryMetadataFieldValue){
        CategoryMetadataFieldValue categoryMetadataFieldValue1 = new CategoryMetadataFieldValue();
        categoryMetadataFieldValue1.setCategory(categoryRepository.findCategoryById(categoryId));
        categoryMetadataFieldValue1.setCategoryMetaData(categoryMetadataRepository.findCategoryMetaDataById(categoryMetaDataId));
        categoryMetadataFieldValue1.setValue(categoryMetadataFieldValue.getValue());
        categoryMetaDataFieldValueRepository.save(categoryMetadataFieldValue1);
    }
    public void updateCategoryMetaDataToCategory(Long categoryId, Long categoryMetaDataId, CategoryMetadataFieldValue categoryMetadataFieldValue){
        CategoryMetadataFieldValue categoryMetadataFieldValue1 = categoryMetaDataFieldValueRepository.findCategoryMetadataFieldValueById(categoryId);
        categoryMetadataFieldValue1.setCategory(categoryRepository.findCategoryById(categoryId));
        categoryMetadataFieldValue1.setCategoryMetaData(categoryMetadataRepository.findCategoryMetaDataById(categoryMetaDataId));
        categoryMetadataFieldValue1.setValue(categoryMetadataFieldValue.getValue());
        categoryMetaDataFieldValueRepository.save(categoryMetadataFieldValue1);
    }

    //Seller
    public List<CategoryMetadataFieldValue> findAllCategoryOfSeller(){
        return categoryMetaDataFieldValueRepository.findAll();
    }

    //Customer
    public List<Category> getChildCategories(Long id){
        List<Category> list = categoryRepository.findCategoriesByCategoryId(id);
        return list;
    }

}