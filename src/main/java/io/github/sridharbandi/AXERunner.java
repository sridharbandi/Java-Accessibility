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

import io.github.sridharbandi.modal.Issue;
import io.github.sridharbandi.modal.Issues;
import io.github.sridharbandi.modal.axe.AxeIssue;
import io.github.sridharbandi.modal.axe.AxeIssues;
import io.github.sridharbandi.modal.axe.AxeNode;
import io.github.sridharbandi.report.Result;
import io.github.sridharbandi.util.DateUtil;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AXERunner extends Result {

    private static Logger LOG = LoggerFactory.getLogger(AXERunner.class);
    private Map<String, Object> issueList;
    private Map<String, List<AxeIssue>> processedIssues;
    private AxeIssues issues;

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
        issues = getIssues(pageName);
        System.out.println("Hello");
    }

    private AxeIssues getIssues(String reportName) {
        AxeIssues issues = new AxeIssues();
        issues.setCritical(issueCount("critical"));
        issues.setSerious(issueCount("serious"));
        issues.setModerate(issueCount("moderate"));
        issues.setMinor(issueCount("minor"));
        //Todo Change the Accessibility Standar Logic
        issues.setStandard(Accessibility.STANDARD.name());
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
                .flatMap(List::stream)
                .filter(axeIssue -> axeIssue.getImpact().equalsIgnoreCase(impact))
                .map(AxeIssue::getNodes)
                .mapToInt(List::size)
                .sum();
    }

}
