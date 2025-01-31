package pro.chenggang.project.reactive.mybatis.support.r2dbc.executor.type.defaults;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import io.r2dbc.spi.Statement;
import pro.chenggang.project.reactive.mybatis.support.r2dbc.executor.parameter.ParameterHandlerContext;
import pro.chenggang.project.reactive.mybatis.support.r2dbc.executor.type.R2dbcTypeHandlerAdapter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * The type Offset date time r2dbc type handler adapter.
 *
 * @author Gang Cheng
 * @version 1.0.0
 */
public class OffsetDateTimeR2dbcTypeHandlerAdapter implements R2dbcTypeHandlerAdapter<OffsetDateTime> {

    @Override
    public Class<OffsetDateTime> adaptClazz() {
        return OffsetDateTime.class;
    }

    @Override
    public void setParameter(Statement statement, ParameterHandlerContext parameterHandlerContext, OffsetDateTime parameter) {
        statement.bind(parameterHandlerContext.getIndex(), parameter.toLocalDateTime());
    }

    @Override
    public OffsetDateTime getResult(Row row, RowMetadata rowMetadata, String columnName) {
        LocalDateTime localDateTime = row.get(columnName, LocalDateTime.class);
        if (null == localDateTime) {
            return null;
        }
        return OffsetDateTime.from(localDateTime);
    }

    @Override
    public OffsetDateTime getResult(Row row, RowMetadata rowMetadata, int columnIndex) {
        LocalDateTime localDateTime = row.get(columnIndex, LocalDateTime.class);
        if (null == localDateTime) {
            return null;
        }
        return OffsetDateTime.from(localDateTime);
    }

}
