package com.lambferret.playhttpground.document;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) String id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) Boolean isFixed;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) String ownerSession;
}
