package com.bmstu.flowrence.dto.request.personal;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskUpdateRequestDto extends AbstractPersonalRequestDto { // jsonSchema2pojo next time, for sure...

    String taskUuid;
    String header;
    String description;

    String priority;

    String assigneeUuid;
}
