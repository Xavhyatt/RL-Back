package com.qa.rl.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.rl.Model.PlayerModel;
import com.qa.rl.Model.TeamModel;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, Long> {

	Page<PlayerModel> findByTeamId(TeamModel teamId, Pageable pageable);





}
