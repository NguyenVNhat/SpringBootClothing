package com.example.NguyenVanNhat.Response;

import com.example.NguyenVanNhat.Models.ReviewProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewProductRepository extends JpaRepository<ReviewProduct, Integer> {
    List<ReviewProduct> findByproduct_id(int productid);
    ReviewProduct findByid(int id);
}
