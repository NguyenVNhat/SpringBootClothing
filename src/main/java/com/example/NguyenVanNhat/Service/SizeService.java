package com.example.NguyenVanNhat.Service;

import com.example.NguyenVanNhat.Models.Color;
import com.example.NguyenVanNhat.Models.Product;
import com.example.NguyenVanNhat.Models.Size;
import com.example.NguyenVanNhat.Models.Style;
import com.example.NguyenVanNhat.Repository.ProductRespone;
import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Repository.SizeResponse;
import com.example.NguyenVanNhat.Request.ColorRequest;
import com.example.NguyenVanNhat.Request.SizeRequest;
import com.example.NguyenVanNhat.Request.SizeUpdateRequest;
import com.example.NguyenVanNhat.Response.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SizeService {
    @Autowired
    private SizeRepository sizeRepository;
    public ResponseEntity<ResponeObject> getall(){
        if( sizeRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This size does not exist","") );
        }
        else{
            ArrayList<String> sizes = new ArrayList<>();
            for (Size size : sizeRepository.findAll()) {
                sizes.add(size.getSize());
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Get list size ",sizes )
            );
        }
    }
    public ResponseEntity<ResponeObject> get(int id){
        if( sizeRepository.findByid(id) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This size does not exist ","") );
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Get list size ",sizeRepository.findByid(id).getSize() )
            );
        }
    }


    public ResponseEntity<ResponeObject> insert(SizeRequest request){
        Size size = sizeRepository.findBysize(request.getSize());

        if(size == null){

            sizeRepository.save(new Size(request.getSize()));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Insert size successfully ",request.getSize())
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This size already exists ","")
            );
        }
    }
    public ResponseEntity<ResponeObject> update(SizeUpdateRequest request){
        Size size = sizeRepository.findByid(request.getId());

        if(size != null){
            size.setSize(request.getNewValue());
            sizeRepository.save(size);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Update size successfully ",request.getNewValue())
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This size does not exist ","")
            );
        }
    }
    public ResponseEntity<ResponeObject> delete(int id){
        Size size = sizeRepository.findByid(id);

        if(size != null){
            List<Product> products = size.getProducts();

            for (Product product : products){
                product.getColors().remove(size);
            }
            sizeRepository.delete(size);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Delete size successfully ","")
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This size does not exist ","")
            );
        }
    }
}
