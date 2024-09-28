package com.example.NguyenVanNhat.Request;

import com.example.NguyenVanNhat.Models.Color;
import com.example.NguyenVanNhat.Models.Size;
import com.example.NguyenVanNhat.Models.Style;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private int price;
    private int discountprice;
    private Date expired;
    private int view;
    private ArrayList<Integer> categoryid ;

    private ArrayList<Integer> styleid ;
    private ArrayList<Integer> sizeid ;
    private ArrayList<Integer> colorid;
}
