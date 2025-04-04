package com.cd.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cd.entity.ProductCategory;


@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(path="product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
