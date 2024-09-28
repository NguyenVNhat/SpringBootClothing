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
public class SizeResponse {
    public SizeApi size;
    public List<ProductRespone> products = new ArrayList<>();

    public void setSize(int id, String size){
        this.size = new SizeApi(id,size);
    }

}
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class SizeApi{
    public int id;
    public String size;
}
