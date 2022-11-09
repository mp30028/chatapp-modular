package com.zonesoft.chats.models;

import static com.zonesoft.utils.ToStringHelpers.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "conversations")
public class Conversation {
	@Id private String id = null;
	
	private List<Participant> participants = new ArrayList<>(); 
	private List<Message> messages = new ArrayList<>();
	
	public Conversation() {
		super();
	}
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<Participant> getParticipants() {
		return participants;
	}
	
	
	public List<Message> getMessages() {
		return messages;
	}
	
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		lb(sb);
			n(sb); t(sb); v(sb, "\"id\": "); qv(sb,this.id); c(sb);
			n(sb); t(sb); v(sb, "\"participants\": "); v(sb,this.getParticipants()); c(sb);
			n(sb); t(sb); v(sb, "\"messages\": "); v(sb,this.getMessages());
			n(sb);
		rb(sb);	
		return sb.toString();
	}
	
	public class Message {
		
		private final String message;
		private final Participant sender;
		private final OffsetDateTime sentTime;
		
		public Message(Participant sender, String message) {
			super();
			this.sender = sender;
			this.message = message;
			this.sentTime = OffsetDateTime.now();
		}
		
		public String getMessage() {
			return message;
		}


		public Participant getSender() {
			return sender;
		}


		public OffsetDateTime getSentTime() {
			return sentTime;
		}

		
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			lb(sb);
				n(sb); t(sb); v(sb, "\"message\": "); qv(sb,this.message);  c(sb);
				n(sb); t(sb); v(sb, "\"sender\": "); v(sb,this.sender);  c(sb);
				n(sb); t(sb); v(sb, "\"sentTime\": "); qv(sb,this.sentTime); 
			rb(sb);	
			return sb.toString();
		}
	}

	public class Participant {
		
		@DBRef private final String personId;
		private final OffsetDateTime participationStart;
		private OffsetDateTime participationEnd = null;
		
		
		public Participant(String personId) {
			super();
			this.personId = personId;
			this.participationStart = OffsetDateTime.now();
		}
		
		public Participant(String personId, OffsetDateTime participationStart) {
			super();
			this.personId = personId;
			this.participationStart = participationStart;
		}
			
		public String getPersonId() {
			return personId;
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
			StringBuffer sb = new StringBuffer();
			lb(sb);
				n(sb); t(sb); v(sb, "\"personId\": "); v(sb,this.personId);  c(sb);
				n(sb); t(sb); v(sb, "\"participationStart\": "); qv(sb,this.participationStart);  c(sb);
				n(sb); t(sb); v(sb, "\"participationEnd\": "); qv(sb,this.participationEnd); 
				n(sb);
			rb(sb);	
			return sb.toString();
		}
		
	}

	
}
