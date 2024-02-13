package com.boot.batchWork.data.meta;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class UserInfo {
    private final String userId;
    private final String password;
    private final String userIdSaveYn;
}
