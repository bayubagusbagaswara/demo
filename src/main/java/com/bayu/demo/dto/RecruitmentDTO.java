package com.bayu.demo.dto;

import com.google.gson.annotations.SerializedName;
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

    @SerializedName("created_at")
    private String createdAt;

    private String company;

    @SerializedName("company_url")
    private String companyUrl;

    private String location;

    private String title;

    private String description;

}
