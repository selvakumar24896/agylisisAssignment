package com.agilysis.assignment.dal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agilysis.assignment.model.Friend;

@Repository
public interface AppRepo extends MongoRepository<Friend,String> {
	
}
