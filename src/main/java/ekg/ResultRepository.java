package ekg;

import ekg.entity.ResultsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<ResultsEntity,Long> {
    List<ResultsEntity> findAllByUserId(long id);
    ResultsEntity getById(long id);

}
