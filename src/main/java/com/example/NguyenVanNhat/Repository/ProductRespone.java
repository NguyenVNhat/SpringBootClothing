package com.example.NguyenVanNhat.Repository;

import com.example.NguyenVanNhat.Models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRespone {
    public ProductAPI product;
    public List<String> color  = new ArrayList<>();
    public List<String> style = new ArrayList<>();
    public List<String> size = new ArrayList<>();
    public List<String> categories = new ArrayList<>();
    public ArrayList<String> listReview= new ArrayList<>();
    public ArrayList<String> listImage= new ArrayList<>();

    public void setProductApi(int id,String name,String description,int price,int discountprice,Date expired,int view,double rate) {
        this.product = new ProductAPI(id,name,description,price,discountprice,expired,view,rate);
    }
    public String getProductName(){
        return this.product.getName();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class ProductAPI{
    public int id;
    public String name;
    public String description;
    public int price;
    public int discountprice;
    public Date expired;
    public int view;
    public double rate;

}
