package com.lambferret.playhttpground.document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Movie {
    String title;
    String rank;
    String id;
    Boolean isFixed;
    String ownerSession;
}
