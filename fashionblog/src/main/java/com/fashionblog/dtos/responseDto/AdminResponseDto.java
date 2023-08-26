package com.fashionblog.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponseDto {
    private Long adminId;
    private String name;
    private String email;
}




//package com.fashionblog.dtos.responseDto;
//
//public class AdminResponseDto {
//}
