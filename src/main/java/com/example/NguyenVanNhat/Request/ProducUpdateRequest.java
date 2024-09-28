package com.example.NguyenVanNhat.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProducUpdateRequest {
    private int id;
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
