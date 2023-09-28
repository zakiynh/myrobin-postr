package com.myrobin.postr.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment", schema = "public")
public class Comment extends Base {

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
