package com.bmstu.flowrence.dto.request.personal;

import lombok.Getter;

@Getter
public class UserToTeamActionRequestDto extends AbstractPersonalRequestDto { // jsonSchema2pojo next time, for sure...

    private String teamUuid;
}

