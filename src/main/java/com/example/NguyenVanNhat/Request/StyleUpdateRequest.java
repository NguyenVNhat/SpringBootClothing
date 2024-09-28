package com.example.NguyenVanNhat.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StyleUpdateRequest {
    private int id;
    private String newValue;
}
