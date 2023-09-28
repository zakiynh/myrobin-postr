package com.myrobin.postr.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment", schema = "public")
public class Comment extends Base {

    @Column(name = "comment", length = 100)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
