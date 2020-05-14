package io.github.sridharbandi.modal.axe;

import java.util.Map;

public class AxeIssues {
    private int critical;
    private int serious;
    private int moderate;
    private int minor;
    private String name;
    private String engine;
    private String standard;
    private String url;
    private String date;
    private String size;
    private String device;
    private String browser;
    private String reportID;
    private Map<String, AxeIssueList> issues;

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getSerious() {
        return serious;
    }

    public void setSerious(int serious) {
        this.serious = serious;
    }

    public int getModerate() {
        return moderate;
    }

    public void setModerate(int moderate) {
        this.moderate = moderate;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public Map<String, AxeIssueList> getIssues() {
        return issues;
    }

    public void setIssues(Map<String, AxeIssueList> issues) {
        this.issues = issues;
    }
}
