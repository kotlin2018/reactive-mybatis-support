package pro.chenggang.project.reactive.mybatis.support.r2dbc.dynamic;

import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.GeneralInsertDSL;
import org.mybatis.dynamic.sql.insert.InsertDSL;
import org.mybatis.dynamic.sql.insert.MultiRowInsertDSL;
import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.CountDSL;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Utility functions for building MyBatis3 mappers.
 *
 * @author Jeff Butler
 *
 */
public class ReactiveMyBatis3Utils {

    private ReactiveMyBatis3Utils() {}

    public static Mono<Long> count(ToMonoLongFunction<SelectStatementProvider> mapper, BasicColumn column, SqlTable table,
                                   CountDSLCompleter completer) {
        return mapper.applyAsLong(count(column, table, completer));
    }

    public static SelectStatementProvider count(BasicColumn column, SqlTable table, CountDSLCompleter completer) {
        return countFrom(SqlBuilder.countColumn(column).from(table), completer);
    }

    public static Mono<Long> countDistinct(ToMonoLongFunction<SelectStatementProvider> mapper, BasicColumn column, SqlTable table,
            CountDSLCompleter completer) {
        return mapper.applyAsLong(countDistinct(column, table, completer));
    }

    public static SelectStatementProvider countDistinct(BasicColumn column, SqlTable table,
            CountDSLCompleter completer) {
        return countFrom(SqlBuilder.countDistinctColumn(column).from(table), completer);
    }

    public static SelectStatementProvider countFrom(SqlTable table, CountDSLCompleter completer) {
        return countFrom(SqlBuilder.countFrom(table), completer);
    }

    public static Mono<Long> countFrom(ToMonoLongFunction<SelectStatementProvider> mapper,
            SqlTable table, CountDSLCompleter completer) {
        return mapper.applyAsLong(countFrom(table, completer));
    }

    public static SelectStatementProvider countFrom(CountDSL<SelectModel> start, CountDSLCompleter completer) {
        return completer.apply(start)
                .build()
                .render(RenderingStrategies.MYBATIS3);
    }

    public static Mono<Long> countFrom(ToMonoLongFunction<SelectStatementProvider> mapper,
            CountDSL<SelectModel> start, CountDSLCompleter completer) {
        return mapper.applyAsLong(countFrom(start, completer));
    }

    public static DeleteStatementProvider deleteFrom(SqlTable table, DeleteDSLCompleter completer) {
        return completer.apply(SqlBuilder.deleteFrom(table))
                .build()
                .render(RenderingStrategies.MYBATIS3);
    }

    public static Mono<Integer> deleteFrom(ToMonoIntFunction<DeleteStatementProvider> mapper,
                                           SqlTable table, DeleteDSLCompleter completer) {
        return mapper.applyAsInt(deleteFrom(table, completer));
    }

    public static <R> InsertStatementProvider<R> insert(R row, SqlTable table,
            UnaryOperator<InsertDSL<R>> completer) {
        return completer.apply(SqlBuilder.insert(row).into(table))
                .build()
                .render(RenderingStrategies.MYBATIS3);
    }

    public static <R> Mono<Integer> insert(ToMonoIntFunction<InsertStatementProvider<R>> mapper, R row,
            SqlTable table, UnaryOperator<InsertDSL<R>> completer) {
        return mapper.applyAsInt(insert(row, table, completer));
    }

    public static GeneralInsertStatementProvider generalInsert(SqlTable table,
            UnaryOperator<GeneralInsertDSL> completer) {
        return completer.apply(GeneralInsertDSL.insertInto(table))
                .build()
                .render(RenderingStrategies.MYBATIS3);
    }

    public static Mono<Integer> generalInsert(ToMonoIntFunction<GeneralInsertStatementProvider> mapper,
            SqlTable table, UnaryOperator<GeneralInsertDSL> completer) {
        return mapper.applyAsInt(generalInsert(table, completer));
    }

    public static <R> MultiRowInsertStatementProvider<R> insertMultiple(Collection<R> records, SqlTable table,
            UnaryOperator<MultiRowInsertDSL<R>> completer) {
        return completer.apply(SqlBuilder.insertMultiple(records).into(table))
                .build()
                .render(RenderingStrategies.MYBATIS3);
    }

    public static <R> Mono<Integer> insertMultiple(ToMonoIntFunction<MultiRowInsertStatementProvider<R>> mapper,
            Collection<R> records, SqlTable table, UnaryOperator<MultiRowInsertDSL<R>> completer) {
        return mapper.applyAsInt(insertMultiple(records, table, completer));
    }

    public static <R> Mono<Integer> insertMultipleWithGeneratedKeys(ToMonoIntBiFunction<String, List<R>> mapper,
                                                          Collection<R> records, SqlTable table, 
                                                          UnaryOperator<MultiRowInsertDSL<R>> completer) {
        MultiRowInsertStatementProvider<R> provider = insertMultiple(records, table, completer);
        return mapper.applyAsInt(provider.getInsertStatement(), provider.getRecords());
    }

    public static SelectStatementProvider select(BasicColumn[] selectList, SqlTable table,
            SelectDSLCompleter completer) {
        return select(SqlBuilder.select(selectList).from(table), completer);
    }

    public static SelectStatementProvider select(QueryExpressionDSL<SelectModel> start,
            SelectDSLCompleter completer) {
        return completer.apply(start)
                .build()
                .render(RenderingStrategies.MYBATIS3);
    }

    public static SelectStatementProvider selectDistinct(BasicColumn[] selectList, SqlTable table,
            SelectDSLCompleter completer) {
        return select(SqlBuilder.selectDistinct(selectList).from(table), completer);
    }

    public static <R> Flux<R> selectDistinct(Function<SelectStatementProvider, Flux<R>> mapper,
                                             BasicColumn[] selectList,
                                             SqlTable table,
                                             SelectDSLCompleter completer) {
        return mapper.apply(selectDistinct(selectList, table, completer));
    }

    public static <R> Flux<R> selectList(Function<SelectStatementProvider, Flux<R>> mapper,
            BasicColumn[] selectList, SqlTable table, SelectDSLCompleter completer) {
        return mapper.apply(select(selectList, table, completer));
    }

    public static <R> Flux<R> selectList(Function<SelectStatementProvider, Flux<R>> mapper,
            QueryExpressionDSL<SelectModel> start, SelectDSLCompleter completer) {
        return mapper.apply(select(start, completer));
    }

    public static <R> Mono<R> selectOne(Function<SelectStatementProvider, Mono<R>> mapper,
            BasicColumn[] selectList, SqlTable table, SelectDSLCompleter completer) {
        return mapper.apply(select(selectList, table, completer));
    }

    public static <R> Mono<R> selectOne(Function<SelectStatementProvider, Mono<R>> mapper,
            QueryExpressionDSL<SelectModel> start, SelectDSLCompleter completer) {
        return mapper.apply(select(start, completer));
    }

    public static UpdateStatementProvider update(SqlTable table, UpdateDSLCompleter completer) {
        return completer.apply(SqlBuilder.update(table))
                .build()
                .render(RenderingStrategies.MYBATIS3);
    }

    public static Mono<Integer> update(ToMonoIntFunction<UpdateStatementProvider> mapper,
            SqlTable table, UpdateDSLCompleter completer) {
        return mapper.applyAsInt(update(table, completer));
    }
}
