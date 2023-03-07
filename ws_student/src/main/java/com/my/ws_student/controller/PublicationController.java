package com.my.ws_student.controller;

import com.my.ws_student.models.community.Publication;
import com.my.ws_student.utils.ToJsonData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publication")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class PublicationController {
    @GetMapping("/select")
    public ResponseEntity<ToJsonData> getAllpub() throws Exception {
        return new ResponseEntity<>(new ToJsonData<>(new Publication().SelectAll(),null), HttpStatus.OK);
    }
}
