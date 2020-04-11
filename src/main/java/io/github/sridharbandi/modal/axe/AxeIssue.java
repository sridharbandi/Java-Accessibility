package io.github.sridharbandi.modal.axe;

import java.util.ArrayList;

public class AxeIssue {
    private String description;
    private String help;
    private String helpUrl;
    private String id;
    private String impact;
    private ArrayList<AxeNode> nodes;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getHelpUrl() {
        return helpUrl;
    }

    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public ArrayList<AxeNode> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<AxeNode> nodes) {
        this.nodes = nodes;
    }
}
