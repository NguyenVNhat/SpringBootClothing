package com.example.NguyenVanNhat.Controller;

import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Request.SizeRequest;
import com.example.NguyenVanNhat.Request.SizeUpdateRequest;
import com.example.NguyenVanNhat.Service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/size")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @GetMapping("/getall")
    public ResponseEntity<ResponeObject> getall(){
        return sizeService.getall();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponeObject> get(@PathVariable int id){
        return sizeService.get(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponeObject> insert(@RequestBody SizeRequest request){
        return sizeService.insert(request);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponeObject> update(@RequestBody SizeUpdateRequest request){
        return sizeService.update(request);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponeObject> delete(@PathVariable int id){
        return sizeService.delete(id);
    }

}
