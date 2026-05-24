package com.obviously20.pojo;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOption {
    private List classList;
    private List dataList;
}
