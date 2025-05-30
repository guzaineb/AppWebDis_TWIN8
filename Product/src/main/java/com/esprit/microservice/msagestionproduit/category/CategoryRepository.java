package com.esprit.microservice.msagestionproduit.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findById(long id);
    Category findByCodeApi(String codeApi);

}
