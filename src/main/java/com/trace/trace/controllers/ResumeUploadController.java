package com.trace.trace.controllers;

import com.trace.trace.models.Resume;
import com.trace.trace.repositories.ResumeRepository;
import com.trace.trace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

@Controller
public class ResumeUploadController {

    @Value("${file-upload-path}")
    private String uploadPath;

    private final ResumeRepository resumeDao;

    public ResumeUploadController(ResumeRepository resumeDao) {
        this.resumeDao = resumeDao;
    }

    @PostMapping("/fileupload")
    public String saveFile(Model model, @RequestParam(name = "file") MultipartFile uploadFile, @ModelAttribute Resume resume) {
        String filename = uploadFile.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);
        resume.setCreatedAt(new Date(System.currentTimeMillis()));
        resume.setFilePath(filepath);
        try {
            uploadFile.transferTo(destinationFile);
            resumeDao.save(resume);
            model.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }
        return "fileupload";
    }

}
