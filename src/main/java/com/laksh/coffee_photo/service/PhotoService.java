package com.laksh.coffee_photo.service;

import com.laksh.coffee_photo.model.Photo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PhotoService {

    private final Map<String, Photo> db = new HashMap<>(){{
       put("1", new Photo("1", "hello.jpg"));
    }};

    public Collection<Photo> get() {

        return db.values();

    }

    public Photo get(String id) {

        return db.get(id);

    }

    public Photo remove(String id) {

        return db.remove(id);

    }

    public Photo save(String fileName, String contentType, byte[] data) {

        Photo photo = new Photo();

        photo.setId(UUID.randomUUID().toString()); // id should be generated in the backend
        photo.setFileName(fileName);
        photo.setData(data);
        photo.setContentType(contentType);

        db.put(photo.getId(), photo);

        return photo;
    }
}
