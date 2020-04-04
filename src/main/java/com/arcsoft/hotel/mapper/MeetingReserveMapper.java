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
import org.apache.ibatis.annotations.SelectKey;
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
            "insert into meeting_reserve (meeting_type, name, ",
            "phone_number, invite_code, ",
            "user_id, status, ",
            "meeting_id, face)",
            "values (#{meetingType,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
            "#{phoneNumber,jdbcType=VARCHAR}, #{inviteCode,jdbcType=VARCHAR}, ",
            "#{userId,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
            "#{meetingId,jdbcType=INTEGER}, #{face,jdbcType=VARBINARY})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(MeetingReserve record);

    @InsertProvider(type = MeetingReserveSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(MeetingReserve record);

    @SelectProvider(type = MeetingReserveSqlProvider.class, method = "selectByExampleWithBLOBs")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "meeting_type", property = "meetingType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "invite_code", property = "inviteCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "meeting_id", property = "meetingId", jdbcType = JdbcType.INTEGER),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    List<MeetingReserve> selectByExampleWithBLOBs(MeetingReserveExample example);

    @SelectProvider(type = MeetingReserveSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "meeting_type", property = "meetingType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "invite_code", property = "inviteCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "meeting_id", property = "meetingId", jdbcType = JdbcType.INTEGER)
    })
    List<MeetingReserve> selectByExample(MeetingReserveExample example);

    @Select({
            "select",
            "id, meeting_type, name, phone_number, invite_code, user_id, status, meeting_id, ",
            "face",
            "from meeting_reserve",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "meeting_type", property = "meetingType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "invite_code", property = "inviteCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "meeting_id", property = "meetingId", jdbcType = JdbcType.INTEGER),
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
            "set meeting_type = #{meetingType,jdbcType=VARCHAR},",
            "name = #{name,jdbcType=VARCHAR},",
            "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
            "invite_code = #{inviteCode,jdbcType=VARCHAR},",
            "user_id = #{userId,jdbcType=INTEGER},",
            "status = #{status,jdbcType=INTEGER},",
            "meeting_id = #{meetingId,jdbcType=INTEGER},",
            "face = #{face,jdbcType=VARBINARY}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(MeetingReserve record);

    @Update({
            "update meeting_reserve",
            "set meeting_type = #{meetingType,jdbcType=VARCHAR},",
            "name = #{name,jdbcType=VARCHAR},",
            "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
            "invite_code = #{inviteCode,jdbcType=VARCHAR},",
            "user_id = #{userId,jdbcType=INTEGER},",
            "status = #{status,jdbcType=INTEGER},",
            "meeting_id = #{meetingId,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MeetingReserve record);
}