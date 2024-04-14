package com.aquabrim.controller.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_activity")
public class UserActivity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "controller_id", nullable = false)
    private Controller controller;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    @Column(nullable = false)
    private String commandSent;

    @Column(nullable = false)
    private String sentOrReceived;

    @Column(nullable = false)
    private LocalDateTime timestamp;

}
