package com.ll.gramgram.boundedContext.instaMember.entity;

import com.ll.gramgram.base.baseEntity.BaseEntity;
import com.ll.gramgram.boundedContext.likeablePerson.entity.LikeablePerson;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;


@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@SuperBuilder
@ToString(callSuper = true)
public class InstaMember extends BaseEntity {

    @Column(unique = true)
    private String username;
    @Setter
    private String gender;


    @OneToMany(mappedBy = "fromInstaMember", cascade = {CascadeType.ALL})
    @OrderBy("id desc") // 역순 정렬
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Builder.Default // @Builder 가 있으면 ` = new ArrayList<>();` 가 작동하지 않는다. 그래서 이걸 붙여야 한다.
    // LikeablePerson중에서 from이 나인 사람들
    private List<LikeablePerson> fromLikeablePeople = new ArrayList<>();

    @OneToMany(mappedBy = "toInstaMember", cascade = {CascadeType.ALL})
    @OrderBy("id desc") // 정렬
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Builder.Default // @Builder 가 있으면 ` = new ArrayList<>();` 가 작동하지 않는다. 그래서 이걸 붙여야 한다.
    // LikeablePerson중에서 to가 나인 사람들
    private List<LikeablePerson> toLikeablePeople = new ArrayList<>();

    public void addFromLikeablePerson(LikeablePerson likeablePerson) {
        // 인덱스가 0인것은 앞에다가 넣는다는 뜻 .역순 정렬이라 최신것이 앞에 들어가도록

        fromLikeablePeople.add(0, likeablePerson);
    }

    public void addToLikeablePerson(LikeablePerson likeablePerson) {
        //

        toLikeablePeople.add(0, likeablePerson);

    }

    public void removeFromLikeablePerson(LikeablePerson likeablePerson){
        fromLikeablePeople.removeIf(e -> e.equals(likeablePerson));
    }

    public void removeToLikeablePerson(LikeablePerson likeablePerson){
        fromLikeablePeople.removeIf(e->e.equals(likeablePerson));
    }

}
