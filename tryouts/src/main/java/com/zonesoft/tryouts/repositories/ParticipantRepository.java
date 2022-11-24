package com.zonesoft.tryouts.repositories;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.zonesoft.tryouts.models.Participant;

@Repository
public interface ParticipantRepository extends ReactiveMongoRepository<Participant, String> {
	
}
