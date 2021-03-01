package br.com.dbserver.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateUserModel {
    private String name;
    private String job;
}
