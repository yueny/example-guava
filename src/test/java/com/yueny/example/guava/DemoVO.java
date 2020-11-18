package com.yueny.example.guava;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemoVO {
    private Long age;

    private String name;

    private List<String> history;
}
