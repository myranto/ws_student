package com.my.ws_student.controller;

import com.my.ws_student.models.community.Commentaire;
import com.my.ws_student.models.community.Publication;
import com.my.ws_student.utils.ToJsonData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/publication")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class PublicationController {
    @GetMapping("/select")
    public ResponseEntity<ArrayList<Publication>> getAllpub() throws Exception {
        ArrayList<Publication> list = new Publication().SelectAll();
        System.out.println(list.size());
        System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
