package com.bmstu.flowrence.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DashboardInfoDto implements ResponseDto { // jsonSchema2pojo next time, for sure...

    String uuid; // TODO: to abstract
    String name;
    String description;
    String prefix;

    List<TaskInfoDto> tasks;

}
