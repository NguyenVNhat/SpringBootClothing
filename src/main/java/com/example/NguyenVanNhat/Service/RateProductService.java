package com.example.NguyenVanNhat.Service;

import com.example.NguyenVanNhat.Models.Product;
import com.example.NguyenVanNhat.Models.RateProduct;
import com.example.NguyenVanNhat.Models.ReviewProduct;
import com.example.NguyenVanNhat.Repository.RateRespone;
import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Repository.ReviewRespone;
import com.example.NguyenVanNhat.Request.RateRequest;
import com.example.NguyenVanNhat.Request.ReviewRequest;
import com.example.NguyenVanNhat.Response.ProductRepository;
import com.example.NguyenVanNhat.Response.RateProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RateProductService {
    @Autowired
    private RateProductRepository rateProductRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ResponeObject> getbyproductid(int productid) {
        if(rateProductRepository.findByproduct_id(productid).isEmpty()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"This product does not exist","")
            );
        }
        else{
            RateRespone rateRespone = new RateRespone();
            rateRespone.setProductid(productid);

            for (RateProduct rateProduct : rateProductRepository.findByproduct_id(productid)){
                rateRespone.getListRate().add(rateProduct.getRate());
            }
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Get list rate successful",rateRespone)
            );
        }
    }
    public ResponseEntity<ResponeObject> insert(RateRequest request) {
        Product product = productRepository.findByid(request.getProductid());

        if (product == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false, "This product does not exist", "")
            );
        } else {
            RateProduct reviewProduct = new RateProduct(request.getRate(), product);
            rateProductRepository.save(reviewProduct);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true, "Insert rate success", request)
            );
        }
    }


}
