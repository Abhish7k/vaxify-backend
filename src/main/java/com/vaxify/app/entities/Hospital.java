package com.vaxify.app.entities;

import com.vaxify.app.entities.enums.HospitalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "hospitals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    // Hospital STAFF user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_user_id", nullable = false)
    private User staffUser;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HospitalStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
