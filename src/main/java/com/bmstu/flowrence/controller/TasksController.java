package com.bmstu.flowrence.controller;

import com.bmstu.flowrence.dto.request.ListDashboardTasksRequestDto;
import com.bmstu.flowrence.dto.request.personal.TaskCreateRequestDto;
import com.bmstu.flowrence.dto.request.personal.TaskRetrieveRequestDto;
import com.bmstu.flowrence.dto.request.personal.TaskUpdateRequestDto;
import com.bmstu.flowrence.dto.response.TaskInfoDto;
import com.bmstu.flowrence.entity.Task;
import com.bmstu.flowrence.mapper.dto.TaskToTaskInfoMapper;
import com.bmstu.flowrence.service.TasksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TasksService tasksService;
    private final TaskToTaskInfoMapper taskToTaskInfoMapper;

    // maybe some kind of annotation-based wrapper for error handling
    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskInfoDto> createTask(@RequestBody TaskCreateRequestDto request) {
        log.debug("Creating task with header {}", request.getHeader());
        try {
            Task task = tasksService.createTask(request);
            TaskInfoDto responseDto = taskToTaskInfoMapper.mapSourceToDestination(task);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error processing request", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/update", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskInfoDto> updateTask(@RequestBody TaskUpdateRequestDto request) {
        log.debug("Updating task with header {}", request.getHeader());
        try {
            Task task = tasksService.updateTask(request);
            TaskInfoDto responseDto = taskToTaskInfoMapper.mapSourceToDestination(task);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error processing request", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/reported", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskInfoDto>> listReportedTasks(@RequestBody TaskRetrieveRequestDto request) {
        log.debug("Listing tasks with reporter {}", request.getUserUuid());
        try {
            List<Task> tasks = tasksService.listReportedTasks(request.getUserUuid());
            List<TaskInfoDto> responseDto = taskToTaskInfoMapper.mapSourceToDestination(tasks);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error processing request", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/assigned", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskInfoDto>> listAssignedTasks(@RequestBody TaskRetrieveRequestDto request) {
        log.debug("Listing tasks with assignee {}", request.getUserUuid());
        try {
            List<Task> tasks = tasksService.listAssignedTasks(request.getUserUuid());
            List<TaskInfoDto> responseDto = taskToTaskInfoMapper.mapSourceToDestination(tasks);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error processing request", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/dashboard", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskInfoDto>> listTasksFromDashboard(@RequestBody ListDashboardTasksRequestDto request) {
        log.debug("Listing tasks for dashboard {}", request.getDashboardUuid());
        try {
            List<Task> tasks = tasksService.listDashboardTasks(request.getDashboardUuid());
            List<TaskInfoDto> responseDto = taskToTaskInfoMapper.mapSourceToDestination(tasks);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error processing request", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
