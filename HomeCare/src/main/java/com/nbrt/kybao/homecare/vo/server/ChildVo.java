package com.nbrt.kybao.homecare.vo.server;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


import java.util.List;

@Data
public class ChildVo<T> {
    private Long value;
    private String label;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<T> children;
}
