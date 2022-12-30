package com.lambferret.playhttpground.document.attribute;

import com.lambferret.playhttpground.document.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@Builder
public class CreateAttribute {
    private Map<String, List<Movie>> ab;
}
