package com.mongo.controller.dto;

import lombok.Data;

@Data
public class StudentQueryDTO {

   private Integer ageStart;
   private Integer ageEnd;
   private String dname;
}
