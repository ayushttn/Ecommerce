package com.ecommerce.Repos;

import com.ecommerce.Entities.CategoryMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryMetadataRepository extends JpaRepository<CategoryMetaData, Long> {

    CategoryMetaData findByName(String s);

    CategoryMetaData findCategoryMetaDataById(Long id);
}
