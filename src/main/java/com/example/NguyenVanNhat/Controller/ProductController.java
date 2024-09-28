package com.example.NguyenVanNhat.Controller;

import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Request.ColorRequest;
import com.example.NguyenVanNhat.Request.ProducUpdateRequest;
import com.example.NguyenVanNhat.Request.ProductRequest;
import com.example.NguyenVanNhat.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    // get all product
    @GetMapping("/getall")
    public ResponseEntity<ResponeObject> getall(){
        return productService.getall();
    }

    // get product by id
    @GetMapping("/{id}")
    public ResponseEntity<ResponeObject> get(@PathVariable int id){
        return productService.get(id);
    }

    //get product by category id
    @GetMapping("/category/{id}")
    public ResponseEntity<ResponeObject> getbycategory(@PathVariable int id){
        return productService.getbycategoryid(id);
    }

    // get product by color id
    @GetMapping("/color/{id}")
    public ResponseEntity<ResponeObject> getbycolor(@PathVariable int id){
        return productService.getbycolorid(id);
    }

    // get product by style id
    @GetMapping("/style/{id}")
    public ResponseEntity<ResponeObject> getbystyle(@PathVariable int id){
        return productService.getbystyleid(id);
    }

    // get product by size id
    @GetMapping("/size/{id}")
    public ResponseEntity<ResponeObject> getbysize(@PathVariable int id){
        return productService.getbysizeid(id);
    }

    // get product by price
    @GetMapping("/fillter/{low}/{high}")
    public ResponseEntity<ResponeObject> getbycolor(@PathVariable("low") int lowprice,@PathVariable("high") int highprice ){
        return productService.fillterprice(lowprice,highprice);
    }

    // sort product by price
    @GetMapping("/price/sort/desc")
    public ResponseEntity<ResponeObject> sortbypricedesc(){
        return productService.sortbyPriceDesc();
    }

    // sort product by price
    @GetMapping("/price/sort/asc")
    public ResponseEntity<ResponeObject> sortbypriceasc(){
        return productService.sortbyPriceAsc();
    }

    // insert product
    @PostMapping("/insert")
    public ResponseEntity<ResponeObject> insert(@RequestBody ProductRequest productRequest){
        return productService.insert(productRequest);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponeObject> update(@RequestBody ProducUpdateRequest request){
        return productService.update(request);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponeObject> delete(@PathVariable int id){
        return productService.delete(id);
    }
}
