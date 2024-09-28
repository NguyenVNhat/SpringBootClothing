package com.example.NguyenVanNhat.Response;

import com.example.NguyenVanNhat.Models.Style;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StyleRepository extends JpaRepository<Style, Integer> {
    Style findBystyle(String style);
    Style findByid(int id);
}
