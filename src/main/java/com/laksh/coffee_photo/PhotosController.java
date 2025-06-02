package com.laksh.coffee_photo;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Photo create(@RequestBody Photo photo){ // jackson creates the java object with the empty constructor, sets file name with the setFileName setters
        photo.setId(UUID.randomUUID().toString()); // id should be generated in the backend
        db.put(photo.getId(), photo);
        return photo;
    }

}
