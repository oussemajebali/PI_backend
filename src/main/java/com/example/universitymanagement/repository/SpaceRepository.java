package com.example.universitymanagement.repository;
import com.example.universitymanagement.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SpaceRepository extends JpaRepository<Space, Integer>
{
    List<Space> findByAvailable(boolean available);
}