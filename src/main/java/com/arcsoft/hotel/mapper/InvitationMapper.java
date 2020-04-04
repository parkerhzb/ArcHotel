package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.Invitation;
import com.arcsoft.hotel.pojo.InvitationExample;
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

public interface InvitationMapper {
    @SelectProvider(type = InvitationSqlProvider.class, method = "countByExample")
    int countByExample(InvitationExample example);

    @DeleteProvider(type = InvitationSqlProvider.class, method = "deleteByExample")
    int deleteByExample(InvitationExample example);

    @Delete({
            "delete from invitation",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into invitation (room_id, time, ",
            "invite_code, user_id, ",
            "name, power)",
            "values (#{roomId,jdbcType=INTEGER}, #{time,jdbcType=TIMESTAMP}, ",
            "#{inviteCode,jdbcType=VARCHAR}, #{userId,jdbcType=INTEGER}, ",
            "#{name,jdbcType=VARCHAR}, #{power,jdbcType=VARCHAR})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(Invitation record);

    @InsertProvider(type = InvitationSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(Invitation record);

    @SelectProvider(type = InvitationSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "invite_code", property = "inviteCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "power", property = "power", jdbcType = JdbcType.VARCHAR)
    })
    List<Invitation> selectByExample(InvitationExample example);

    @Select({
            "select",
            "id, room_id, time, invite_code, user_id, name, power",
            "from invitation",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "invite_code", property = "inviteCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "power", property = "power", jdbcType = JdbcType.VARCHAR)
    })
    Invitation selectByPrimaryKey(Integer id);

    @UpdateProvider(type = InvitationSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Invitation record, @Param("example") InvitationExample example);

    @UpdateProvider(type = InvitationSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") Invitation record, @Param("example") InvitationExample example);

    @UpdateProvider(type = InvitationSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Invitation record);

    @Update({
            "update invitation",
            "set room_id = #{roomId,jdbcType=INTEGER},",
            "time = #{time,jdbcType=TIMESTAMP},",
            "invite_code = #{inviteCode,jdbcType=VARCHAR},",
            "user_id = #{userId,jdbcType=INTEGER},",
            "name = #{name,jdbcType=VARCHAR},",
            "power = #{power,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Invitation record);
}