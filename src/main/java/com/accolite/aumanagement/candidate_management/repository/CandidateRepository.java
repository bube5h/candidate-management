package com.accolite.aumanagement.candidate_management.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.accolite.aumanagement.candidate_management.model.Candidate;
import com.accolite.aumanagement.candidate_management.model.mapper.CandidateAllDetailsMapper;

@Repository
public class CandidateRepository 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Candidate> getAllCandidate()
	{	
		String CANDIDATES_WITH_ALL_DETAILS = "SELECT candidates.empid,candidates.firstname,candidates.lastname,candidates.instituteid,institutes.institute,emp_skills.skillid,skills.skill,candidates.locationid,locations.location,candidates.joiningdate,candidates.jobdescriptionid,jobdescriptions.jobdescription,candidates.feedback,candidates.contactnumber,candidates.email FROM candidates LEFT JOIN emp_skills ON candidates.empid  = emp_skills.empid LEFT JOIN skills ON emp_skills.skillid = skills.skillid LEFT JOIN institutes ON candidates.instituteid = institutes.instituteid LEFT JOIN locations ON candidates.locationid = locations.locationid LEFT JOIN jobdescriptions ON candidates.jobdescriptionid = jobdescriptions.jobdescriptionid;";
		
		return jdbcTemplate.query(CANDIDATES_WITH_ALL_DETAILS, new CandidateAllDetailsMapper());
	}
	


	public List<Candidate> getCandidateByEmpId(String empid)
	{
		String CANDIDATE_BY_ID = "SELECT candidates.empid,candidates.firstname,candidates.lastname,candidates.instituteid,institutes.institute,emp_skills.skillid,skills.skill,candidates.locationid,locations.location,candidates.joiningdate,candidates.jobdescriptionid,jobdescriptions.jobdescription,candidates.feedback,candidates.contactnumber,candidates.email FROM candidates LEFT JOIN emp_skills ON candidates.empid  = emp_skills.empid LEFT JOIN skills ON emp_skills.skillid = skills.skillid LEFT JOIN institutes ON candidates.instituteid = institutes.instituteid LEFT JOIN locations ON candidates.locationid = locations.locationid LEFT JOIN jobdescriptions ON candidates.jobdescriptionid = jobdescriptions.jobdescriptionid where candidates.empid = '"+empid+"';";

		try {
			return jdbcTemplate.query(CANDIDATE_BY_ID, new CandidateAllDetailsMapper());
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	public List<Candidate> getCandidateByLocation(String location)
	{
		String CANDIDATE_BY_LOCATION = "SELECT candidates.empid,candidates.firstname,candidates.lastname,candidates.instituteid,institutes.institute,emp_skills.skillid,skills.skill,candidates.locationid,locations.location,candidates.joiningdate,candidates.jobdescriptionid,jobdescriptions.jobdescription,candidates.feedback,candidates.contactnumber,candidates.email FROM candidates LEFT JOIN emp_skills ON candidates.empid  = emp_skills.empid LEFT JOIN skills ON emp_skills.skillid = skills.skillid LEFT JOIN institutes ON candidates.instituteid = institutes.instituteid LEFT JOIN locations ON candidates.locationid = locations.locationid LEFT JOIN jobdescriptions ON candidates.jobdescriptionid = jobdescriptions.jobdescriptionid where locations.location= '"+location+"';";
		
		try {
			return jdbcTemplate.query(CANDIDATE_BY_LOCATION, new CandidateAllDetailsMapper());
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	
	public List<Candidate> getCandidateByInstitute(String institute)
	{
		String CANDIDATE_BY_INSTITUTE = "SELECT candidates.empid,candidates.firstname,candidates.lastname,candidates.instituteid,institutes.institute,emp_skills.skillid,skills.skill,candidates.locationid,locations.location,candidates.joiningdate,candidates.jobdescriptionid,jobdescriptions.jobdescription,candidates.feedback,candidates.contactnumber,candidates.email FROM candidates LEFT JOIN emp_skills ON candidates.empid  = emp_skills.empid LEFT JOIN skills ON emp_skills.skillid = skills.skillid LEFT JOIN institutes ON candidates.instituteid = institutes.instituteid LEFT JOIN locations ON candidates.locationid = locations.locationid LEFT JOIN jobdescriptions ON candidates.jobdescriptionid = jobdescriptions.jobdescriptionid where institutes.institute= '"+institute+"';";
		
		try {
			return jdbcTemplate.query(CANDIDATE_BY_INSTITUTE, new CandidateAllDetailsMapper());
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	public List<Candidate> getCandidateByJobDescription(String jobdescription)
	{
		String CANDIDATE_BY_JOBDESCRIPTION = "SELECT candidates.empid,candidates.firstname,candidates.lastname,candidates.instituteid,institutes.institute,emp_skills.skillid,skills.skill,candidates.locationid,locations.location,candidates.joiningdate,candidates.jobdescriptionid,jobdescriptions.jobdescription,candidates.feedback,candidates.contactnumber,candidates.email FROM candidates LEFT JOIN emp_skills ON candidates.empid  = emp_skills.empid LEFT JOIN skills ON emp_skills.skillid = skills.skillid LEFT JOIN institutes ON candidates.instituteid = institutes.instituteid LEFT JOIN locations ON candidates.locationid = locations.locationid LEFT JOIN jobdescriptions ON candidates.jobdescriptionid = jobdescriptions.jobdescriptionid where jobdescriptions.jobdescription= '"+jobdescription+"';";
		
		try {
			return jdbcTemplate.query(CANDIDATE_BY_JOBDESCRIPTION, new CandidateAllDetailsMapper());
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	public List<Candidate> getCandidateBySkill(String skill)
	{
		String CANDIDATE_BY_SKILL = "SELECT candidates.empid,candidates.firstname,candidates.lastname,candidates.instituteid,institutes.institute,emp_skills.skillid,skills.skill,candidates.locationid,locations.location,candidates.joiningdate,candidates.jobdescriptionid,jobdescriptions.jobdescription,candidates.feedback,candidates.contactnumber,candidates.email FROM candidates LEFT JOIN emp_skills ON candidates.empid  = emp_skills.empid LEFT JOIN skills ON emp_skills.skillid = skills.skillid LEFT JOIN institutes ON candidates.instituteid = institutes.instituteid LEFT JOIN locations ON candidates.locationid = locations.locationid LEFT JOIN jobdescriptions ON candidates.jobdescriptionid = jobdescriptions.jobdescriptionid where skills.skill= '"+skill+"';";
		
		try {
			return jdbcTemplate.query(CANDIDATE_BY_SKILL, new CandidateAllDetailsMapper());
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	
	public boolean saveCandidate(Candidate candidate)
	{
		String INSERT_INTO_CANDIDATE = "INSERT INTO candidates VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		return jdbcTemplate.execute(INSERT_INTO_CANDIDATE,new PreparedStatementCallback<Boolean>() {
		@Override
		public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException,DataAccessException
			{
				ps.setString(1, candidate.getEmpid());
				ps.setString(2, candidate.getFirstname());
				ps.setString(3, candidate.getLastname());
				ps.setInt(4, candidate.getInstituteid());
				ps.setInt(5, candidate.getLocationid());
				ps.setDate(6, candidate.getJoiningdate());
				ps.setInt(7, candidate.getJobdescriptionid());
				ps.setString(8, candidate.getFeedback());
				ps.setString(9, candidate.getContactnumber());
				ps.setString(10, candidate.getEmail());
				return ps.execute();
			}
		});
		
	}
	
	public boolean updateCandidate(Candidate candidate)
	{
		String UPDATE_INTO_CANDIDATE = "UPDATE candidates SET firstname = ?, lastname = ?, instituteid = ?, locationid = ?, joiningdate = ?, jobdescriptionid = ?, feedback = ?, contactnumber = ?, email = ? WHERE empid = ?;";
		return jdbcTemplate.execute(UPDATE_INTO_CANDIDATE,new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException,DataAccessException
				{
					ps.setString(1, candidate.getFirstname());
					ps.setString(2, candidate.getLastname());
					ps.setInt(3, candidate.getInstituteid());
					ps.setInt(4, candidate.getLocationid());
					ps.setDate(5, candidate.getJoiningdate());
					ps.setInt(6, candidate.getJobdescriptionid());
					ps.setString(7, candidate.getFeedback());
					ps.setString(8, candidate.getContactnumber());
					ps.setString(9, candidate.getEmail());
					ps.setString(10, candidate.getEmpid());
					return ps.execute();
				}
			});
	}
	
	public boolean deleteCandidate(Candidate candidate)
	{
		try {
			jdbcTemplate.update("delete from emp_skills where empid= ?",candidate.getEmpid());
			jdbcTemplate.update("delete from candidates where empid= ?",candidate.getEmpid());
		} catch (DataAccessException e) {
			return false;
		}
		return true;
		
	}
	
	
	
	

	
	
	
	
}
