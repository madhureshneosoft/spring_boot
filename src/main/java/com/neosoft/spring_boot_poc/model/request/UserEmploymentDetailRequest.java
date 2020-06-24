package com.neosoft.spring_boot_poc.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEmploymentDetailRequest {

    private String department;

    private Byte experience;
}
