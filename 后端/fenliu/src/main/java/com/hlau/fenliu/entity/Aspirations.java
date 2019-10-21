package com.hlau.fenliu.entity;

import com.hlau.fenliu.valid.MinSet;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;
@Data
public class Aspirations {
    @MinSet(message = "志愿不能重复")
    @NotNull
    Set<Aspiration> aspirations;
}
