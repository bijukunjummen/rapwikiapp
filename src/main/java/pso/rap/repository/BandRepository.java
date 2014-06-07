package pso.rap.repository;


import org.springframework.data.repository.CrudRepository;
import pso.rap.domain.Band;

public interface BandRepository extends CrudRepository<Band, Long> {

}
