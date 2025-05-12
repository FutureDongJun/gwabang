package com.gwabang.gwabang.member.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_key", nullable = false, unique = true, length = 40)
    private String memberKey;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(name = "payment_account_id")
    private Long paymentAccountId;

    @Column(name = "main_account_id", unique = true)
    private Long mainAccountId;
}

