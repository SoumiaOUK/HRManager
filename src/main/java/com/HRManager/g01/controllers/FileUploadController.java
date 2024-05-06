package com.HRManager.g01.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    @RequestMapping("/showUpload")
    public String showUpload(){
        return "Leaves/UploadFile";
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            /*
            model.addAttribute("message", "Please select a file to upload");
            return "uploadError";

             */
            System.out.println("Please select a file");
        }

        try {
            // Get the filename and build the local file path
            String fileName = file.getOriginalFilename();
            String directoryPath = "/Users/hammou/Desktop/WorkSpace/g01/src/main/resources/Files/"; // Change the directory as per your requirement

            // Create the directory if it does not exist
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                System.out.println("directory created");
                directory.mkdirs(); // Create directories including any necessary but nonexistent parent directories
            }

            String filePath = directoryPath + fileName;

            // Save the file to the local file system
            File dest = new File(filePath);
            file.transferTo(dest);

            // Print file details to the console
            System.out.println("Uploaded file: " + filePath);

            // Add success message to the model
            model.addAttribute("message", "File uploaded successfully");
        } catch (IOException e) {
            /*
            e.printStackTrace();
            model.addAttribute("message", "Failed to upload file");
            return "uploadError";

             */
            System.out.println("Failed to upload file Exception "+e.getMessage());

        }

        return "Leaves/FileUploaded";
    }

}
