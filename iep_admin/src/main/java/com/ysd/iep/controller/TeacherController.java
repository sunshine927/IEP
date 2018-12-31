package com.ysd.iep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ysd.iep.entity.dto.Result;
import com.ysd.iep.entity.dto.UsersStuDTO;
import com.ysd.iep.entity.dto.UsersTeaDTO;
import com.ysd.iep.entity.query.TeacherQuery;
import com.ysd.iep.entity.query.UsersRoleQuery;
import com.ysd.iep.entity.vo.PagingResult;
import com.ysd.iep.service.TeacherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="/teacher", tags="教师API")
@RestController
@RequestMapping("/teacher")
public class TeacherController {

	 @Autowired
	 private TeacherService teaService;
	 
	@GetMapping("/teacherQuery")
    @ApiOperation("根据教师分页查询")
    public  PagingResult<UsersTeaDTO> getTeacher(TeacherQuery teacherQuery){
        System.out.println(teacherQuery);
        return teaService.get(teacherQuery);
    }
	
	@PostMapping("/addTeacher")
    @ApiOperation("添加教师")
	public Result addTeacher(@RequestBody UsersTeaDTO userteaDTO) {
		System.out.println("教师>>>>>>"+userteaDTO);
		teaService.addTeacher(userteaDTO);
		return new Result(true,"新增成功");
	}
	
	@PutMapping("/{id}")
    public Result update(@PathVariable("id") String id,@RequestBody UsersTeaDTO userTeaDTO){
        try {
        	userTeaDTO.setId(id);
            teaService.update(userTeaDTO);
        } catch (DataIntegrityViolationException e) {
            return new Result<String>(false,e.getMessage());
        }
        return new Result(true,"修改成功");
    }
	
	@DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable("id") String id){
		teaService.delete(id);
        return new Result<String>(true,"删除成功");
    }
	
}
