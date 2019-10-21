package com.hlau.fenliu.entity;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Component
public class Aspiration implements Comparable<Aspiration> , Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int objectId;
    private int level;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="aspiration_major")
    private Major major;
    @Override
    public int compareTo(Aspiration o) {
        return level-o.level;
    }

    @Override
    public boolean equals(Object obj) {
        Aspiration aspiration=(Aspiration) obj;
        return aspiration.getMajor().getObjectId()==this.getMajor().getObjectId();
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
