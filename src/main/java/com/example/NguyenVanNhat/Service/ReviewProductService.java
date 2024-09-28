package com.example.NguyenVanNhat.Service;

import com.example.NguyenVanNhat.Models.Product;
import com.example.NguyenVanNhat.Models.ReviewProduct;
import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Repository.ReviewRespone;
import com.example.NguyenVanNhat.Request.ReviewRequest;
import com.example.NguyenVanNhat.Request.ReviewUpdateRequest;
import com.example.NguyenVanNhat.Response.ProductRepository;
import com.example.NguyenVanNhat.Response.ReviewProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewProductService {
    @Autowired
    private ReviewProductRepository reviewProductRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ResponeObject> getbyproductid(int productid) {
        if(reviewProductRepository.findByproduct_id(productid).isEmpty()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"Get list review failed","")
            );
        }
        else{
            ReviewRespone reviewRespone = new ReviewRespone();
            reviewRespone.setProductid(productid);

            for (ReviewProduct reviewProduct : reviewProductRepository.findByproduct_id(productid)){
                reviewRespone.getListcontent().add(reviewProduct.getContent());
            }
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Get list review successful",reviewRespone)
            );
        }
    }
    public ResponseEntity<ResponeObject> insert(ReviewRequest request) {
        Product product = productRepository.findByid(request.getProductid());

        if (product == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false, "Insert review failed: Product not found", "")
            );
        } else {
            ReviewProduct reviewProduct = new ReviewProduct(request.getContent(), product);
            reviewProductRepository.save(reviewProduct);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true, "Insert review success", request)
            );
        }
    }
    public ResponseEntity<ResponeObject> update(ReviewUpdateRequest request) {
        ReviewProduct reviewProduct = reviewProductRepository.findByid(request.getId());

        if (reviewProduct == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false, "This review does not exist", "")
            );
        } else {
            reviewProduct.setContent(request.getNewValue());
            reviewProductRepository.save(reviewProduct);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true, "Update review success", request.getNewValue())
            );
        }
    }
    public ResponseEntity<ResponeObject> delete(int id) {
        ReviewProduct reviewProduct = reviewProductRepository.findByid(id);

        if (reviewProduct == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false, "This review does not exist", "")
            );
        } else {
            reviewProductRepository.delete(reviewProduct);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true, "Update review success", "")
            );
        }
    }

}
