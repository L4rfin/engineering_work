package ekg;

import ekg.entity.ResultsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<ResultsEntity,Long> {



}
