package com.example.NguyenVanNhat.Controller;

import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Request.SizeRequest;
import com.example.NguyenVanNhat.Request.StyleRequest;
import com.example.NguyenVanNhat.Request.StyleUpdateRequest;
import com.example.NguyenVanNhat.Service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/style")
public class StyleController {
    @Autowired
    private StyleService styleService;

    @GetMapping("/getall")
    public ResponseEntity<ResponeObject> getall(){
        return styleService.getall();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponeObject> get(@PathVariable int id){
        return styleService.get(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponeObject> insert(@RequestBody StyleRequest request){
        return styleService.insert(request);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponeObject> update(@RequestBody StyleUpdateRequest request){
        return styleService.update(request);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponeObject> delete(@PathVariable int id){
        return styleService.delete(id);
    }
}
