package com.example.kahoot.dto;

public class AnswerDTO {
    private Long id;
    private String text;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
