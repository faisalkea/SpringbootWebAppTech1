package com.atkins.f2integrationjava.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewCaseArgs implements Serializable {

    private String title;
    private String keywordCode;
    private List<String> keywordCodes;

    private String journalPlanCode;
    private String deletionActionCode;
    private String processInstructionCode;
    private String taskGuideKey;

    private List securityGrouops;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywordCode() {
        return keywordCode;
    }

    public void setKeywordCode(String keywordCode) {
        this.keywordCode = keywordCode;
    }

    public List<String> getKeywordCodes() {
        return keywordCodes;
    }

    public void setKeywordCodes(List<String> keywordCodes) {
        this.keywordCodes = keywordCodes;
    }

    public String getJournalPlanCode() {
        return journalPlanCode;
    }

    public void setJournalPlanCode(String journalPlanCode) {
        this.journalPlanCode = journalPlanCode;
    }

    public String getDeletionActionCode() {
        return deletionActionCode;
    }

    public void setDeletionActionCode(String deletionActionCode) {
        this.deletionActionCode = deletionActionCode;
    }

    public String getProcessInstructionCode() {
        return processInstructionCode;
    }

    public void setProcessInstructionCode(String processInstructionCode) {
        this.processInstructionCode = processInstructionCode;
    }

    public String getTaskGuideKey() {
        return taskGuideKey;
    }

    public void setTaskGuideKey(String taskGuideKey) {
        this.taskGuideKey = taskGuideKey;
    }

    public List getSecurityGrouops() {
        return securityGrouops;
    }

    public void setSecurityGrouops(List securityGrouops) {
        this.securityGrouops = securityGrouops;
    }

    @Override
    public String toString() {
        return "NewCaseArgs{" +
                "title='" + title + '\'' +
                ", keywordCode='" + keywordCode + '\'' +
                ", keywordCodes=" + keywordCodes +
                ", journalPlanCode='" + journalPlanCode + '\'' +
                ", deletionActionCode='" + deletionActionCode + '\'' +
                ", processInstructionCode='" + processInstructionCode + '\'' +
                ", taskGuideKey='" + taskGuideKey + '\'' +
                ", securityGrouops=" + securityGrouops +
                '}';
    }
}
