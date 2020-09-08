package com.michau.kurseasyapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Post {

    @Id
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
}
