package com.accessibility.report;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Issue {
    private String issueType;
    private String issueCode;
    private List<String> issueTechniques;
    private String issueTag;
    private String issueId;
    private String issueMsg;
    private String issueElement;

    public Issue(String issue) {
        String[] arrIssue = issue.split("\\|");
        this.setIssueType(arrIssue[0]);
        this.setIssueCode(arrIssue[1]);
        this.setIssueTechniques(getIssueTechniques(arrIssue[1]));
        this.setIssueTag(arrIssue[2]);
        this.setIssueId(arrIssue[3]);
        this.setIssueMsg(arrIssue[4]);
        this.setIssueElement(arrIssue[5]);
    }

    public List<String> getIssueTechniques(String issueCode) {
        Pattern pattern = Pattern.compile("([A-Z]+[0-9]+(,[A-Z]+[0-9]+)*)");
        Matcher matcher = pattern.matcher(issueCode);
        LinkedList<String> codes = new LinkedList<>();
        while (matcher.find()) {
            String str = matcher.group();
            if(str.contains(",")){
                String[] techniques = str.split(",");
                for (String code : techniques){
                    codes.add(code);
                }
            }
            else {
                codes.add(str);
            }
        }
        if(codes.size() != 0){
            codes.remove(0);
        }
        return codes.stream().map(code -> "https://www.w3.org/TR/WCAG20-TECHS/"+code).collect(Collectors.toList());
    }

    public List<String> getIssueTechniques() {
        return issueTechniques;
    }

    public void setIssueTechniques(List<String> issueTechniques) {
        this.issueTechniques = issueTechniques;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getIssueCode() {
        return issueCode;
    }

    public void setIssueCode(String issueCode) {
        this.issueCode = issueCode;
    }

    public String getIssueTag() {
        return issueTag;
    }

    public void setIssueTag(String issueTag) {
        this.issueTag = issueTag;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getIssueMsg() {
        return issueMsg;
    }

    public void setIssueMsg(String issueMsg) {
        this.issueMsg = issueMsg;
    }

    public String getIssueElement() {
        return issueElement;
    }

    public void setIssueElement(String issueElement) {
        this.issueElement = issueElement;
    }
}
