package com.laksh.coffee_photo.web;

import com.laksh.coffee_photo.model.Photo;
import com.laksh.coffee_photo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {

    @Autowired
    PhotoService photoService;

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) {

        Photo photo = photoService.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        byte[] data = photo.getData();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.valueOf(photo.getContentType()));

        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(photo.getFileName()).build());

        return new ResponseEntity<>(data, headers, HttpStatus.OK);

    }
}
