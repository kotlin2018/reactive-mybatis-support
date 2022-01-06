package pro.chenggang.project.reactive.mybatis.support.r2dbc.executor.result;

import org.apache.ibatis.type.TypeHandler;

/**
 * The interface Type handle context.
 *
 * @author chenggang
 * @version 1.0.0
 * @date 12 /11/21.
 */
public interface TypeHandleContext {

    /**
     * set delegated type handler
     *
     * @param delegatedTypeHandler the delegated type handler
     * @param rowResultWrapper     the row result wrapper
     */
    void contextWith(TypeHandler delegatedTypeHandler, RowResultWrapper rowResultWrapper);

}
