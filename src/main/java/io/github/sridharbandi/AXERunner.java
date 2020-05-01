/**
 * Copyright (c) 2020 Sridhar Bandi.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.sridharbandi;

import com.google.common.io.Files;
import freemarker.template.Template;
import io.github.sridharbandi.ftl.FtlConfig;
import io.github.sridharbandi.modal.axe.AxeIssue;
import io.github.sridharbandi.modal.axe.AxeIssueList;
import io.github.sridharbandi.modal.axe.AxeIssues;
import io.github.sridharbandi.report.Result;
import io.github.sridharbandi.util.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static io.github.sridharbandi.util.Data.*;
import static io.github.sridharbandi.util.Runner.*;

public class AXERunner extends Result {

    private static Logger LOG = LoggerFactory.getLogger(AXERunner.class);
    private Map<String, Object> issueList;
    private Map<String, AxeIssueList> processedIssues;
    private AxeIssues issues;
    private String standard;

    public AXERunner(WebDriver driver) {
        super(driver);
    }

    public void execute() {
        execute(pageTitle());
    }

    public void execute(String pageName) {
        LOG.info("Running Accessibility for {} page", pageName);
        issueList = executeAxe();
        processedIssues = axeIssueList(issueList);
        standard = getStandards(issueList);
        issues = getIssues(pageName);
        SaveJson.save(issues, pageName, "axe");
    }

    private String getStandards(Map<String, Object> issueList) {
        Map<String, Object> toolOptions = (Map<String, Object>) issueList.get("toolOptions");
        if (toolOptions.containsKey("runOnly")) {
            Map<String, Object> runOnly = (Map<String, Object>) toolOptions.get("runOnly");
            List<String> tags = (ArrayList<String>) runOnly.get("values");
            return String.join(",", tags);
        }
        return "wcag2a,wcag2aa,section508,best-practice";
    }

    private AxeIssues getIssues(String reportName) {
        AxeIssues issues = new AxeIssues();
        issues.setCritical(issueCount("critical"));
        issues.setSerious(issueCount("serious"));
        issues.setModerate(issueCount("moderate"));
        issues.setMinor(issueCount("minor"));
        issues.setStandard(standard);
        issues.setEngine("AXE");
        issues.setUrl(url());
        issues.setDate(DateUtil.getDate());
        issues.setSize(viewPort());
        issues.setDevice(device());
        issues.setBrowser(browserName());
        issues.setName(reportName.isEmpty() ? pageTitle() : reportName);
        issues.setReportID(UUID.randomUUID().toString().replace("-", ""));
        issues.setIssues(processedIssues);
        return issues;
    }

    private int issueCount(String impact) {
        return processedIssues.values()
                .stream()
                .map(AxeIssueList::getIssueList)
                .flatMap(List::stream)
                .filter(axeIssue -> axeIssue.getImpact().equalsIgnoreCase(impact))
                .map(AxeIssue::getNodes)
                .mapToInt(List::size)
                .sum();
    }

    public void generateHtmlReport() {
        Template tmplPage = FtlConfig.getInstance().getTemplate("axe-page.ftl");
        List<AxeIssues> allissues = (List<AxeIssues>) jsonIssues(AXE, AxeIssues.class);
        for (AxeIssues issues : allissues) {
            Map<String, Object> map = new HashMap<>();
            map.put("reportname", issues.getName());
            map.put("url", issues.getUrl());
            map.put("standard", issues.getStandard());
            map.put("browser", issues.getBrowser());
            map.put("browsersize", issues.getSize());
            map.put("device", issues.getDevice());
            map.put("datetime", issues.getDate());
            map.put("engine", "Axe");
            map.put("version", Statik.AXE_VERSION);
            map.put("criticalcount", issues.getCritical());
            map.put("seriouscount", issues.getSerious());
            map.put("moderatecount", issues.getModerate());
            map.put("minorcount", issues.getMinor());
            map.put("results", issues.getIssues());
            save(tmplPage, map, issues.getReportID(), AXE);
        }
        Template tmplIndex = FtlConfig.getInstance().getTemplate("axe-index.ftl");
        Map<String, Object> map = new HashMap<>();
        map.put("reportname", "Accessibility Report");
        map.put("urlcount", reportAxeData(allissues, URL).size());
        map.put("criticalcount", count((List<Integer>) reportAxeData(allissues,CRITICAL)));
        map.put("seriouscount", count((List<Integer>) reportAxeData(allissues,SERIOUS)));
        map.put("moderatecount", count((List<Integer>) reportAxeData(allissues,MODERATE)));
        map.put("minorcount", count((List<Integer>) reportAxeData(allissues,MINOR)));
        map.put("urls", reportAxeData(allissues, URL));
        map.put("critical", reportAxeData(allissues,CRITICAL));
        map.put("serious", reportAxeData(allissues,SERIOUS));
        map.put("moderate", reportAxeData(allissues,MODERATE));
        map.put("minor", reportAxeData(allissues,MINOR));
        map.put("issues", allissues);
        save(tmplIndex, map, "index", AXE);
    }

}
