package com.cibil.manager;

import java.net.InetAddress;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cibil.service.AppContext;

public class AuditManager implements IAuditManager {

	public DataSource dataSource;
	public static AuditManager instance;
	private Log log = LogFactory.getLog(AuditManager.class);

	public static AuditManager getInstance() {
		if (instance == null) {
			instance = (AuditManager) AppContext.getInstance().getBean(
					"auditManager");
		}
		return instance;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean addAudit(String UserId, String moduleId, String eventId,
			String Remarks) {
		// TODO Auto-generated method stub
		log.info("Method:addAudit() of AuditManager.java");
		Date today = new Date();
		Connection conn = null;
		boolean result = false;
		try {
			String ipAddress = InetAddress.getLocalHost().getHostAddress();
			conn = dataSource.getConnection();
			CallableStatement cs = conn
					.prepareCall("{call PRC_INSERT_AUDITEVENT(?,?,?,?,?,?)}");
			cs.setString(1, UserId);
			cs.setString(2, moduleId);
			cs.setTimestamp(3, new Timestamp(today.getTime()));
			cs.setString(4, eventId);
			cs.setString(5, ipAddress);
			cs.setString(6, Remarks);
			result = cs.execute();
			return result;
		} catch (Exception e) {
			log.error(e.toString());
		}
		return false;
	}

}
