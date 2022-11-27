package com.zonesoft.participants.models;

import static com.zonesoft.utils.helpers.ToStringBuilder.*;
import com.zonesoft.utils.helpers.ToStringBuilder;
import java.time.OffsetDateTime;

public class Participant {
	private String id;
	private final OffsetDateTime participationStart;
	private OffsetDateTime participationEnd = null;
	
	
	public Participant() {
		super();
		this.participationStart = OffsetDateTime.now();
	}
	
	public Participant(OffsetDateTime participationStart) {
		super();
		this.participationStart = participationStart;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		
	
	public OffsetDateTime getParticipationStart() {
		return participationStart;
	}
	
	public OffsetDateTime getParticipationEnd() {
		return participationEnd;
	}
	
	public void setParticipationEnd(OffsetDateTime participationEnd) {
		this.participationEnd = participationEnd;
	}

	@Override
	public String toString() {
		return new ToStringBuilder()
			.build(
				lBrace, newline,
					indent, key("participant-id"), 		value(this.id), 					comma, newline,
					indent, key("participation-start"), value(this.participationStart), 	comma, newline,
					indent, key("participation-end"), 	value(this.participationEnd), 		newline,
				rBrace
			);
	}

	
}