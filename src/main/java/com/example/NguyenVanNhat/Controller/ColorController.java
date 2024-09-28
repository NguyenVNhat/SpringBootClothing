package com.example.NguyenVanNhat.Controller;

import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Request.ColorRequest;
import com.example.NguyenVanNhat.Request.ColorUpdateRequest;
import com.example.NguyenVanNhat.Service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/color")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping("")
    public ResponseEntity<ResponeObject> getall(){
        return colorService.getall();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponeObject> get(@PathVariable int id){
        return colorService.get(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponeObject> insert(@RequestBody ColorRequest request){
        return colorService.insert(request);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponeObject> update(@RequestBody ColorUpdateRequest request){
        return colorService.update(request.getId(),request.getColor());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponeObject> delete(@PathVariable int id){
        return colorService.delete(id);
    }
}
