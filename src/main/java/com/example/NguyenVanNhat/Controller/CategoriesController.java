package com.example.NguyenVanNhat.Controller;

import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Request.CategoryRequest;
import com.example.NguyenVanNhat.Request.CategoryUpdateRequest;
import com.example.NguyenVanNhat.Request.ColorRequest;
import com.example.NguyenVanNhat.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/category")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("")
    public ResponseEntity<ResponeObject> getall(){
        return categoriesService.getall();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponeObject> get(@PathVariable int id){
        return categoriesService.get(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponeObject> insert(@RequestBody CategoryRequest request){
        return categoriesService.insert(request);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponeObject> update(@RequestBody CategoryUpdateRequest request){
        return categoriesService.update(request.getId(), request.getNewValue());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponeObject> delete(@PathVariable int id){
        return categoriesService.delete(id);
    }
}
