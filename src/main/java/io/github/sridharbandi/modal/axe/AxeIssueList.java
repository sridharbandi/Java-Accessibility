package io.github.sridharbandi.modal.axe;

import java.util.List;
import java.util.Map;

public class AxeIssueList {
    private Map<String, Integer> stats;
    private int total;
    private List<AxeIssue> issueList;

    public Map<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }

    public List<AxeIssue> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<AxeIssue> issueList) {
        this.issueList = issueList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
