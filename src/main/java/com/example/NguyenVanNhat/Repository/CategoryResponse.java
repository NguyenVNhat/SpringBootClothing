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
public class CategoryResponse {
    public CategoryApi categories;
    public List<ProductRespone> products = new ArrayList<>();

    public void setCategories(int id, String color){
        this.categories = new CategoryApi(id,color);
    }

}
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class CategoryApi{
    public int id;
    public String color;
}
