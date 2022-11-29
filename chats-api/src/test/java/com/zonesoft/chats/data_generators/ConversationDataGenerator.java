package com.zonesoft.chats.data_generators;

import static com.zonesoft.chats.data_generators.MessageDataGenerator_DELETE.*;
import static com.zonesoft.chats.data_generators.ParticipantRecordBuilder.*;

import com.zonesoft.chats.models.Conversation;

public class ConversationDataGenerator {
	
	public static Conversation generateConversation() {
		Conversation conversation = new Conversation();
		conversation.participants().addAll(generateParticipants());
		conversation.messages().addAll(generateMessagesWithId());
		return conversation;
	}
}
