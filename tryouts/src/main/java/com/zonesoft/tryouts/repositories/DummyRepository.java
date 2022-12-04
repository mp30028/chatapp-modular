package com.zonesoft.tryouts.repositories;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.zonesoft.tryouts.models.Dummy;
import reactor.core.publisher.Flux;

@Repository
public interface DummyRepository extends ReactiveMongoRepository<Dummy, String> {
	
	public Flux<Dummy> findByMoniker(String moniker);

}
