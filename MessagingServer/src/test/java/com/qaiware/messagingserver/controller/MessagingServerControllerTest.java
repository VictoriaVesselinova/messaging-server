package com.qaiware.messagingserver.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.qaiware.messagingserver.model.helper.MessageType;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessagingServerControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void saveTextMessageWithRightLength() throws Exception {

		String bodyContent = "payload_123";

		mvc.perform(MockMvcRequestBuilders.post("/messages/" + MessageType.send_text).content(bodyContent)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(content().string(equalTo("")));
	}

	@Test
	public void saveTextMessageWithWrongtLength() throws Exception {

		String bodyContent = "payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123payload_123";

		mvc.perform(MockMvcRequestBuilders.post("/messages/" + MessageType.send_text).content(bodyContent)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isPayloadTooLarge())
				.andExpect(content().string(equalTo("")));
	}

	@Test
	public void saveEmotionMessageWithNumbersAndWithRightLength() throws Exception {
		String bodyContent = "payload1";

		mvc.perform(MockMvcRequestBuilders.post("/messages/" + MessageType.send_emotion).content(bodyContent)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isPayloadTooLarge())
				.andExpect(content().string(equalTo("")));
	}

	@Test
	public void saveEmotionMessageWithNumbersAndWithWrongLength() throws Exception {
		String bodyContent = "payload1payload1payload1payload1";

		mvc.perform(MockMvcRequestBuilders.post("/messages/" + MessageType.send_emotion).content(bodyContent)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isPayloadTooLarge())
				.andExpect(content().string(equalTo("")));
	}

	@Test
	public void saveEmotionMessageWithoutNumbersAndWithRightLength() throws Exception {
		String bodyContent = "payload";

		mvc.perform(MockMvcRequestBuilders.post("/messages/" + MessageType.send_emotion).content(bodyContent)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(content().string(equalTo("")));
	}

	@Test
	public void saveEmotionMessageWithoutNumbersAndWithWrongLength() throws Exception {
		String bodyContent = "payloadpayloadpayloadpayloadpayload";

		mvc.perform(MockMvcRequestBuilders.post("/messages/" + MessageType.send_emotion).content(bodyContent)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isPayloadTooLarge())
				.andExpect(content().string(equalTo("")));

		bodyContent = "p";

		mvc.perform(MockMvcRequestBuilders.post("/messages/" + MessageType.send_emotion).content(bodyContent)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isPayloadTooLarge())
				.andExpect(content().string(equalTo("")));
	}
}
