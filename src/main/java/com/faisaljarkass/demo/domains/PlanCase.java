package com.atkins.f2integrationjava.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanCase implements Serializable {

    private Long id;
    private String caseNumber;
    private String title;
    private String cprNumber;
    private String externalId;

    private Enum keyword;
    private List<Enum> keywords;

    private Enum journalPlan;
    private Enum processInstruction;
    private Enum deletionAction;

    //private AtomLinkList links;
    private List links;

    private String taskGuideKey;

    //private RawXmlString extensionData;
    private String extensionData;

    private List<PartyGroupItem> securityGroups;

    public PlanCase() {
        this.links = new ArrayList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCprNumber() {
        return cprNumber;
    }

    public void setCprNumber(String cprNumber) {
        this.cprNumber = cprNumber;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Enum getKeyword() {
        return keyword;
    }

    public void setKeyword(Enum keyword) {
        this.keyword = keyword;
    }

    public List<Enum> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Enum> keywords) {
        this.keywords = keywords;
    }

    public Enum getJournalPlan() {
        return journalPlan;
    }

    public void setJournalPlan(Enum journalPlan) {
        this.journalPlan = journalPlan;
    }

    public Enum getProcessInstruction() {
        return processInstruction;
    }

    public void setProcessInstruction(Enum processInstruction) {
        this.processInstruction = processInstruction;
    }

    public Enum getDeletionAction() {
        return deletionAction;
    }

    public void setDeletionAction(Enum deletionAction) {
        this.deletionAction = deletionAction;
    }

    public List getLinks() {
        return links;
    }

    public void setLinks(List links) {
        this.links = links;
    }

    public String getTaskGuideKey() {
        return taskGuideKey;
    }

    public void setTaskGuideKey(String taskGuideKey) {
        this.taskGuideKey = taskGuideKey;
    }

    public String getExtensionData() {
        return extensionData;
    }

    public void setExtensionData(String extensionData) {
        this.extensionData = extensionData;
    }

    public List<PartyGroupItem> getSecurityGroups() {
        return securityGroups;
    }

    public void setSecurityGroups(List<PartyGroupItem> securityGroups) {
        this.securityGroups = securityGroups;
    }

    @Override
    public String toString() {
        return "PlanCase{" +
                "id=" + id +
                ", caseNumber='" + caseNumber + '\'' +
                ", title='" + title + '\'' +
                ", cprNumber='" + cprNumber + '\'' +
                ", externalId='" + externalId + '\'' +
                ", keyword=" + keyword +
                ", keywords=" + keywords +
                ", journalPlan=" + journalPlan +
                ", processInstruction=" + processInstruction +
                ", deletionAction=" + deletionAction +
                ", links=" + links +
                ", taskGuideKey='" + taskGuideKey + '\'' +
                ", extensionData='" + extensionData + '\'' +
                ", securityGroups=" + securityGroups +
                '}';
    }
}
