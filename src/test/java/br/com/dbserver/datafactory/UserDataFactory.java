package br.com.dbserver.datafactory;

import br.com.dbserver.models.CreateUserModel;

public class UserDataFactory {
    public static CreateUserModel create() {
        return CreateUserModel.builder().name("morpheus").job("leader").build();
    }
}
