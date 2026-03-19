package com.curso.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostEtiquetaId implements Serializable {

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "etiqueta_id")
    private Long etiquetaId;
}
