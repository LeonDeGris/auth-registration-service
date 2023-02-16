package com.bmstu.flowrence.dto.request.personal;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskCreateRequestDto extends AbstractPersonalRequestDto { // jsonSchema2pojo next time, for sure...

    String header;
    String description;

    String type;
    String priority;

    String assigneeUuid;
    String dashboardUuid;
}
