package edu.esprit.domain.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Order
 * 
 */
@Entity
public class Order implements Serializable {

	private int id;
	private NonResidentCorporate nonResidentCorporate;
	private static final long serialVersionUID = 1L;

	public Order() {
		super();
	}

	public Order(int id, NonResidentCorporate nonResidentCorporate) {
		super();
		this.id = id;
		this.nonResidentCorporate = nonResidentCorporate;
	}

	@ManyToOne
	public NonResidentCorporate getNonResidentCorporate() {
		return nonResidentCorporate;
	}

	public void setNonResidentCorporate(
			NonResidentCorporate nonResidentCorporate) {
		this.nonResidentCorporate = nonResidentCorporate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
