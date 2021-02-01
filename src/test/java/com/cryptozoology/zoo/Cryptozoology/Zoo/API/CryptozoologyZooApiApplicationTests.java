package com.cryptozoology.zoo.Cryptozoology.Zoo.API;

import com.cryptozoology.zoo.Cryptozoology.Zoo.API.model.Animal;
import com.cryptozoology.zoo.Cryptozoology.Zoo.API.repository.ZooRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CryptozoologyZooApiApplicationTests {

	@Autowired
	MockMvc mockMVC;

	@Autowired
	ZooRepository zooRepository;

	ObjectMapper mapper;

	@BeforeEach
	void setUp() {
		mapper = new ObjectMapper();
	}

	@Test
	public void addAnimal() throws Exception {
		Animal exAnimal = zooRepository.save(new Animal("Dog", "walking"));

		mockMVC.perform(MockMvcRequestBuilders.post("/animal")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(exAnimal))
				).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andExpect(jsonPath("$.name").value("Dog"))
				.andExpect(jsonPath("$.type").value("walking"));
	}



}
