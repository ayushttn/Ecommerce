package com.ecommerce.Repos;

import com.ecommerce.Entities.Product;
import com.ecommerce.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    @Query("select p.id, p.name, p.description, c.id from Product p, Seller s join Category c on c = p.category where p.id=:id and p.seller=:s and p.isDeleted=false ")
    List<Object []> findProductByIdAndSellerAndDeletedFalseManual(@Param("id") Long id, @Param("s") Seller s);

    @Query("select p.id, p.name, p.description from Product p where p.seller=:s")
    List<Object []> findAllBySeller(@Param("s") Seller s);

    Product findProductByIdAndSeller(Long id, Seller s);
}
