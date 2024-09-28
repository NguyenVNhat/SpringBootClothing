package com.example.NguyenVanNhat.Response;

import com.example.NguyenVanNhat.Models.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Integer> {
    Size findBysize(String size);
    Size findByid(int id);


}
