package edu.miu.sa.batchadmin.dto;

import lombok.*;

@Data
@Setter@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String username;
    private String password;
}
