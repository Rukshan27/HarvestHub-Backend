package com.harvest.hub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class MBaseEntity implements Serializable {

    @Column(name = "IS_ACTIVE", nullable = false, columnDefinition = "tinyint(1) default 1", length = 1)
    protected Boolean active;

    @Column(name = "CREATED_BY")
    protected String createdBy;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", nullable = false)
    protected LocalDateTime createdDate;

    @Column(name = "UPDATED_BY")
    protected String updatedBy;

    @UpdateTimestamp
    @Column(name = "UPDATED_DATE")
    protected LocalDateTime updatedDate;

    @PrePersist
    private void setActive() {
        this.active = true;
        this.createdDate = LocalDateTime.now();
    }

}
