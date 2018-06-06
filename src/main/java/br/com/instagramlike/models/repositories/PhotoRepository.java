package br.com.instagramlike.models.repositories;

import br.com.instagramlike.models.domains.Photo;
import br.com.instagramlike.models.domains.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Api(tags = "Photo Repository")
@RepositoryRestResource(path = "gallery")
@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    @ApiOperation("Find All photos by username refference")
    Optional<Photo> findByReffNameAndOwner(String reffName, User owner);

    //WE HAVE TO ANALISE THE IN (VALUES FROM FOLLOWES)
    @ApiOperation("Find all photos by ")
    Page<Photo> findByOwner(Pageable pageable, User owner);

//    @Query("SELECT p FROM Photo p JOIN p.owner o WHERE  o = :owner")
//    Page<Photo> findByOwner(Pageable pageable, @Param("owner") User owner);
}
