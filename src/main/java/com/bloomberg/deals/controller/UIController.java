/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.deals.controller;

import com.bloomberg.deals.MainApp;
import com.bloomberg.deals.dto.StatReport;
import com.bloomberg.deals.dto.entity.DealFile;
import com.bloomberg.deals.exception.MigrationException;

import com.bloomberg.deals.repository.DealCountRepository;
import com.bloomberg.deals.repository.DealFileRepository;
import com.bloomberg.deals.services.CSVService;
import com.bloomberg.deals.services.DealFileService;
import com.bloomberg.deals.services.DealService;

import com.bloomberg.deals.repository.DealFileRepository;
import com.bloomberg.deals.services.CSVService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author johnson3yo
 */
@Controller
public class UIController {

    private static Logger logger = LogManager.getLogger(MainApp.class);
    @Value("${path.deal.upload}")
    private String csvFilePath;
    private @Autowired
    DealFileRepository dfileRepo;
    private @Autowired
    CSVService csvserve;
    @Autowired
    private DealFileService dserve;
    @Autowired
    private DealService serve;
    @Autowired
    private DealCountRepository dcrep;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @GetMapping("/home")
    public String home() {
        return "upload";
    }

    @GetMapping("/viewdeals")
    public String viewDeals() {
        return "deal_file";
    }

    @GetMapping("/count")
    public String dealsCount() {
        return "deals_count";
    }

    @GetMapping("/deals")
    public String findAllDeals(Model model, @RequestParam(value = "pageNo", defaultValue = "1") String pageNo) {
        model.addAttribute("deals", serve.fetchDeals(pageNo));
        return "deal";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes,
            @RequestParam("fileName") String fileName) {

        File f = new File(csvFilePath);
        if (!f.exists()) {
            FileUtils.mkdir(csvFilePath);
        }

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/";
        }

        String fullPath = csvFilePath.concat(file.getOriginalFilename());
        File fp = new File(fullPath);
        //validating against file
        if (fp.exists()) {
            redirectAttributes.addFlashAttribute("message", "File Exists and has been processed");
            return "redirect:/";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(fullPath);
            Files.write(path, bytes);
            DealFile df = new DealFile();
            df.setFilePath(fullPath);
            df.setFileName(fileName);

            dfileRepo.save(df);
            //calculate time
            long startTime = System.currentTimeMillis();
            StatReport stat = csvserve.processFile(df);
            long endTime = System.currentTimeMillis();
            stat.setProcessDuration((endTime - startTime));

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            long duration = endTime - startTime;
            String sec = "seconds";
            if (duration == 0) {
                sec = "seconds";
            }
            redirectAttributes.addFlashAttribute("duration",
                    "Process Duration took " + TimeUnit.MILLISECONDS.toSeconds(duration) + " " + sec);
            redirectAttributes.addFlashAttribute("validreport",
                    "Valid row count is " + stat.getValidRecordsCount());
            redirectAttributes.addFlashAttribute("invalidreport",
                    "Invalid row count is " + (stat.getInvalidRecordsCount() - 1));

        } catch (IOException e) {
            logger.debug(e.getMessage());
        } catch (MigrationException ex) {
            logger.debug(ex.getMessage());
        }

        return "redirect:/";

    }

    @GetMapping("/findDealByFileName")
    public String findDealByFileName(@RequestParam("fileName") String fileName,
            RedirectAttributes redirectAttributes, Model model) throws Exception {
        redirectAttributes.addFlashAttribute("dealFile",
                dserve.getFiles("%".concat(fileName).concat("%")));
        return "redirect:/viewdeals";
    }

    @GetMapping("/dealcount")
    public String dealCount(Model model) throws Exception {
        model.addAttribute("deals", dcrep.getAccumulatedDealCount());
        return "deals_count";

    }

}
