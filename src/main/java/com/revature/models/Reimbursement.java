package com.revature.models;

import java.sql.Timestamp;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {
	
	private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    private int statusId;
    private int typeId;
    private String type;
 
    //private Double amount; 
    public Reimbursement() {
        super();
    }

   

	public Reimbursement(Timestamp submitted, Timestamp resolved, String description, int statusId,
			int typeId) {
		super();
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.statusId = statusId;
		this.typeId = typeId;
	
	}

	

	/**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    public Reimbursement(int id, Status status, User author, User resolver, double amount) {
        super(id, status, author, resolver, amount);
    }



	public Timestamp getSubmitted() {
		return submitted;
	}



	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}



	public Timestamp getResolved() {
		return resolved;
	}



	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getStatusId() {
		return statusId;
	}



	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}



	public int getTypeId() {
		return typeId;
	}



	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(description, resolved, statusId, submitted, type, typeId);
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Objects.equals(description, other.description) && Objects.equals(resolved, other.resolved)
				&& statusId == other.statusId && Objects.equals(submitted, other.submitted)
				&& Objects.equals(type, other.type) && typeId == other.typeId;
	}



	@Override
	public String toString() {
		return "Reimbursement [submitted=" + submitted + ", resolved=" + resolved + ", description=" + description
				+ ", statusId=" + statusId + ", typeId=" + typeId + ", type=" + type + "]";
	}
}




	