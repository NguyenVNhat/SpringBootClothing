package com.example.NguyenVanNhat.Service;

import com.example.NguyenVanNhat.Models.*;
import com.example.NguyenVanNhat.Repository.CategoryResponse;
import com.example.NguyenVanNhat.Repository.ProductRespone;
import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Repository.StyleResponse;
import com.example.NguyenVanNhat.Request.CategoryRequest;
import com.example.NguyenVanNhat.Request.ColorRequest;
import com.example.NguyenVanNhat.Response.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    public ResponseEntity<ResponeObject> getall(){
        if( categoriesRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"List category are empty","") );
        }
        else{
            ArrayList<String> categories = new ArrayList<>();
            for (Categories categorie : categoriesRepository.findAll()) {
                categories.add(categorie.getName());
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"List category : ",categories )
            );
        }
    }
    public ResponseEntity<ResponeObject> get(int id){
        if( categoriesRepository.findByid(id) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This category does not exist","") );
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Category with id  : "+ id,categoriesRepository.findByid(id).getName())
            );
        }
    }

    public ResponseEntity<ResponeObject> insert(CategoryRequest categoryRequest){
        Categories categories = categoriesRepository.findByname(categoryRequest.getCategory());

        if(categories == null){
            categoriesRepository.save(new Categories(categoryRequest.getCategory()));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Insert category successfully ",categoryRequest.getCategory())
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This color existed","")
            );
        }
    }
    public ResponseEntity<ResponeObject> update(int id , String newValue){
        Categories categories = categoriesRepository.findByid(id);
        if(categories != null){
            categories.setName(newValue);
            categoriesRepository.save(categories);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Update category successfully ",categories.getName())
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This category does not exist","")
            );
        }
    }
    public ResponseEntity<ResponeObject> delete(int id){
        Categories categories = categoriesRepository.findByid(id);
        if(categories != null){
            List<Product> products = categories.getProducts();
            for (Product product : products){
                product.getCategories().remove(categories);
            }

            categoriesRepository.delete(categories);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Delete category successfully ","")
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This category does not exist","")
            );
        }
    }
}
