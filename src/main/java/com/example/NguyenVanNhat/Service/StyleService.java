package com.example.NguyenVanNhat.Service;

import com.example.NguyenVanNhat.Models.Color;
import com.example.NguyenVanNhat.Models.Product;
import com.example.NguyenVanNhat.Models.Size;
import com.example.NguyenVanNhat.Models.Style;
import com.example.NguyenVanNhat.Repository.ProductRespone;
import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Repository.SizeResponse;
import com.example.NguyenVanNhat.Repository.StyleResponse;
import com.example.NguyenVanNhat.Request.SizeRequest;
import com.example.NguyenVanNhat.Request.SizeUpdateRequest;
import com.example.NguyenVanNhat.Request.StyleRequest;
import com.example.NguyenVanNhat.Request.StyleUpdateRequest;
import com.example.NguyenVanNhat.Response.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StyleService {
    @Autowired
    private StyleRepository styleRepository;

    public ResponseEntity<ResponeObject> getall(){
        if( styleRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"Get list style failed ","") );
        }
        else{
            ArrayList<String> styles = new ArrayList<>();
            for (Style style : styleRepository.findAll()) {
                styles.add(style.getStyle());
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Get list style ",styles )
            );
        }
    }
    public ResponseEntity<ResponeObject> get(int id){
        if( styleRepository.findByid(id) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"Get list style failed ","") );
        }
        else{

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Get list style ",styleRepository.findByid(id).getStyle() )
            );
        }
    }

    public ResponseEntity<ResponeObject> insert(StyleRequest styleRequest){
        Style style = styleRepository.findBystyle(styleRequest.getStyle());

        if(style == null){
            styleRepository.save(new Style(styleRequest.getStyle()));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Insert style successfully ",styleRequest.getStyle())
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This style does not exist ","")
            );
        }
    }
    public ResponseEntity<ResponeObject> update(StyleUpdateRequest request){
        Style style = styleRepository.findByid(request.getId());

        if(style != null){
            style.setStyle(request.getNewValue());
            styleRepository.save(style);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Update style successfully ",request.getNewValue())
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This style does not exist ","")
            );
        }
    }
    public ResponseEntity<ResponeObject> delete(int id){
        Style style = styleRepository.findByid(id);

        if(style != null){
            List<Product> products = style.getProducts();

            for (Product product : products){
                product.getColors().remove(style);
            }
            styleRepository.delete(style);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Delete style successfully ","")
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This style does not exist ","")
            );
        }
    }
}
