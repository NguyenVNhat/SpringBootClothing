package com.example.NguyenVanNhat.Service;

import com.example.NguyenVanNhat.Models.Color;
import com.example.NguyenVanNhat.Models.Product;
import com.example.NguyenVanNhat.Models.Size;
import com.example.NguyenVanNhat.Models.Style;
import com.example.NguyenVanNhat.Repository.ColorResponse;
import com.example.NguyenVanNhat.Repository.ProductRespone;
import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Request.ColorRequest;
import com.example.NguyenVanNhat.Response.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;
    public ResponseEntity<ResponeObject> getall(){
        if( colorRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"List color are empty","") );
        }
        else{
            ArrayList<String> colors = new ArrayList<>();
            for (Color color : colorRepository.findAll()) {
                colors.add(color.getColor());
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"List color : ",colors )
            );
        }
    }
    public ResponseEntity<ResponeObject> get(int id){
        if( colorRepository.findByid(id) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This color does not exist","") );
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"List color : ",colorRepository.findByid(id).getColor() )
            );
        }
    }

    public ResponseEntity<ResponeObject> insert(ColorRequest colorRequest){
        Color color = colorRepository.findBycolor(colorRequest.getColor());
        if(color == null){
            colorRepository.save(new Color(colorRequest.getColor()));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Insert color successfully ",color)
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This color existed","")
            );
        }
    }

    public ResponseEntity<ResponeObject> update(int id , String newValue){
        Color color = colorRepository.findByid(id);
        if(color != null){
            color.setColor(newValue);
            colorRepository.save(color);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Update color successfully ",newValue)
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This color does not exist","")
            );
        }
    }

    public ResponseEntity<ResponeObject> delete(int id ){
        Color color = colorRepository.findById(id).orElse(null);

        if(color != null){
            List<Product> products = color.getProducts();

            for (Product product : products){
                product.getColors().remove(color);
            }
            colorRepository.delete(color);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Delete color successfully ",color)
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This color does not exist","")
            );
        }
    }
}
