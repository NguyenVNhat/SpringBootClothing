package com.example.NguyenVanNhat.Repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponeObject {
    private boolean status;
    private String message;
    private Object data;
}
