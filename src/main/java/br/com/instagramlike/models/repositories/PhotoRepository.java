package br.com.instagramlike.models.repositories;

import br.com.instagramlike.models.domains.Photo;
import br.com.instagramlike.models.domains.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    Optional<Photo> findByReffNameAndOwner(String reffName, User owner);

    //WE HAVE TO ANALISE THE IN (VALUES FROM FOLLOWES)
    Page<Photo> findByOwner(Pageable pageable, User owner);

//    @Query("SELECT p FROM Photo p JOIN p.owner o WHERE  o = :owner")
//    Page<Photo> findByOwner(Pageable pageable, @Param("owner") User owner);
}
