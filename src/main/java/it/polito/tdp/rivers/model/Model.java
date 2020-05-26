package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {

	RiversDAO dao;
	Simulatore sim ;

	public Model() {
		dao = new RiversDAO();
		sim = new Simulatore();
	}

	public List<River> getAllRivers() {
		return dao.getAllRivers();
	}

	public String getStartDate(River fiume) {
		LocalDate best = fiume.getFlows().get(0).getDay();

		for (Flow f : fiume.getFlows())
			if (f.getDay().isBefore(best))
				best = f.getDay();

		fiume.setInizio(best);
		return best.toString();
	}

	public String getEndDate(River fiume) {
		LocalDate best = fiume.getFlows().get(0).getDay();

		for (Flow f : fiume.getFlows())
			if (f.getDay().isAfter(best))
				best = f.getDay();
		
		fiume.setFine(best);
		return best.toString();
	}

	public String getNumMeasurements(River fiume) {
		return Integer.toString(fiume.getFlows().size());
	}

	public String getFMed(River fiume) {
		String avg = Double.toString(fiume.getFlowAvg());
		return avg;
	}

	public void loadData(River fiume) {
		dao.setFlows(fiume);
		
	}

	public void doSimulazione(River fiume, double k) {
		
		sim.init(fiume, k);
		sim.run();
		
		
	}
	
	public String getcMed() {
		return Double.toString(sim.getcMed());
	}
	
	public String getNo() {
		return Integer.toString(sim.getNumGiorniNo());
	}
	
	

}
