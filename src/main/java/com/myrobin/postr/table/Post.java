package com.myrobin.postr.table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "post", schema = "public")
public class Post extends Base {

    @Column(name = "post")
    private String post;
}
