package com.bmstu.flowrence.dto.request.personal;

import com.bmstu.flowrence.dto.request.RequestDto;
import lombok.Getter;

@Getter
public abstract class AbstractPersonalRequestDto implements RequestDto { // jsonSchema2pojo next time, for sure...

    private String userUuid;

}
