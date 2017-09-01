package com.organization.project.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.organization.project.bean.Todo;
import com.organization.project.bean.TodoNotFoundException;
import com.organization.project.service.TodoService;

@RestController
public class TodoController {
	@Autowired
	private TodoService todoService;

	@RequestMapping("/users/{name}/todos")
	public List<Todo> retrieveTodos(
			@PathVariable String name) {
		return todoService.retrieveTodos(name);
	}

	@RequestMapping("/users/{name}/todos/{id}")
	public Resource<Todo> retrieveTodo(
			@PathVariable String name,
			@PathVariable int id) {
		Todo todo = todoService.retrieveTodo(id);
		if (todo == null)
			throw new TodoNotFoundException("Something");
		Resource<Todo> todoResource = new Resource<Todo>(
				todo);
		return todoResource;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/users/{name}/todos")
	ResponseEntity<?> add(@PathVariable String name,
			@Valid @RequestBody Todo todo) {

		Todo createdTodo = todoService.addTodo(name,
				todo.getDesc(), todo.getTargetDate(),
				todo.isDone());

		if (createdTodo == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdTodo.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
}