package com.example.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class HistoriqueRetourDTO {

    private Long id;  // Include ID for read operations

    @NotNull(message = "Retour ID is required")
    private Long retourId;

    @NotNull(message = "Action is required")
    private String action;

    @NotNull(message = "Employe ID is required")
    private Long employeId;

    @NotNull(message = "Date is required")
    private LocalDateTime date;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRetourId() {
        return retourId;
    }

    public void setRetourId(Long retourId) {
        this.retourId = retourId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
