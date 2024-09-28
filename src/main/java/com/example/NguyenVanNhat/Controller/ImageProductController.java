package com.example.NguyenVanNhat.Controller;

import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Request.ImageRequest;
import com.example.NguyenVanNhat.Request.ImageUpdateRequest;
import com.example.NguyenVanNhat.Service.ImageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/image")
public class ImageProductController {
    @Autowired
    private ImageProductService imageProductService;

    @GetMapping("/{productid}")
    public ResponseEntity<ResponeObject> getbyproductid(@PathVariable int productid) {
        return imageProductService.getbyproductid(productid);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponeObject> get(@PathVariable int id) {
        return imageProductService.get(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponeObject> insert(@RequestBody ImageRequest request) {
        return imageProductService.insert(request);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponeObject> update(@RequestBody ImageUpdateRequest request) {
        return imageProductService.update(request);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponeObject> delete(@PathVariable int id) {
        return imageProductService.delete(id);
    }
}
