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
public class ColorResponse {
    public ColorApi color;
    public List<ProductRespone> products = new ArrayList<>();

    public void setColor(int id, String color){
        this.color = new ColorApi(id,color);
    }

}
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class ColorApi{
    public int id;
    public String color;
}
