package com.berta.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(
			name = "event.find.by.name",
			query = "SELECT e FROM Event e WHERE e.eventName = :name"
	),
})


@Entity
@Table(name = "T_EVENT_DEF")
public class Event {
	private String ceId;
	private String eventName;
	private Long treiberId;
	private Long seqNr;
	
	@Id
	@Column(name="CEID")
	public String getCeId() {
		return ceId;
	}
	public void setCeId(String ceId) {
		this.ceId = ceId;
	}
	@Column(name="EVENT_NAME")
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	@Column(name="TREIBER_ID")
	public Long getTreiberId() {
		return treiberId;
	}
	public void setTreiberId(Long treiberId) {
		this.treiberId = treiberId;
	}
	@Column(name="SEQ_NR")
	public Long getSeqNr() {
		return seqNr;
	}
	public void setSeqNr(Long seqNr) {
		this.seqNr = seqNr;
	}
}
