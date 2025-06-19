package com.url.url_shortner.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ClickEventDTO {
    private LocalDate clickDate;
    private Long count;

    public LocalDate getClickDate() {
        return clickDate;
    }

    public LocalDate getCreatedDate() {
        return clickDate;
    }

    public Long getCount() {
        return count;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.clickDate = createdDate;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setClickDate(LocalDate clickDate) {
        this.clickDate = clickDate;
    }
}
