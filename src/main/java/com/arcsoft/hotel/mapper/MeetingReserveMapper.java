package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.MeetingReserve;
import com.arcsoft.hotel.pojo.MeetingReserveExample;

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

public interface MeetingReserveMapper {
    @SelectProvider(type = MeetingReserveSqlProvider.class, method = "countByExample")
    int countByExample(MeetingReserveExample example);

    @DeleteProvider(type = MeetingReserveSqlProvider.class, method = "deleteByExample")
    int deleteByExample(MeetingReserveExample example);

    @Delete({
            "delete from meeting_reserve",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into meeting_reserve (id, meeting_id, ",
            "name, phone_number, ",
            "time_period, invite_code, ",
            "date, face)",
            "values (#{id,jdbcType=INTEGER}, #{meetingId,jdbcType=INTEGER}, ",
            "#{name,jdbcType=VARCHAR}, #{phoneNumber,jdbcType=VARCHAR}, ",
            "#{timePeriod,jdbcType=INTEGER}, #{inviteCode,jdbcType=VARCHAR}, ",
            "#{date,jdbcType=DATE}, #{face,jdbcType=VARBINARY})"
    })
    int insert(MeetingReserve record);

    @InsertProvider(type = MeetingReserveSqlProvider.class, method = "insertSelective")
    int insertSelective(MeetingReserve record);

    @SelectProvider(type = MeetingReserveSqlProvider.class, method = "selectByExampleWithBLOBs")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "meeting_id", property = "meetingId", jdbcType = JdbcType.INTEGER),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "time_period", property = "timePeriod", jdbcType = JdbcType.INTEGER),
            @Result(column = "invite_code", property = "inviteCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "date", property = "date", jdbcType = JdbcType.DATE),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    List<MeetingReserve> selectByExampleWithBLOBs(MeetingReserveExample example);

    @SelectProvider(type = MeetingReserveSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "meeting_id", property = "meetingId", jdbcType = JdbcType.INTEGER),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "time_period", property = "timePeriod", jdbcType = JdbcType.INTEGER),
            @Result(column = "invite_code", property = "inviteCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "date", property = "date", jdbcType = JdbcType.DATE)
    })
    List<MeetingReserve> selectByExample(MeetingReserveExample example);

    @Select({
            "select",
            "id, meeting_id, name, phone_number, time_period, invite_code, date, face",
            "from meeting_reserve",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "meeting_id", property = "meetingId", jdbcType = JdbcType.INTEGER),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "time_period", property = "timePeriod", jdbcType = JdbcType.INTEGER),
            @Result(column = "invite_code", property = "inviteCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "date", property = "date", jdbcType = JdbcType.DATE),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    MeetingReserve selectByPrimaryKey(Integer id);

    @UpdateProvider(type = MeetingReserveSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MeetingReserve record, @Param("example") MeetingReserveExample example);

    @UpdateProvider(type = MeetingReserveSqlProvider.class, method = "updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") MeetingReserve record, @Param("example") MeetingReserveExample example);

    @UpdateProvider(type = MeetingReserveSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") MeetingReserve record, @Param("example") MeetingReserveExample example);

    @UpdateProvider(type = MeetingReserveSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MeetingReserve record);

    @Update({
            "update meeting_reserve",
            "set meeting_id = #{meetingId,jdbcType=INTEGER},",
            "name = #{name,jdbcType=VARCHAR},",
            "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
            "time_period = #{timePeriod,jdbcType=INTEGER},",
            "invite_code = #{inviteCode,jdbcType=VARCHAR},",
            "date = #{date,jdbcType=DATE},",
            "face = #{face,jdbcType=VARBINARY}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(MeetingReserve record);

    @Update({
            "update meeting_reserve",
            "set meeting_id = #{meetingId,jdbcType=INTEGER},",
            "name = #{name,jdbcType=VARCHAR},",
            "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
            "time_period = #{timePeriod,jdbcType=INTEGER},",
            "invite_code = #{inviteCode,jdbcType=VARCHAR},",
            "date = #{date,jdbcType=DATE}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MeetingReserve record);
}