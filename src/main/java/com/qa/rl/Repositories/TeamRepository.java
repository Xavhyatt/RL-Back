package com.qa.rl.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.rl.Model.LeagueModel;
import com.qa.rl.Model.TeamModel;

@Repository
public interface TeamRepository extends JpaRepository<TeamModel, Long> {

	Page<TeamModel> findByLeagueId(LeagueModel leagueId, Pageable pageable);

	Page<TeamModel> findByName(String name, Pageable pageable);

	Page<TeamModel> findByLocation(String location, Pageable pageable);

	Page<TeamModel> findByTeamId(Long teamId, Pageable pageable);

}
