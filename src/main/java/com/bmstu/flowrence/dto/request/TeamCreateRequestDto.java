package com.bmstu.flowrence.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamCreateRequestDto implements RequestDto { // jsonSchema2pojo next time, for sure...

    String name;

}
