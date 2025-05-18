package com.gwabang.gwabang.commentlike.entity;

import com.gwabang.gwabang.comment.entity.Comment;
import com.gwabang.gwabang.member.entity.Member;
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

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment comment;

    @Column(name = "comment_count")
    private Long commentCount;

}