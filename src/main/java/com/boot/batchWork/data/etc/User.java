package com.boot.batchWork.data.etc;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class User {
    private final String userId;
    private final String password;
    private final String userIdSaveYn;
}
