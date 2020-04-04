package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.MeetingAttendees;
import com.arcsoft.hotel.pojo.MeetingAttendeesExample;

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

public interface MeetingAttendeesMapper {
    @SelectProvider(type = MeetingAttendeesSqlProvider.class, method = "countByExample")
    int countByExample(MeetingAttendeesExample example);

    @DeleteProvider(type = MeetingAttendeesSqlProvider.class, method = "deleteByExample")
    int deleteByExample(MeetingAttendeesExample example);

    @Delete({
            "delete from meeting_attendees",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into meeting_attendees (reserve_id, name, ",
            "status, face)",
            "values (#{reserveId,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{status,jdbcType=INTEGER}, #{face,jdbcType=VARBINARY})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(MeetingAttendees record);

    @InsertProvider(type = MeetingAttendeesSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(MeetingAttendees record);

    @SelectProvider(type = MeetingAttendeesSqlProvider.class, method = "selectByExampleWithBLOBs")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "reserve_id", property = "reserveId", jdbcType = JdbcType.INTEGER),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    List<MeetingAttendees> selectByExampleWithBLOBs(MeetingAttendeesExample example);

    @SelectProvider(type = MeetingAttendeesSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "reserve_id", property = "reserveId", jdbcType = JdbcType.INTEGER),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER)
    })
    List<MeetingAttendees> selectByExample(MeetingAttendeesExample example);

    @Select({
            "select",
            "id, reserve_id, name, status, face",
            "from meeting_attendees",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "reserve_id", property = "reserveId", jdbcType = JdbcType.INTEGER),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    MeetingAttendees selectByPrimaryKey(Integer id);

    @UpdateProvider(type = MeetingAttendeesSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MeetingAttendees record, @Param("example") MeetingAttendeesExample example);

    @UpdateProvider(type = MeetingAttendeesSqlProvider.class, method = "updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") MeetingAttendees record, @Param("example") MeetingAttendeesExample example);

    @UpdateProvider(type = MeetingAttendeesSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") MeetingAttendees record, @Param("example") MeetingAttendeesExample example);

    @UpdateProvider(type = MeetingAttendeesSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MeetingAttendees record);

    @Update({
            "update meeting_attendees",
            "set reserve_id = #{reserveId,jdbcType=INTEGER},",
            "name = #{name,jdbcType=VARCHAR},",
            "status = #{status,jdbcType=INTEGER},",
            "face = #{face,jdbcType=VARBINARY}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(MeetingAttendees record);

    @Update({
            "update meeting_attendees",
            "set reserve_id = #{reserveId,jdbcType=INTEGER},",
            "name = #{name,jdbcType=VARCHAR},",
            "status = #{status,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MeetingAttendees record);
}