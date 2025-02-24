package com.simanglam.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.simanglam.util.GlobalStorage;

public class ActDeserializer extends StdDeserializer<Act> {
    public ActDeserializer() {
        super(Act.class);
    }

    @Override
    public Act deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Act act = new Act(node.get("channel").get("name").asText());
        GlobalStorage state = GlobalStorage.getInstance();
        node.get("messages").forEach(Node -> {
            String authorName = Node.get("author").get("name").asText();
            String content = Node.get("content").asText();
            if (!state.hasCharacter(authorName)){
                state.addCharacter(authorName);
            }
            act.addDialog(new Message(content, state.getCharacter(authorName)));
        });
        return act;
    }
    
}
