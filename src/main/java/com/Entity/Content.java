package com.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "content")
@Data
public class Content {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//next file types add cheyali
	private String moduleTitle;
	private String moduleDescription;
	@JsonProperty("isAccessFree")
	private boolean isAccessFree;
	private String link;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
}
