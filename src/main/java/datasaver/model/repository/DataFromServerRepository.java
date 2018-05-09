package datasaver.model.repository;

import datasaver.model.entity.DataFromServer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataFromServerRepository extends JpaRepository<DataFromServer, String> {
}
