package uz.chat.app.entity.auth;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class UserOtp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users user;

    @Column(nullable = false)
    private String otp;

    @Column(nullable = false)
    private LocalDateTime otpExpiryDate;
}
