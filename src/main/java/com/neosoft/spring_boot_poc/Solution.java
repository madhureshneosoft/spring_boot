//package com.neosoft.spring_boot_poc;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//public class Solution {
//    public static void main(String[] args) {
//        List<String> userTable = Arrays.asList("username", "createDate", "updateDate");
//        List<String> userDetailTable = Arrays.asList("firstName", "lastName", "mobileNumber", "emailId", "pincode");
//        List<String> userEducationTable = Arrays.asList("sscPercentage", "hscPercentage", "cgpa");
//        List<String> userEmploymentTable = Arrays.asList("salary", "workEmail", "workMobileNumber", "department", "dateOfJoin", "experience");
//        List<String> userProjectTable = Arrays.asList("projectName", "projectCompany", "startDate", "endDate");
//        List<List<String>> masterList = Arrays.asList(userTable, userProjectTable, userEmploymentTable, userEducationTable, userDetailTable);
//
//        String test1 = "sscPercentage";
//        String test2 = "hscPercentage";
//        String test3 = "salary";
//        String name = "";
//
//        String field1 = "";
//        String field2 = "";
//        String field3 = "";
//
//        List<String> fields = Arrays.asList(test1, test2, test3);
//
//        for (String s : fields) {
//            for (List<String> ls : masterList) {
//                if (ls.contains(s)) {
//                    switch (ls.get(0)) {
//                        case "username":
//                            name = "";
//                            break;
//                        case "firstName":
//                            name = "userDetail";
//                            break;
//                        case "sscPercentage":
//                            name = "userEducationDetail";
//                            break;
//                        case "salary":
//                            name = "userEmploymentDetail";
//                            break;
//                        case "projectName":
//                            name = "userProjectDetail";
//                            break;
//                    }
//                }
//            }
//        }
//
//        System.out.println("99786"+ (10000 + new Random().nextInt(99999 - 10000 + 1)));
//    }
//}
