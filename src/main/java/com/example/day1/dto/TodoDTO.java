package com.example.day1.dto;

import com.example.day1.model.Priority;
import com.example.day1.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoDTO {
    @NotBlank(message = "Không được bỏ trống")
    private String content;
    @FutureOrPresent(message = "Không được lấy ngày trong quá khứ")
    private LocalDate dueDate;

    private Status status;

    private Priority priority;
}
