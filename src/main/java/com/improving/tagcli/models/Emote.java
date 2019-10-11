package com.improving.tagcli.models;

public class Emote {
    private int id;
    private String prompt;
    private String text;

    public int getId() {
        return id;
    }

    public String getPrompt() {
        return prompt;
    }
    public void setPrompt(String prompt) { this.prompt = prompt; }

    public String getText() {
        return text;
    }
    public void setText(String text) { this.text = text;}

    public Emote() {}
    public Emote(String prompt, String text) {
        this.prompt = prompt;
        this.text = text;
    }
}
