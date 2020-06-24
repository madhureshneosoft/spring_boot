package com.neosoft.spring_boot_poc.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserProjectDetailResponse {

    private String projectName;

    private String projectDetail;

    private boolean active;

}
