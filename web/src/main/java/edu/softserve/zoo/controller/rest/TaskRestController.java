package edu.softserve.zoo.controller.rest;


import edu.softserve.zoo.annotation.DocsClassDescription;
import edu.softserve.zoo.annotation.DocsParamDescription;
import edu.softserve.zoo.annotation.DocsTest;
import edu.softserve.zoo.dto.TaskDto;
import edu.softserve.zoo.model.Task;
import edu.softserve.zoo.service.Service;
import edu.softserve.zoo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static edu.softserve.zoo.controller.rest.Routes.TASKS;

/**
 * RestController implementation for {@link Task} entity.
 */

@DocsClassDescription("Task resource")
@RestController
@RequestMapping(TASKS)
public class TaskRestController extends AbstractRestController<TaskDto, Task> {

    @Autowired
    private TaskService taskService;

    @DocsTest(pathParameters = "1")
    @RequestMapping(method = RequestMethod.GET, params = "assigneeId")
    public List<TaskDto> tasksGetAllByAssignee(@RequestParam("assigneeId") @DocsParamDescription("The id of assignee") Long assigneeId) {
        List<Task> taskList = taskService.taskGetAllByAssigneeId(assigneeId);
        return converter.convertToDto(taskList);
    }

    @DocsTest(pathParameters = "1")
    @RequestMapping(method = RequestMethod.GET, params = "assignerId")
    public List<TaskDto> tasksGetAllByAssigner(@RequestParam("assignerId") @DocsParamDescription("The id of assigner") Long assignerId) {
        List<Task> taskList = taskService.taskGetAllByAssignerId(assignerId);
        return converter.convertToDto(taskList);
    }

    @DocsTest
    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<TaskDto> getAll() {
        return super.getAll();
    }

    @DocsTest
    @Override
    @RequestMapping(method = RequestMethod.POST)
    public TaskDto create(@Valid @RequestBody TaskDto dto){
        return super.create(dto);
    }

    @Override
    protected Service<Task> getService() {
        return taskService;
    }
}
