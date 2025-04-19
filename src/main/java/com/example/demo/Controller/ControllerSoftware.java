package com.example.demo.Controller;

import com.example.demo.Model.Software;
import com.example.demo.Services.SoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/software")
public class ControllerSoftware {

    @Autowired
    private SoftwareService service;

   @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
   public Software findById(@PathVariable("id") Long id){
       return service.findById(id);
   }

   @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
   public Software create(@RequestBody Software software){
       return service.save(software);
    }
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Software update(@RequestBody Software software){
       return service.save(software);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Software> findAll(){
       return service.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public Software delete(@PathVariable("id") Long id){
       service.delete(id);
       return null;
    }

}


