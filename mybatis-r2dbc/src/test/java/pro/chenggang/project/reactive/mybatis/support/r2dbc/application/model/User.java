package pro.chenggang.project.reactive.mybatis.support.r2dbc.application.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * auto generated
 * @author AutoGenerated
 */
@ToString
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class User {
    /**
     * 自增ID
     */
    protected Long id;

    /**
     * 用户ID
     */
    protected String uid;

    /**
     * 品牌ID
     */
    protected String brandId;

    /**
     * 门店ID
     */
    protected String storeId;

    /**
     * 账号ID
     */
    protected String accountId;

    /**
     * 用户名
     */
    protected String username;

    /**
     * 密码
     */
    protected String password;

    /**
     * 邮件
     */
    protected String email;

    /**
     * 手机号
     */
    protected String mobile;

    /**
     * 用户类型，SYSTEM，NORMAL
     */
    protected String userType;

    /**
     * 是否启用，1是，2否
     */
    protected Integer enableFlag;

    /**
     * 是否删除，1是，2否
     */
    protected Integer deleteFlag;

    /**
     * 最后一次登录时间
     */
    protected LocalDateTime lastLoginTime;

    /**
     * 创建时间
     */
    protected LocalDateTime createTime;

    /**
     * 创建人
     */
    protected String createUser;

    /**
     * 修改时间
     */
    protected LocalDateTime updateTime;

    /**
     * 更新人
     */
    protected String updateUser;

    public static final String ID = "id";

    public static final String DB_ID = "id";

    public static final String UID = "uid";

    public static final String DB_UID = "uid";

    public static final String BRAND_ID = "brandId";

    public static final String DB_BRAND_ID = "brand_id";

    public static final String STORE_ID = "storeId";

    public static final String DB_STORE_ID = "store_id";

    public static final String ACCOUNT_ID = "accountId";

    public static final String DB_ACCOUNT_ID = "account_id";

    public static final String USERNAME = "username";

    public static final String DB_USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String DB_PASSWORD = "password";

    public static final String EMAIL = "email";

    public static final String DB_EMAIL = "email";

    public static final String MOBILE = "mobile";

    public static final String DB_MOBILE = "mobile";

    public static final String USER_TYPE = "userType";

    public static final String DB_USER_TYPE = "user_type";

    public static final String ENABLE_FLAG = "enableFlag";

    public static final String DB_ENABLE_FLAG = "enable_flag";

    public static final String DELETE_FLAG = "deleteFlag";

    public static final String DB_DELETE_FLAG = "delete_flag";

    public static final String LAST_LOGIN_TIME = "lastLoginTime";

    public static final String DB_LAST_LOGIN_TIME = "last_login_time";

    public static final String CREATE_TIME = "createTime";

    public static final String DB_CREATE_TIME = "create_time";

    public static final String CREATE_USER = "createUser";

    public static final String DB_CREATE_USER = "create_user";

    public static final String UPDATE_TIME = "updateTime";

    public static final String DB_UPDATE_TIME = "update_time";

    public static final String UPDATE_USER = "updateUser";

    public static final String DB_UPDATE_USER = "update_user";
}