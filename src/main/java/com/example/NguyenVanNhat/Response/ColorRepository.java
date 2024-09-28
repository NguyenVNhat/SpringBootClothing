package com.example.NguyenVanNhat.Response;

import com.example.NguyenVanNhat.Models.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    Color findBycolor(String color);
    Color findByid(int id);
}
