package com.example.NguyenVanNhat.Controller;

import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Request.ColorRequest;
import com.example.NguyenVanNhat.Request.ColorUpdateRequest;
import com.example.NguyenVanNhat.Request.RateRequest;
import com.example.NguyenVanNhat.Service.RateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/rate")
public class RateProductController {
    @Autowired
    private RateProductService rateProductService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponeObject> get(@PathVariable int id){
        return rateProductService.getbyproductid(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponeObject> insert(@RequestBody RateRequest request){
        return rateProductService.insert(request);
    }
}
