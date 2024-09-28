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
public class StyleResponse {
    public StyleApi style;
    public List<ProductRespone> products = new ArrayList<>();

    public void setStyle(int id, String style){
        this.style = new StyleApi(id,style);
    }

}
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class StyleApi{
    public int id;
    public String style;
}
