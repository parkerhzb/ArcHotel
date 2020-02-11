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
            "insert into invitation (id, room_id, ",
            "time, invite_code, ",
            "checkin_id)",
            "values (#{id,jdbcType=INTEGER}, #{roomId,jdbcType=INTEGER}, ",
            "#{time,jdbcType=TIMESTAMP}, #{inviteCode,jdbcType=VARCHAR}, ",
            "#{checkinId,jdbcType=INTEGER})"
    })
    int insert(Invitation record);

    @InsertProvider(type = InvitationSqlProvider.class, method = "insertSelective")
    int insertSelective(Invitation record);

    @SelectProvider(type = InvitationSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "invite_code", property = "inviteCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "checkin_id", property = "checkinId", jdbcType = JdbcType.INTEGER)
    })
    List<Invitation> selectByExample(InvitationExample example);

    @Select({
            "select",
            "id, room_id, time, invite_code, checkin_id",
            "from invitation",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "invite_code", property = "inviteCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "checkin_id", property = "checkinId", jdbcType = JdbcType.INTEGER)
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
            "checkin_id = #{checkinId,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Invitation record);
}