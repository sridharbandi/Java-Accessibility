/**
 * Copyright (c) 2020 Sridhar Bandi.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.sridharbandi.modal.htmlcs;

import java.util.List;

public class Issue {
    private Long issueType;
    private String issueCode;
    private List<String> issueTechniques;
    private String issueTag;
    private String issueId;
    private String issueMsg;
    private String issueElement;

    public List<String> getIssueTechniques() {
        return issueTechniques;
    }

    public void setIssueTechniques(List<String> issueTechniques) {
        this.issueTechniques = issueTechniques;
    }

    public Long getIssueType() {
        return issueType;
    }

    public void setIssueType(Long issueType) {
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
