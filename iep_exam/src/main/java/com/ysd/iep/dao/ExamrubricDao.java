package com.ysd.iep.dao;

import com.ysd.iep.entity.Examrubric;
import com.ysd.iep.entity.Rubric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author gaozhongye
 * @date 2018/12/7
 * 考试题干dao
 */
public interface ExamrubricDao extends JpaRepository<Examrubric, String>, JpaSpecificationExecutor<Examrubric> {
}
