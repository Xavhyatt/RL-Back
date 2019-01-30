package com.qa.rl.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.rl.Model.LeagueModel;

@Repository
public interface LeagueRepository extends JpaRepository<LeagueModel, Long> {

}
