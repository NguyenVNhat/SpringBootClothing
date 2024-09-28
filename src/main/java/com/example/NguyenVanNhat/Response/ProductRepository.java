package com.example.NguyenVanNhat.Response;

import com.example.NguyenVanNhat.Models.Color;
import com.example.NguyenVanNhat.Models.Product;
import com.example.NguyenVanNhat.Models.Size;
import com.example.NguyenVanNhat.Models.Style;
import com.example.NguyenVanNhat.Request.ProductRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);
    Product findByid(int id);
    List<Product> findBycategories_id(int categories_id);
    List<Product> findBycolors(Color colors);
    List<Product> findBysizes(Size sizes);
    List<Product> findBystyles(Style styles);

    @Query("SELECT p FROM product p WHERE p.price >= :lowprice AND p.price <= :highprice")
    List<Product> fillterByPrice(@Param("lowprice") int lowprice, @Param("highprice") int highprice);

    @Query("SELECT p FROM product p ORDER BY p.price DESC ")
    List<Product> sortByPriceDesc();

    @Query("SELECT p FROM product p ORDER BY p.price ASC ")
    List<Product> sortByPriceAsc();

    @Query("SELECT AVG(rp.rate) FROM rate_product rp WHERE rp.product.id = :productid")
    double getAverageRating(@Param("productid") int productid);


}
