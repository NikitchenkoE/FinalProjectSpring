package com.example.finalprojectspring.mailService;

public interface EmailInterface {
    void sendSimpleMessage(String to, String subject, String text);
}
