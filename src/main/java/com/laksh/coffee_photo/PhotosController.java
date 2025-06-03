package com.laksh.coffee_photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;


@RestController
public class PhotosController {

    @Autowired PhotoService photoService;

    @GetMapping("/photos")
    public Collection<Photo> get(){

        return photoService.get();

    }

    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable String id){

        Photo photo = photoService.get(id);

        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photoService.get(id);
    }

    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable String id){

        Photo photo = photoService.remove(id);

        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/photos")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {

        Photo photo = photoService.save(file.getOriginalFilename(), file.getContentType() ,file.getBytes());

        return photo;

    }

}
