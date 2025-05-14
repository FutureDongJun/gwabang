package com.gwabang.gwabang.commentlike.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "commentlike")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="member_id")
    private Long memberId;

    @Column(name="comment_id")
    private Long commentId;

    @Column(name = "comment_count")
    private Long commentCount;

}