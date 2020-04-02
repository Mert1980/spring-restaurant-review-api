package com.awbd.restaurantreview.repositories;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.awbd.restaurantreview.domain.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, UUID> {

}
