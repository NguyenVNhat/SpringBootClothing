package com.example.NguyenVanNhat.Repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageRespone {
    public int productid;
    public List<String> listimage = new ArrayList<>();
}
