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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.map;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
		zooRepository.deleteAll();
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

	@Test
	public void getAllAnimal() throws Exception {
		zooRepository.save(new Animal("Dog", "walking"));
		zooRepository.save(new Animal("Cat", "walking"));

		String expectedAnimalList = mapper.writeValueAsString(zooRepository.findAll());
		String animalList = mockMVC.perform(MockMvcRequestBuilders.get("/animalList"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		assertThat(animalList).isEqualTo(expectedAnimalList);
	}

	@Test
	public void  checkAndSetAnimalMood() throws Exception {
		Animal animal1 = zooRepository.save(new Animal("Dog", "walking", Boolean.FALSE));
		Animal animal2 = zooRepository.save(new Animal("Cat", "walking", Boolean.TRUE));

		mockMVC.perform(MockMvcRequestBuilders.put("/updateMood/{id}", animal1.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.mood").value(Boolean.TRUE));

		mockMVC.perform(MockMvcRequestBuilders.put("/updateMood/" + animal2.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.mood").value(Boolean.TRUE));
	}

	@Test
	public void searchAnimals() throws Exception {
		Animal animal1 = zooRepository.save(new Animal("Lion", "walking", Boolean.FALSE));
		Animal animal2 = zooRepository.save(new Animal("Crow", "flying", Boolean.FALSE));
		Animal animal3 = zooRepository.save(new Animal("Deer", "walking", Boolean.FALSE));

		List<Animal> animalList = new ArrayList<>();
		List<Animal> expectedAnimalList = new ArrayList<>();

		animalList.add(animal1);
		animalList.add(animal2);
		animalList.add(animal3);

		for(Animal animal : animalList){
			if(animal.getType().equals("walking") && animal.isMood() == Boolean.FALSE ){
				expectedAnimalList.add(animal);
			}
		}

		String expectedList = mapper.writeValueAsString(expectedAnimalList);

		String actualAnimalList = mockMVC.perform(get("/animals/{mood}/{type}", Boolean.FALSE, "walking"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		assertThat(actualAnimalList).isEqualTo(expectedList);
	}
}





