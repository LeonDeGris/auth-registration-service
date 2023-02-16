package com.bmstu.flowrence.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListDashboardTasksRequestDto implements RequestDto {

    String dashboardUuid;
}
