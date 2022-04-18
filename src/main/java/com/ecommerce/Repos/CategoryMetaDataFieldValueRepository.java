package com.ecommerce.Repos;

import com.ecommerce.Entities.CategoryMetadataFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryMetaDataFieldValueRepository extends JpaRepository<CategoryMetadataFieldValue, Long> {
    CategoryMetadataFieldValue findCategoryMetadataFieldValueByValue(String s);

    CategoryMetadataFieldValue findCategoryMetadataFieldValueById(Long id);
}
