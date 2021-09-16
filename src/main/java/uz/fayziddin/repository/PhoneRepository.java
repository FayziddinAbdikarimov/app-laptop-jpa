package uz.fayziddin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.fayziddin.entity.Phone;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    List<Phone> findAllByModelContainingIgnoreCase(String model);
}
