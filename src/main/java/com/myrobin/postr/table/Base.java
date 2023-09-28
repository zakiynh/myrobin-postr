package com.myrobin.postr.table;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class Base {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="created_at_utc0")
    private Long createdAtUtc0;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="updated_at_utc0")
    private Long updatedAtUtc0;

    @Column(name="updated_by")
    private String updatedBy;

    @PrePersist
    public void prePersist() {
        if (null == this.createdAtUtc0) this.createdAtUtc0 = new Date().getTime();
        if (null == this.createdBy) this.createdBy = "system";
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAtUtc0 = new Date().getTime();
    }
}
