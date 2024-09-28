package com.example.NguyenVanNhat.Controller;

import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Request.ReviewRequest;
import com.example.NguyenVanNhat.Request.ReviewUpdateRequest;
import com.example.NguyenVanNhat.Service.ReviewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/review")
public class ReviewProductController {
    @Autowired
    private ReviewProductService reviewProductService;
    @GetMapping("/getbyproductid/{id}")
    private ResponseEntity<ResponeObject> getbyproductid(@PathVariable int id) {
        return reviewProductService.getbyproductid(id);
    }
    @PostMapping("/insert")
    private ResponseEntity<ResponeObject> insert(@RequestBody ReviewRequest request) {
        return reviewProductService.insert(request);
    }
    @PutMapping("/update")
    private ResponseEntity<ResponeObject> update(@RequestBody ReviewUpdateRequest request) {
        return reviewProductService.update(request);
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ResponeObject> delete(@PathVariable int id) {
        return reviewProductService.delete(id);
    }
}
