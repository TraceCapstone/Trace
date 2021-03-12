//package com.trace.trace.controllers;
//
//import com.trace.trace.models.Resume;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Paths;
//
//@Controller
//public class ResumeUploadController {
//
//    @Value("${file-upload-path}")
//    private String uploadPath;
//
//    @PostMapping("/fileupload")
//    public String saveFile(Model model, @RequestParam(name = "file") MultipartFile uploadFile, @ModelAttribute Resume resume) {
//        String filename = uploadFile.getOriginalFilename();
//        String filepath = Paths.get(uploadPath, filename).toString();
//        File destinationFile = new File(filepath);
//        try {
//            uploadFile.transferTo(destinationFile);
//            model.addAttribute("message", "File successfully uploaded!");
//        } catch (IOException e) {
//            e.printStackTrace();
//            model.addAttribute("message", "Oops! Something went wrong! " + e);
//        }
//        return "fileupload";
//    }
//
//}
