package pro.chenggang.project.reactive.mybatis.support.generator.mapper.dynamic;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.where.WhereApplier;
import pro.chenggang.project.reactive.mybatis.support.generator.entity.model.Emp;
import pro.chenggang.project.reactive.mybatis.support.r2dbc.dynamic.CommonCountMapper;
import pro.chenggang.project.reactive.mybatis.support.r2dbc.dynamic.CommonDeleteMapper;
import pro.chenggang.project.reactive.mybatis.support.r2dbc.dynamic.CommonInsertMapper;
import pro.chenggang.project.reactive.mybatis.support.r2dbc.dynamic.CommonSelectMapper;
import pro.chenggang.project.reactive.mybatis.support.r2dbc.dynamic.CommonUpdateMapper;
import pro.chenggang.project.reactive.mybatis.support.r2dbc.dynamic.ReactiveMyBatis3Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static pro.chenggang.project.reactive.mybatis.support.generator.mapper.dynamic.EmpDynamicSqlSupport.createTime;
import static pro.chenggang.project.reactive.mybatis.support.generator.mapper.dynamic.EmpDynamicSqlSupport.deptNo;
import static pro.chenggang.project.reactive.mybatis.support.generator.mapper.dynamic.EmpDynamicSqlSupport.emp;
import static pro.chenggang.project.reactive.mybatis.support.generator.mapper.dynamic.EmpDynamicSqlSupport.empName;
import static pro.chenggang.project.reactive.mybatis.support.generator.mapper.dynamic.EmpDynamicSqlSupport.empNo;
import static pro.chenggang.project.reactive.mybatis.support.generator.mapper.dynamic.EmpDynamicSqlSupport.hireDate;
import static pro.chenggang.project.reactive.mybatis.support.generator.mapper.dynamic.EmpDynamicSqlSupport.job;
import static pro.chenggang.project.reactive.mybatis.support.generator.mapper.dynamic.EmpDynamicSqlSupport.kpi;
import static pro.chenggang.project.reactive.mybatis.support.generator.mapper.dynamic.EmpDynamicSqlSupport.manager;
import static pro.chenggang.project.reactive.mybatis.support.generator.mapper.dynamic.EmpDynamicSqlSupport.salary;

/**
 * auto generated
 * @author AutoGenerated
 */
@Mapper
public interface EmpDynamicMapper extends CommonSelectMapper, CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Emp>, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(empNo, empName, job, manager, hireDate, salary, kpi, deptNo, createTime);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys = true,keyProperty = "row.empNo",keyColumn = "emp_no")
    Mono<Integer> insert(InsertStatementProvider<Emp> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="EmpResult", value = {
        @Result(column="emp_no", property="empNo", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="emp_name", property="empName", jdbcType=JdbcType.VARCHAR),
        @Result(column="job", property="job", jdbcType=JdbcType.VARCHAR),
        @Result(column="manager", property="manager", jdbcType=JdbcType.VARCHAR),
        @Result(column="hire_date", property="hireDate", jdbcType=JdbcType.DATE),
        @Result(column="salary", property="salary", jdbcType=JdbcType.INTEGER),
        @Result(column="kpi", property="kpi", jdbcType=JdbcType.DECIMAL),
        @Result(column="dept_no", property="deptNo", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Flux<Emp> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("EmpResult")
    Mono<Emp> selectOne(SelectStatementProvider selectStatement);

    default Mono<Long> count(CountDSLCompleter completer) {
        return ReactiveMyBatis3Utils.countFrom(this::count, emp, completer);
    }

    default Mono<Integer> delete(DeleteDSLCompleter completer) {
        return ReactiveMyBatis3Utils.deleteFrom(this::delete, emp, completer);
    }

    default Mono<Integer> insert(Emp row) {
        return ReactiveMyBatis3Utils.insert(this::insert, row, emp, c ->
            c.map(empNo).toProperty("empNo")
            .map(empName).toProperty("empName")
            .map(job).toProperty("job")
            .map(manager).toProperty("manager")
            .map(hireDate).toProperty("hireDate")
            .map(salary).toProperty("salary")
            .map(kpi).toProperty("kpi")
            .map(deptNo).toProperty("deptNo")
            .map(createTime).toProperty("createTime")
        );
    }

    default Mono<Integer> insertMultiple(Collection<Emp> records) {
        return ReactiveMyBatis3Utils.insertMultiple(this::insertMultiple, records, emp, c ->
            c.map(empNo).toProperty("empNo")
            .map(empName).toProperty("empName")
            .map(job).toProperty("job")
            .map(manager).toProperty("manager")
            .map(hireDate).toProperty("hireDate")
            .map(salary).toProperty("salary")
            .map(kpi).toProperty("kpi")
            .map(deptNo).toProperty("deptNo")
            .map(createTime).toProperty("createTime")
        );
    }

    default Mono<Integer> insertSelective(Emp row) {
        return ReactiveMyBatis3Utils.insert(this::insert, row, emp, c ->
            c.map(empNo).toPropertyWhenPresent("empNo", row::getEmpNo)
            .map(empName).toPropertyWhenPresent("empName", row::getEmpName)
            .map(job).toPropertyWhenPresent("job", row::getJob)
            .map(manager).toPropertyWhenPresent("manager", row::getManager)
            .map(hireDate).toPropertyWhenPresent("hireDate", row::getHireDate)
            .map(salary).toPropertyWhenPresent("salary", row::getSalary)
            .map(kpi).toPropertyWhenPresent("kpi", row::getKpi)
            .map(deptNo).toPropertyWhenPresent("deptNo", row::getDeptNo)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
        );
    }

    default Mono<Emp> selectOne(SelectDSLCompleter completer) {
        return ReactiveMyBatis3Utils.selectOne(this::selectOne, selectList, emp, completer);
    }

    default Flux<Emp> select(SelectDSLCompleter completer) {
        return ReactiveMyBatis3Utils.selectList(this::selectMany, selectList, emp, completer);
    }

    default Flux<Emp> selectDistinct(SelectDSLCompleter completer) {
        return ReactiveMyBatis3Utils.selectDistinct(this::selectMany, selectList, emp, completer);
    }

    default Mono<Integer> update(UpdateDSLCompleter completer) {
        return ReactiveMyBatis3Utils.update(this::update, emp, completer);
    }

    default Mono<Integer> updateSelectiveByPrimaryKey(Emp row) {
        return update(c ->
            c.set(empName).equalToWhenPresent(row::getEmpName)
            .set(job).equalToWhenPresent(row::getJob)
            .set(manager).equalToWhenPresent(row::getManager)
            .set(hireDate).equalToWhenPresent(row::getHireDate)
            .set(salary).equalToWhenPresent(row::getSalary)
            .set(kpi).equalToWhenPresent(row::getKpi)
            .set(deptNo).equalToWhenPresent(row::getDeptNo)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .where(empNo, isEqualTo(row::getEmpNo))
        );
    }

    default Mono<Integer> updateAllByPrimaryKey(Emp row) {
        return update(c ->
            c.set(empName).equalToWhenPresent(row::getEmpName)
            .set(job).equalToWhenPresent(row::getJob)
            .set(manager).equalTo(row::getManager)
            .set(hireDate).equalToWhenPresent(row::getHireDate)
            .set(salary).equalToWhenPresent(row::getSalary)
            .set(kpi).equalToWhenPresent(row::getKpi)
            .set(deptNo).equalToWhenPresent(row::getDeptNo)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .where(empNo, isEqualTo(row::getEmpNo))
        );
    }

    default Mono<Integer> updateAll(Emp row, WhereApplier whereApplier) {
        return update(c ->
            c.set(empName).equalToWhenPresent(row::getEmpName)
            .set(job).equalToWhenPresent(row::getJob)
            .set(manager).equalTo(row::getManager)
            .set(hireDate).equalToWhenPresent(row::getHireDate)
            .set(salary).equalToWhenPresent(row::getSalary)
            .set(kpi).equalToWhenPresent(row::getKpi)
            .set(deptNo).equalToWhenPresent(row::getDeptNo)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .applyWhere(whereApplier)
        );
    }

    default Mono<Integer> updateSelective(Emp row, WhereApplier whereApplier) {
        return update(c ->
            c.set(empName).equalToWhenPresent(row::getEmpName)
            .set(job).equalToWhenPresent(row::getJob)
            .set(manager).equalToWhenPresent(row::getManager)
            .set(hireDate).equalToWhenPresent(row::getHireDate)
            .set(salary).equalToWhenPresent(row::getSalary)
            .set(kpi).equalToWhenPresent(row::getKpi)
            .set(deptNo).equalToWhenPresent(row::getDeptNo)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .applyWhere(whereApplier)
        );
    }
}