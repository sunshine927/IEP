package com.ysd.iep.controller;

import com.ysd.iep.entity.Student;
import com.ysd.iep.entity.StudentRecord;
import com.ysd.iep.entity.dto.Chapters;
import com.ysd.iep.feign.TeacherFeign;
import com.ysd.iep.service.StudentService;
import com.ysd.iep.service.StudyService;
import com.ysd.iep.service.TeacherService;
import com.ysd.iep.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/study")
public class StudyController {
    @Autowired
    private StudyService studyService;
    @Autowired(required = false)
    private TeacherFeign teacherFeign;
    @Autowired(required = false)
    private TeacherService teacherService;
    @Autowired
    private StudentService student;

    /**
     * 查询当前学生某门课的最新学习进度
     */
    @ApiOperation(value = " 查询课程学习进度")
    @GetMapping("/queryRecord")
    public Object queryStudentRecord(@RequestParam("cid") Integer cid, @RequestParam("sid") String sid) {
        StudentRecord studentRecord = studyService.queryStudentRecord(cid, sid);
        if (studentRecord == null) {
            //无学习记录
            return new Result(false, "没有开始学习");
        } else {
            //有记录 得到章节id再查询得到章节信息
            studentRecord.getChaid();

            return "";
        }
    }

    /**
     * 根据课程id 查询课程公告
     */
    @ApiOperation(value = " 根据课程id查询课程公告信息")
    @GetMapping("/queryNotice")
    public Result queryNotice(Integer cid) {

        return teacherService.queryNoticeByCourId(cid);
    }

    /**
     * study/queryChapter
     * 查询课程 课件
     */
    @ApiOperation(value = " 根据课程id查询课程课件")
    @GetMapping("/queryChapter")
    public Object queryChapter(Integer cid) {
        List<Chapters> list = teacherFeign.queryChapter(cid);
        return list;
    }

    /**
     * study/addChapterStudyLog
     *
     * @param studentRecord
     * @return
     */
    @ApiOperation(value = "记录学习")
    @PostMapping("/addChapterStudyLog")
    public Object addChapterStudyLog(StudentRecord studentRecord) {
        return studyService.insertChapters(studentRecord);
    }
    @ApiOperation(value = "获取用户信息（头像）")
    @GetMapping("/getphotoByIds")
    public String getphotoByIds(String id){
       return student.getphotoByIds(id).getPhoto();
    }



}
