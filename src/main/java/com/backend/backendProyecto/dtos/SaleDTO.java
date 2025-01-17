package com.backend.backendProyecto.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class SaleDTO {
    private int id;
    private LocalDateTime date;
    private List<SaleDetailDTO> details;

    public SaleDTO(int id, LocalDateTime date, List<SaleDetailDTO> details) {
        this.id = id;
        this.date = date;
        this.details = details;
    }

    public SaleDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<SaleDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<SaleDetailDTO> details) {
        this.details = details;
    }
}
