package com.mongo.project1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private @Getter @Setter String id;

    private @Getter @Setter String name;

    private @Getter @Setter String designation;

    private @Getter @Setter String email;

    private @Getter @Setter String contact;

    private @Getter @Setter int age;

}
