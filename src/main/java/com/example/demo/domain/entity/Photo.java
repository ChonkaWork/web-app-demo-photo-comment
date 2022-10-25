package com.example.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "photos")
@Getter
@Setter
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY means that id will generate with your DB
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String url;

    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
    @Setter(AccessLevel.PRIVATE)
    @JsonBackReference // to avoid stackoverflow exception
    private List<Comment> comments = new ArrayList<>();

    //Best practice create helper method to encapsulate adding objects to list.
    //You can correctly support synchronisation between chained entities
    public void addComment(Comment comment) {
        comment.setPhoto(this);
        comments.add(comment);
    }
}
