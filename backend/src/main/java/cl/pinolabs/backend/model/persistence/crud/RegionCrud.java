package cl.pinolabs.backend.model.persistence.crud;

import cl.pinolabs.backend.model.persistence.enitty.Region;
import org.springframework.data.repository.CrudRepository;

public interface RegionCrud extends CrudRepository<Region, Integer> {

}
