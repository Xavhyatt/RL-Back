package com.qa.rl.Model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "team")
@EntityListeners(AuditingEntityListener.class)
public class TeamModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teamId;

	@NotBlank
	private String name;

	@NotBlank
	private String location;

	@NotBlank
	private String groundName;

	@NotBlank
	private String colours;
	
	@NotBlank
	private String founded;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "leagueId", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private LeagueModel leagueId;

	public TeamModel() {

	}

	public TeamModel(Long teamId, @NotBlank String name, @NotBlank String location, @NotBlank String groundName,
			@NotBlank String colours, @NotBlank String founded, LeagueModel leagueId) {

		this.teamId = teamId;
		this.name = name;
		this.location = location;
		this.groundName = groundName;
		this.colours = colours;
		this.founded = founded;
		this.leagueId = leagueId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGroundName() {
		return groundName;
	}

	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}

	public String getColours() {
		return colours;
	}

	public void setColours(String colours) {
		this.colours = colours;
	}

	public String getFounded() {
		return founded;
	}

	public void setFounded(String founded) {
		this.founded = founded;
	}

	public LeagueModel getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(LeagueModel leagueId) {
		this.leagueId = leagueId;
	}



	
}
