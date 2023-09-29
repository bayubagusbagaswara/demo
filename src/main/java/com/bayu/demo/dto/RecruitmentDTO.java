package com.bayu.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentDTO {

    private String id;

    private String type;

    private String created_at;

    private String company;

    private String company_url;

    private String location;

    private String title;

    private String description;

}
