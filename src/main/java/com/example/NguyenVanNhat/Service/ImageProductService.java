package com.example.NguyenVanNhat.Service;

import com.example.NguyenVanNhat.Models.Color;
import com.example.NguyenVanNhat.Models.ImageProduct;
import com.example.NguyenVanNhat.Models.Product;
import com.example.NguyenVanNhat.Models.ReviewProduct;
import com.example.NguyenVanNhat.Repository.ImageRespone;
import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Repository.ReviewRespone;
import com.example.NguyenVanNhat.Request.ImageRequest;
import com.example.NguyenVanNhat.Request.ImageUpdateRequest;
import com.example.NguyenVanNhat.Request.ReviewRequest;
import com.example.NguyenVanNhat.Response.ImageProductRepository;
import com.example.NguyenVanNhat.Response.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ImageProductService {
    @Autowired
    private ImageProductRepository imageProductRepository;

    @Autowired
    private ProductRepository productRepository;


    public String convertToBase64(String imagePath) {
        File file = new File(imagePath);
        if (!file.exists() || !file.isFile()) {
            System.err.println("Tệp không tồn tại hoặc không phải là tệp hợp lệ: " + imagePath);
            return null;
        }

        try (FileInputStream imageInFile = new FileInputStream(file)) {
            byte[] imageData = new byte[(int) file.length()];
            int bytesRead = imageInFile.read(imageData);
            if (bytesRead != imageData.length) {
                System.err.println("Không thể đọc toàn bộ dữ liệu tệp.");
                return null;
            }
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            return base64Image;

        } catch (IOException e) {
            System.err.println("Lỗi khi đọc tệp: " + e.getMessage());
            return null;
        }
    }


    public ResponseEntity<ResponeObject> getbyproductid(int productid) {
        if(imageProductRepository.findByproduct_id(productid).isEmpty()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This product don't have any image","")
            );
        }
        else{
            ImageRespone imageRespone = new ImageRespone();
            imageRespone.setProductid(productid);

            for (ImageProduct imageProduct : imageProductRepository.findByproduct_id(productid)){
                imageRespone.getListimage().add(imageProduct.getImage());
            }
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"List image :",imageRespone)
            );
        }
    }
    public ResponseEntity<ResponeObject> get(int id ) {
        ImageProduct imageProduct = imageProductRepository.findByid(id);

        if (imageProduct == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false, "This image does not exist", "")
            );
        } else {

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true, " Image with id : "+id, imageProduct.getImage())
            );
        }
    }

    public ResponseEntity<ResponeObject> insert(ImageRequest request) {
        Product product = productRepository.findByid(request.getProductid());

        if (product == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false, "This product does not exist", "")
            );
        } else {
            String image = convertToBase64(request.getFilePath());
            ImageProduct imageProduct = new ImageProduct(image, product);
            imageProductRepository.save(imageProduct);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true, "Insert image success", request)
            );
        }
    }
    public ResponseEntity<ResponeObject> update(ImageUpdateRequest request ) {
        ImageProduct imageProduct = imageProductRepository.findByid(request.getId());

        if (imageProduct == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false, "This image does not exist", "")
            );
        } else {

            String image = convertToBase64(request.getFilePath());
            imageProduct.setImage(image);
            imageProductRepository.save(imageProduct);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true, "Update image success", "")
            );
        }
    }
    public ResponseEntity<ResponeObject> delete(int id) {
        ImageProduct imageProduct = imageProductRepository.findByid(id);
        if (imageProduct == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false, "This image does not exist", "")
            );
        } else {
            imageProductRepository.delete(imageProduct);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true, "Delete image success", "")
            );
        }
    }
}
