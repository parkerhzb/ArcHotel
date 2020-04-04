package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.User;
import com.arcsoft.hotel.pojo.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @SelectProvider(type = UserSqlProvider.class, method = "countByExample")
    int countByExample(UserExample example);

    @DeleteProvider(type = UserSqlProvider.class, method = "deleteByExample")
    int deleteByExample(UserExample example);

    @Delete({
            "delete from user",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into user (status, open_id, ",
            "sses_id, nickname, ",
            "headimgurl, phone_number)",
            "values (#{status,jdbcType=VARCHAR}, #{openId,jdbcType=VARCHAR}, ",
            "#{ssesId,jdbcType=VARCHAR}, #{nickname,jdbcType=VARCHAR}, ",
            "#{headimgurl,jdbcType=VARCHAR}, #{phoneNumber,jdbcType=VARCHAR})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(User record);

    @InsertProvider(type = UserSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(User record);

    @SelectProvider(type = UserSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
            @Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sses_id", property = "ssesId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "nickname", property = "nickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "headimgurl", property = "headimgurl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR)
    })
    List<User> selectByExample(UserExample example);

    @Select({
            "select",
            "id, status, open_id, sses_id, nickname, headimgurl, phone_number",
            "from user",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "status", property = "status", jdbcType = JdbcType.VARCHAR),
            @Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sses_id", property = "ssesId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "nickname", property = "nickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "headimgurl", property = "headimgurl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(Integer id);

    @UpdateProvider(type = UserSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    @UpdateProvider(type = UserSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    @UpdateProvider(type = UserSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
            "update user",
            "set status = #{status,jdbcType=VARCHAR},",
            "open_id = #{openId,jdbcType=VARCHAR},",
            "sses_id = #{ssesId,jdbcType=VARCHAR},",
            "nickname = #{nickname,jdbcType=VARCHAR},",
            "headimgurl = #{headimgurl,jdbcType=VARCHAR},",
            "phone_number = #{phoneNumber,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);
}