package com.todoList.Project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.todoList.Project.entity.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProjectApplicationTests {
@Autowired
private WebTestClient webTestClient;

	@Test
	void testCreateTodoSucess() {
		var todo = new Todo("Todo 1", "desc todo 1", false, 1);

		webTestClient
			.post()
			.uri("/todos")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(	7)// toda vez que roda o teste incrementa a lista
			.jsonPath("$[0].nome").isEqualTo(todo.getNome())
			.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
			.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
			.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	void testCreateTodoFailure() {
		webTestClient
			.post()
			.uri("/todos")
			.bodyValue(
				new Todo("", "", false, 0)
			).exchange()
			.expectStatus().isBadRequest();
	}

}
