package com.laksh.coffee_photo;


import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
public class PhotosController {

    private Map<String, Photo> db = new HashMap<>(){{
        put("1", new Photo("1", "something.jpg"));
    }};

    @GetMapping("/photos")
    public Collection<Photo> get(){
        return db.values();
    }

    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable String id){

        Photo photo = db.get(id);

        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return db.get(id);
    }

    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable String id){

        Photo photo = db.remove(id);

        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/photos")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {

        Photo photo = new Photo();

        photo.setId(UUID.randomUUID().toString()); // id should be generated in the backend
        photo.setFileName(file.getOriginalFilename());
        photo.setData(file.getBytes());

        db.put(photo.getId(), photo);
        return photo;
    }

}
