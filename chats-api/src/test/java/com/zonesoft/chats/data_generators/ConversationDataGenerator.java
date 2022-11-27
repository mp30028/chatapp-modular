package com.zonesoft.chats.data_generators;

import static com.zonesoft.chats.data_generators.MessageDataGenerator.*;
import static com.zonesoft.chats.data_generators.ParticipantDataGenerator.*;

import com.zonesoft.chats.models.Conversation;

public class ConversationDataGenerator {
	
	public static Conversation generateConversation() {
		Conversation conversation = new Conversation();
		conversation.participants().addAll(generateParticipants());
		conversation.messages().addAll(generateMessagesWithId());
		return conversation;
	}
}
