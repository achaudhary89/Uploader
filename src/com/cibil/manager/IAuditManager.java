package com.cibil.manager;

public interface IAuditManager {
	public boolean addAudit(String UserId, String moduleID, String eventId,
			String Remarks);

}
