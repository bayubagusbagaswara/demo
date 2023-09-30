package com.bayu.demo.controller;

import com.bayu.demo.dto.RecruitmentDTO;
import com.bayu.demo.dto.WebResponse;
import com.bayu.demo.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/recruitments")
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @GetMapping
    public ResponseEntity<WebResponse<List<RecruitmentDTO>>> getAllRecruitments() {
        List<RecruitmentDTO> allRecruitments = recruitmentService.getAllRecruitments();
        return new ResponseEntity<>(new WebResponse<>(Boolean.TRUE, "Success", allRecruitments), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<WebResponse<RecruitmentDTO>> getJobById(@PathVariable(name = "id") String id) {
        RecruitmentDTO job = recruitmentService.getJobById(id);
        return new ResponseEntity<>(new WebResponse<>(Boolean.TRUE, "Success", job), HttpStatus.OK);
    }

}
